package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

public class ShadowtreeFragment extends ConsumableItem {
    public ShadowtreeFragment() {
        super("Shadowtree Fragment", 'e', true, 1);
    }

    @Override
    public boolean consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 50);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 25);
        actor.modifyAttributeMaximum(TarnishedActorAttributes.STRENGTH, ActorAttributeOperations.INCREASE, 5);
        // TODO: Remove the item from the player's inventory, if it is in there
        if (actor.getItemInventory().contains(this)){
            actor.removeItemFromInventory(this);
        }
        return true;
    }
}