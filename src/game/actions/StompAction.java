package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.displays.FancyMessage;
import game.effects.ExplosionEffect;
import game.effects.FireRingEffect;
import game.positions.grounds.Fire;
import game.positions.LocationUtils;
import game.abilites.EntityDamageAbility;
import game.abilites.EntityPassiveAbility;

import java.util.List;
import java.util.Random;

/**
 * A class that represents a stomp attack action.
 * A stomp action is the same as an attack action,
 * but with a chance to deal explosion damage to surrounding actors,
 * and set surrounding ground on fire.
 * @author Aaron Lam Kong Yew
 */
public class StompAction extends AttackAction {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The chance of an extra explosion damaging the surrounding actors
     */
    private static final double EXPLOSION_CHANCE = 10;

    /**
     * An explosion effect that deals extra explosion damage to surrounding actors
     */
    private ExplosionEffect explosionEffect = new ExplosionEffect();

    /**
     * A fire ring effect that sets surrounding ground on fire
     */
    private FireRingEffect fireRingEffect = new FireRingEffect();

    /**
     * Constructor.
     *
     * @param target the Actor that is to be attacked
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public StompAction(Actor target, String direction) {
        super(target, direction, null);
        this.target = target;
    }

    /**
     * Execute the stomp attack action
     * If the actor has the explosion capability, there is a chance to deal explosion damage to surrounding actors,
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result =  super.execute(actor, map);

        List<Location> surroundingLocations = LocationUtils.getSurroundingLocations(map.locationOf(actor));
        Random rand = new Random();
        if (rand.nextInt(100) < EXPLOSION_CHANCE) {
            result += "\n" + actor + "'s stomp attack results in a shockwave in the surrounding areas.";
            result += explosionEffect.applyEffect(actor, surroundingLocations, map);
            result += fireRingEffect.applyEffect(actor, surroundingLocations, map);
            // Some actors' stomp may only cause explosion damage,
            // while others may set-of a chain reaction, setting surrounding ground on fire.

        }
        if (!target.isConscious()) {
            FancyMessage.printYouDied();
            map.removeActor(target);
        }
        return result;
    }
}

