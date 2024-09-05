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
    public Floor() {
        super('_', "Floor");
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(TraversableAbility.FLOORTRAVERSABLE);
    }
}

