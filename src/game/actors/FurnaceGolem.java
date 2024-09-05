package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.abilites.EntityDamageAbility;
import game.abilites.EntityPassiveAbility;
import game.actions.AttackAction;
import game.actors.attributes.Status;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.weapons.intristicweapons.Foot;
import game.positions.LocationUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the Furnace Golem
 * For now, it can only wander around the map.
 * @author Adrian Kristanto
 * Modified by Aaron Lam Kong Yew
 */
public class FurnaceGolem extends Actor {

    /**
     * The behaviours that the Furnace Golem can perform
     * Each behaviour has a priority, where the lower the number, the higher the priority
     * Each behaviour will call a specific action to be performed
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * The target that the Furnace Golem is following
     */
    private Actor target;

    /**
     * Constructor.
     */
    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.setIntrinsicWeapon(new Foot());
        this.addCapability(EntityDamageAbility.EXPLOSION);
        this.addCapability(EntityDamageAbility.FIRE_RING);
        this.addCapability(EntityPassiveAbility.FIRE_RESISTANCE);
    }

    /**
     * Furnace Golem's turn to play
     * Order of Priority:
     * 1. Attack the player if the player is within stomping range
     * 2. Follow the player if the player is not within stomping range
     * 3. Wander around the map
     * @param actions the actions that the Furnace Golem can perform
     * @param lastAction the last action that the Furnace Golem performed
     * @param map the map that the Furnace Golem is on
     * @param display the display that shows the game
     * @return the action that the Furnace Golem will perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (target != null && LocationUtils.isAdjacent(map.locationOf(this), map.locationOf(target))) {
            display.println("Player is within Stomping Range!");
            this.behaviours.put(1, new FollowBehaviour(target));
            this.behaviours.put(0, new AttackBehaviour(target));
        } else {
            this.behaviours.remove(0);
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Furnace Golem's allowable actions
     * Furnace Golem can only be attacked by the player if the player is hostile to the Furnace Golem
     * @param otherActor the other actor that the Furnace Golem can interact with
     * @param direction the direction of the other actor
     * @param map the map that the Furnace Golem is on
     * @return the list of actions that the Furnace Golem can perform
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            this.target = otherActor;
        }
        return actions;
    }

}
