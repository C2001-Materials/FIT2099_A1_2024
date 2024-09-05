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
 */
public class FurnaceGolem extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private Actor target;

    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.setIntrinsicWeapon(new Foot());
        this.addCapability(EntityDamageAbility.EXPLOSION);
        this.addCapability(EntityDamageAbility.FIRE_RING);
        this.addCapability(EntityPassiveAbility.FIRE_RESISTANCE);
    }

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
