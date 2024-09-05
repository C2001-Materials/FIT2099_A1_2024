package game.positions.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.abilites.TraversableAbility;

/**
 * A class representing a wall that cannot be entered by any actor
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Aaron Lam Kong Yew
 *
 */
public class Wall extends Ground {

    /**
     * Constructor.
     */
    public Wall() {
        super('#', "Wall");
    }

    /**
     * Checks if an actor can enter the wall.
     * The actor must have the TraversableAbility.WALLTRAVERSABLE ability.
     * @param actor the actor to check
     * @return true if the actor can enter, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(TraversableAbility.WALLTRAVERSABLE);
    }
}
