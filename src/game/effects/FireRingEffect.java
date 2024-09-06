package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityDamageAbility;
import game.positions.grounds.Fire;
import game.abilites.EntityPassiveAbility;

import java.util.List;

/**
 * A class  that represents an effect that deals damage to actors,
 * by changing the ground to fire.
 */
public class FireRingEffect implements DamageEffect {

    /**
     * Applies the effect of the fire ring.
     * @param actor the actor causing the fire ring
     * @param location the location of the fire ring
     */
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
        if (!actor.hasCapability(EntityDamageAbility.FIRE_RING)) {
            return "";
        }

        for (Location loc : locations) {
            Ground ground = loc.getGround();
            if (!ground.hasCapability(EntityPassiveAbility.FIRE_RESISTANCE)) {
                loc.setGround(new Fire(ground));
            }
        }
        return "\n" + actor + " sets the surrounding ground on fire.";
    }
}