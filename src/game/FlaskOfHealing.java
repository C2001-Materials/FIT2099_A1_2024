package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class FlaskOfHealing extends Item implements Consumable {
    private int charges;

    public FlaskOfHealing() {
        super("Flask of Healing", 'u', true);
        this.charges = 5;
    }

    @Override
    public void consume(Actor actor) {
        if (charges > 0) {
            actor.heal(150);
            charges--;
        } else {
            System.out.println("Flask of Healing is empty");
        }
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();

        actions.add(new ConsumeAction(this));

        return actions;
    }
}