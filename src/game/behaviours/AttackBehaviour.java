package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.LocationUtils;
import game.actions.StompAction;

public class AttackBehaviour implements Behaviour {
    private final Actor target;

    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);


        if (LocationUtils.isAdjacent(here, there)) {
            return new StompAction(target, here.toString());
        }
        return null;
    }
}