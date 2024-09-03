package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class FlaskOfRejuvenation extends Item implements Consumable {
    private int charges;

    public FlaskOfRejuvenation() {
        super("Flask of Healing", 'o', true);
        this.charges = 3;
    }

    @Override
    public void consume(Actor actor) {
        if (charges > 0) {
            actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 100);
            charges--;
        } else {
            System.out.println("Flask of Rejuvenation is empty");
        }
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();

        actions.add(new ConsumeAction(this));

        return actions;
    }
}