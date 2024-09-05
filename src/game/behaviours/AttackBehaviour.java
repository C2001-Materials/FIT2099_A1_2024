package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.LocationUtils;
import game.actions.StompAction;

/**
 * A class that represents an attack behaviour.
 * An attack behaviour is a behaviour that allows a NPC actor to attack another actor, usually the player.
 * The actor will attack the target actor if they are adjacent to each other.
 * Otherwise, the actor will do nothing, and the priority returns to WanderBehaviour.
 */
public class AttackBehaviour implements Behaviour {

    /**
     * The target actor that our actor is attacking
     */
    private final Actor target;

    /**
     * Constructor.
     * @param subject The target actor that our actor is attacking
     */
    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Get the action to attack the target actor if they are adjacent to each other.
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return An AttackAction object if the target actor is adjacent to the actor, null otherwise
     */
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