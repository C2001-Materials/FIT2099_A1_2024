package game.weapons.weaponitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.attributes.TarnishedActorAttributes;
import game.actions.AttackAction;

import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
 * @author Adrian Kristanto
 * Modified by Aaron Lam Kong Yew
 */
public abstract class WeaponItem extends Item implements Weapon {

    /** Default damage multiplier */
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;

    /** Damage of the weapon */
    private int damage;

    /** Hit rate of the weapon */
    private int hitRate;

    /** Verb to use for this weapon */
    private final String verb;

    /** Damage multiplier of the current weapon */
    private float damageMultiplier;

    /** Added: Required strength to wield the weapon */
    private int requiredStrength;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int requiredStrength) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.requiredStrength = requiredStrength;
    }

    /**
     *  Retrieves the required strength to pick up the weapon.
     * @return requiredStrength of the weapon, as an integer
     */
    public int getRequiredStrength() {
        return requiredStrength;
    }

    /**
     * Overrides the default getPickUpAction of the Item class
     * Compares the strength of the actor with the required strength of the weapon
     * @param actor the actor that wants to pick up the weapon
     * @return a PickUpAction if the actor has the required strength to pick up the weapon, null otherwise
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
            if ( actor.hasAttribute(TarnishedActorAttributes.STRENGTH)
                     && actor.getAttribute(TarnishedActorAttributes.STRENGTH) >= this.getRequiredStrength()) {
                return super.getPickUpAction(actor);
            }
        return null;
    }

    /**
     * A weapon can be used to attack an actor so, I call AttackAction!
     * @param otherActor the actor to be attacked
     * @param location the location of the actor
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(otherActor, location.toString(), this));
        return actions;
    }

    /**
     * Implements the attack method from the Weapon interface.
     * Attack the target actor with the weapon.
     * @return A string representing the result of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(Math.round(damage * damageMultiplier));

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }
}
