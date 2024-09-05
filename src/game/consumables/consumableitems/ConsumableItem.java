package game.consumables.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.consumables.Consumable;

/**
 * An abstract class that represents a consumable item.
 * A consumable item is a concrete item that can be consumed by an actor.
 * @author Aaron Lam Kong Yew
 */
public abstract class ConsumableItem extends Item implements Consumable {

    /**
     * The number of charges the consumable item has.
     * 1 charge indicates that the item can be consumed only once.
     */
    protected int charges;

    /**
     * Constructor.
     * @param name the name of the consumable item
     * @param displayChar the character that will represent the consumable item
     * @param portable whether the consumable item is portable
     * @param charges the number of charges the consumable item has
     */
    public ConsumableItem(String name, char displayChar, boolean portable, int charges) {
        super(name, displayChar, portable);
        this.charges = charges;
    }

    /**
     * Only has the ConsumeAction as an allowable action, for now.
     * @return ConsumeAction
     */
    @Override
    public ActionList allowableActions(Actor otherActor) {
        ActionList actions = super.allowableActions(otherActor); // TODO: Be careful of parameter names
            actions.add(new ConsumeAction(this));

        return actions;
    }

    /**
     * Implements the consume method from the Consumable interface.
     * Consumes the consumable item.
     * @param actor the actor consuming the consumable item
     * @return true if the consumable item is consumed, false otherwise
     */
    @Override
    public abstract boolean consume(Actor actor);
}