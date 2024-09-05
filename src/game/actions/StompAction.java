package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.displays.FancyMessage;
import game.positions.grounds.Fire;
import game.positions.LocationUtils;
import game.abilites.EntityDamageAbility;
import game.abilites.EntityPassiveAbility;

import java.util.List;
import java.util.Random;

/**
 * A class that represents a stomp attack action.
 * A stomp action is the same as an attack action,
 * but with a chance to deal explosion damage to surrounding actors,
 * and set surrounding ground on fire.
 */
public class StompAction extends AttackAction {
    private Actor target;
    private static final double EXPLOSION_CHANCE = 10;
    private static final int EXPLOSION_DAMAGE = 50;

    public StompAction(Actor target, String direction) {
        super(target, direction, null);
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result =  super.execute(actor, map);

        List<Location> surroundingLocations = LocationUtils.getSurroundingLocations(map.locationOf(actor));
        if (actor.hasCapability((EntityDamageAbility.EXPLOSION))) {
            Random rand = new Random();
            if (rand.nextInt(100) < EXPLOSION_CHANCE) {
                result += "\n" + actor + "'s stomp attack results in a shockwave in the surrounding areas.";
                result += dealExplosionDamage(surroundingLocations, map);
                if (actor.hasCapability(EntityDamageAbility.FIRE_RING)) {
                    setSurroundingGroundOnFire(surroundingLocations, map);
                }
            }
        }
        if (!target.isConscious()) {
            // BEHOLD, YOU DIED!
            FancyMessage.printYouDied();
            map.removeActor(target);
        }
        return result;
    }

    private String dealExplosionDamage( List<Location> surroundingLocations, GameMap map) {
        String result = "";
        for (Location loc : surroundingLocations) {
            if (map.isAnActorAt(loc)) {
                Actor actor = map.getActorAt(loc);
                actor.hurt(EXPLOSION_DAMAGE);
                result += "\n" + actor + " takes " + EXPLOSION_DAMAGE + " explosion damage.";
            }
        }
        return result;
    }

    private void setSurroundingGroundOnFire( List<Location> surroundingLocations, GameMap map) {
        for (Location loc : surroundingLocations) {
            Ground ground = loc.getGround();
            if (!ground.hasCapability(EntityPassiveAbility.FIRE_RESISTANCE)) {
                loc.setGround(new Fire(ground));
            }
        }
    }

}

