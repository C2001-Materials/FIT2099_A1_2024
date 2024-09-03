package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class FlaskOfRejuvenation extends ConsumableItem {
    private int charges;

    public FlaskOfRejuvenation() {
        super("Flask of Rejuvenation", 'o', true, 3);
    }

    @Override
    public String consume(Actor actor) {
        if (charges > 0) {
            actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 100);
            charges--;
            return "Flask of Rejuvenation is consumed";
        } else {
            return "Flask of Rejuvenation is empty";
        }
    }
}