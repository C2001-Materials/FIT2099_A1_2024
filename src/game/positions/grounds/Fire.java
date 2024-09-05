package game.positions.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityPassiveAbility;

/**
 * A class that represents a fire ground.
 * Fire will deal damage to any actor that is standing on it.
 * Fire will be extinguished after 5 turns.
 * @author Aaron Lam Kong Yew
 */
public class Fire extends Ground {

    /**
     * The initial tick of the fire.
     */
    private int age = 0;

    /**
     * The original ground stored before the fire was on.
     */
    private Ground originalGround;

    /**
     * The damage dealt to an actor standing on the fire.
     */
    private int burnDamage = 5;

    /**
     * Constructor.
     * @param originalGround the original ground before the fire was on
     */
    public Fire(Ground originalGround) {
        super('w', "Fire");
        this.originalGround = originalGround;
    }

    /**
     * Simulates a fire burning.
     * The fire will be extinguished after 5 turns.
     * The fire will deal damage to any actor standing on it.
     * @param location the location of the fire
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;
        // fire will be extinguished after 5 turns
        if (age >= 5) {
            location.setGround(originalGround);
        }

        // Check if there is an actor on this location
        Actor actor = location.getActor();
        if (actor != null && !actor.hasCapability(EntityPassiveAbility.FIRE_RESISTANCE)) {
            new Display().println(actor + " is standing on flames! " + burnDamage + " damage will be dealt.");
            actor.hurt(burnDamage);
        }
    }

    /**
     * Checks if an actor can enter the fire.
     * An actor can enter the fire if the original ground can be entered by said actor.
     * @param actor the actor to check
     * @return true if the actor can enter the fire, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return originalGround.canActorEnter(actor);
    }

}
