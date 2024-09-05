package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * A class that represents a stomp attack action.
 * A stomp action is the same as an attack action,
 * but with a chance to deal explosion damage to surrounding actors,
 * and set surrounding ground on fire.
 */
public class StompAction extends AttackAction {
    private static final double EXPLOSION_CHANCE = 10;
    private static final int EXPLOSION_DAMAGE = 50;

    public StompAction(Actor target, String direction) {
        super(target, direction, null);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result =  super.execute(actor, map);

        if (actor.hasCapability((EntityDamageAbility.EXPLOSION))) {
            Random rand = new Random();
            if (rand.nextInt(100) < EXPLOSION_CHANCE) {
                result += "\n" + actor + "'s stomp attack results in a shockwave in the surrounding areas.";
                result += dealExplosionDamage(map.locationOf(actor), map);
                if (actor.hasCapability(EntityDamageAbility.FIRE_RING)) {
                    setSurroundingGroundOnFire(map.locationOf(actor), map);
                }
            }
        }
        return result;
    }

    private String dealExplosionDamage(Location location, GameMap map) {
        String result = "";
        List<Location> surroundingLocations = location.getExits().stream()
                .map(exit -> exit.getDestination()) // TODO: use eta conversion
                .toList();

        for (Location loc : surroundingLocations) {
            if (map.isAnActorAt(loc)) {
                Actor actor = map.getActorAt(loc);
                actor.hurt(EXPLOSION_DAMAGE);
                result += "\n" + actor + " takes " + EXPLOSION_DAMAGE + " explosion damage.";
            }
        }
        return result;
    }


    private void setSurroundingGroundOnFire(Location location, GameMap map) {
        List<Location> surroundingLocations = location.getExits().stream()
                .map(exit -> exit.getDestination())
                .toList();

        for (Location loc : surroundingLocations) {
            Ground ground = loc.getGround();
            if (!ground.hasCapability(EntityPassiveAbility.FIRE_RESISTANCE)) {
                loc.setGround(new Fire(ground));
            }
        }
    }



}

