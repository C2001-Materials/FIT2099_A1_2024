package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public abstract class ConsumableItem extends Item implements Consumable {
    protected int charges;

    public ConsumableItem(String name, char displayChar, boolean portable, int charges) {
        super(name, displayChar, portable);
        this.charges = charges;
    }

    @Override
    public ActionList allowableActions(Actor otherActor) {
        ActionList actions = super.allowableActions(otherActor); // TODO: Be careful of parameter names
            actions.add(new ConsumeAction(this));

        return actions;
    }

    @Override
    public abstract boolean consume(Actor actor);
}