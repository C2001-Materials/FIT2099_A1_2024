package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.displays.FancyMessage;
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
     * The amount of damage dealt by the explosion
     */
    private static final int EXPLOSION_DAMAGE = 50;

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
        if (actor.hasCapability((EntityDamageAbility.EXPLOSION))) {
            Random rand = new Random();
            if (rand.nextInt(100) < EXPLOSION_CHANCE) {
                result += "\n" + actor + "'s stomp attack results in a shockwave in the surrounding areas.";
                result += dealExplosionDamage(surroundingLocations, map);

                // Some actors' stomp may only cause explosion damage,
                // while others may set-off a chain reaction, setting surrounding ground on fire.
                if (actor.hasCapability(EntityDamageAbility.FIRE_RING)) {
                    setSurroundingGroundOnFire(surroundingLocations, map);
                }
            }
        }
        if (!target.isConscious()) {
            // BEHOLD, YOU DIED!
            FancyMessage.printYouDied();
            map.removeActor(target);
        }
        return result;
    }

    /**
     * Deals extra explosion damage to surrounding actors.
     * @param surroundingLocations a list of surrounding locations
     * @param map the game map
     * @return a string describing the result of the explosion damage
     */
    private String dealExplosionDamage( List<Location> surroundingLocations, GameMap map) {
        String result = "";
        for (Location loc : surroundingLocations) {
            if (map.isAnActorAt(loc)) {
                Actor actor = map.getActorAt(loc);
                actor.hurt(EXPLOSION_DAMAGE);
                result += "\n" + actor + " takes " + EXPLOSION_DAMAGE + " explosion damage.";
            }
        }
        return result;
    }

    /**
     * Sets surrounding ground on fire.
     * May or may not depend on Explosion capability.
     * @param surroundingLocations a list of surrounding locations
     * @param map the game map
     */
    private void setSurroundingGroundOnFire( List<Location> surroundingLocations, GameMap map) {
        for (Location loc : surroundingLocations) {
            Ground ground = loc.getGround();
            if (!ground.hasCapability(EntityPassiveAbility.FIRE_RESISTANCE)) {
                loc.setGround(new Fire(ground));
            }
        }
    }

}

