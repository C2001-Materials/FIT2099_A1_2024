package game;

import edu.monash.fit2099.engine.actors.Actor;

public class FlaskOfHealing extends ConsumableItem {

    public FlaskOfHealing() {
        super("Flask of Healing", 'u', true, 5);
    }

    @Override
    public boolean consume(Actor actor) {
        if (charges > 0) {
            actor.heal(150);
            charges--;
            return true;
        } else {
            return false;
        }
    }

}