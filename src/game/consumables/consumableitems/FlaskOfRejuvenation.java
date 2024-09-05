package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class FlaskOfRejuvenation extends ConsumableItem {

    public FlaskOfRejuvenation() {
        super("Flask of Rejuvenation", 'o', true, 3);
    }

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