package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.grounds.Fire;
import game.abilites.EntityPassiveAbility;

import java.util.List;

public class FireRingEffect implements DamageEffect {

    @Override
    public void applyEffect(Actor actor, Location location) {
        // For future implementation
    }

    /**
     * Sets surrounding ground on fire.
     * May or may not depend on Explosion capability.
     * @param actor the actor causing the fire ring
     * @param locations a list of surrounding locations
     * @param map the game map
     */
    @Override
    public String applyEffect(Actor actor, List<Location> locations, GameMap map) {
        for (Location loc : locations) {
            Ground ground = loc.getGround();
            if (!ground.hasCapability(EntityPassiveAbility.FIRE_RESISTANCE)) {
                loc.setGround(new Fire(ground));
            }
        }
        return "\n" + actor + " sets the surrounding ground on fire.";
    }
}