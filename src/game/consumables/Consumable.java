package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that represents anything that can be consumed.
 * It does not have to be an item. It could be an Actor or Ground too!
 * Consuming the entity will have an effect on the actor.
 * @author Aaron Lam Kong Yew
 */
public interface Consumable {

    /**
     * Consumes the entity.
     * @param actor the actor consuming the entity
     * @return true if the entity is consumed, false otherwise
     */
    boolean consume(Actor actor);
}