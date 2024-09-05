package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;
import java.util.Random;


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

        if (actor.hasCapability((WeaponAbility.EXPLOSION))) {
            Random rand = new Random();
            if (rand.nextInt(100) < EXPLOSION_CHANCE) {
                result += "\n" + actor + "'s stomp attack results in a shockwave in the surrounding areas.";
                result += dealExplosionDamage(map.locationOf(actor), map);

            }
        }
        return result;
    }

    private String dealExplosionDamage(Location location, GameMap map) {
        String result = "";
        List<Location> surroundingLocations = location.getExits().stream()
                .map(exit -> exit.getDestination()) // TODO: use eta conversion
                .toList();

        System.out.println(surroundingLocations);
        for (Location loc : surroundingLocations) {
            if (map.isAnActorAt(loc)) {
                Actor actor = map.getActorAt(loc);
                actor.hurt(EXPLOSION_DAMAGE);
                result += "\n" + actor + " takes " + EXPLOSION_DAMAGE + " explosion damage.";
            }
        }

        return result;
    }
}

