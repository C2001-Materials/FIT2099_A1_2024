package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * A class that represents a Flask of Rejuvenation consumable item.
 * A Flask of Rejuvenation is a consumable item that can be consumed by an actor to increase the actor's mana by 100 points.
 * @author Aaron Lam Kong Yew
 */
public class FlaskOfRejuvenation extends ConsumableItem {

    /**
     * Constructor.
     */
    public FlaskOfRejuvenation() {
        super("Flask of Rejuvenation", 'o', true, 3);
    }

    /**
     * Consumes the Flask of Rejuvenation.
     * The actor will have their mana attribute increased by 100 points.
     * Will run out of charges after 3 uses.
     * @param actor the actor consuming the Flask of Rejuvenation
     * @return true if the Flask of Rejuvenation is consumed, false otherwise
     */
    @Override
    public boolean consume(Actor actor) {
        if (charges > 0) {
            actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 100);
            charges--;
            return true;
        } else {
            return false;
        }
    }
}