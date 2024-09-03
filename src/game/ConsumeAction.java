package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ConsumeAction extends Action {
    private Consumable consumableItem;

    public ConsumeAction(Consumable consumableItem) {
        this.consumableItem = consumableItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        consumableItem.consume(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumableItem;
    }
}
