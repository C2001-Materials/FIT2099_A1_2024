package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.Consumable;

/**
 * Class representing an action to consume a Consumable item
 * Note that the actor must have a consumable item in their inventory
 * Otherwise, the execute method will throw an error.
 * @author Aaron Lam Kong Yew
 */
public class ConsumeAction extends Action {
    /**
     * The Consumable item to be consumed
     */
    private Consumable consumableItem;

    /**
     * Constructor.
     *
     * @param consumableItem the Consumable object
     */
    public ConsumeAction(Consumable consumableItem) {
        this.consumableItem = consumableItem;
    }

    /**
     * Execute the consume action
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean consumed = consumableItem.consume(actor);
        if (consumed) {
            return menuDescription(actor) + " and is now feeling better!";
        } else {
            return consumableItem + " is empty";
        }
    }

    /**
     * Returns a description of the action.
     *
     * @param actor the actor performing the action
     * @return a String representing the description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumableItem;
    }
}
