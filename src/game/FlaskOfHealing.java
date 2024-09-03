package game;

import edu.monash.fit2099.engine.actors.Actor;

public class FlaskOfHealing extends ConsumableItem {
    private int charges;

    public FlaskOfHealing() {
        super("Flask of Healing", 'u', true, 5);
    }

    @Override
    public String consume(Actor actor) {
        if (charges > 0) {
            actor.heal(150);
            charges--;
            return "Flask of Healing is consumed";
        } else {
            return "Flask of Healing is empty";
        }
    }

}