package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

public class ExplosionEffect implements DamageEffect {

    private static final int EXPLOSION_DAMAGE = 50;

    @Override
    public void applyEffect(Actor actor, Location location) {
        // For future implementation
    }

    /**
     * Deals extra explosion damage to surrounding actors.
     * @param actor the actor causing the explosion
     * @param locations a list of surrounding locations
     * @param map the game map
     * @return a string describing the result of the explosion damage
     */
    @Override
    public String applyEffect(Actor actor, List<Location> locations, GameMap map) {
        String result = "";
        for (Location loc : locations) {
            if (map.isAnActorAt(loc)) {
                Actor target = map.getActorAt(loc);
                target.hurt(EXPLOSION_DAMAGE);
                result += "\n" + target + " takes " + EXPLOSION_DAMAGE + " explosion damage.";
            }
        }
        return result;
    }
}