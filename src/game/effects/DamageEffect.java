package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

/**
 * An interface that represents an effect that deals damage to actors.
 * Requires the actor to have the capability to deal damage.
 * The effect can be applied to a single actor or multiple actors,
 * and to a single location or multiple locations,
 * The effect can also return a string describing the result of the effect, or an empty string if no damage is dealt.
 */
public interface DamageEffect {

    /**
     * Applies the effect of the damage.
     * @param actor the actor causing the damage
     * @param location the location of the damage
     */
    void applyEffect(Actor actor, Location location);

    /**
     * Deals damage to surrounding actors.
     * May or may not depend on capabilities.
     * @param actor the actor causing the damage
     * @param locations a list of surrounding locations
     * @param map the game map
     * @return a string describing the result of the damage, or an empty string if no damage is dealt
     */
    String applyEffect(Actor actor, List<Location> locations, GameMap map);
}