package game.positions.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.abilites.TraversableAbility;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Aaron Lam Kong Yew
 *
 */
public class Floor extends Ground {

    /**
     * Constructor.
     */
    public Floor() {
        super('_', "Floor");
    }

    /**
     * Checks if an actor can enter the floor.
     * The actor must have the TraversableAbility.FLOORTRAVERSABLE ability.
     * @param actor the actor to check
     * @return true if the actor can enter, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(TraversableAbility.FLOORTRAVERSABLE);
    }
}

