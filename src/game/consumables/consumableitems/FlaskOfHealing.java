package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A class that represents a Flask of Healing consumable item.
 * A Flask of Healing is a consumable item that can be consumed by an actor to heal the actor by 150 hit points.
 * @author Aaron Lam Kong Yew
 */
public class FlaskOfHealing extends ConsumableItem {

    /**
     * Constructor.
     */
    public FlaskOfHealing() {
        super("Flask of Healing", 'u', true, 5);
    }

    /**
     * Consumes the Flask of Healing.
     * The actor will be healed by 150 hit points.
     * Will run out of charges after 5 uses.
     * @param actor the actor consuming the Flask of Healing
     * @return true if the Flask of Healing is consumed, false otherwise
     */
    @Override
    public boolean consume(Actor actor) {
        if (charges > 0) {
            actor.heal(150);
            charges--;
            return true;
        } else {
            return false;
        }
    }

}