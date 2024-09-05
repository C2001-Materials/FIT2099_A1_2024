package game.positions.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.abilites.EntityPassiveAbility;

public class Fire extends Ground {
    private int age = 0;
    private Ground originalGround;
    private int burnDamage = 5;

    public Fire(Ground originalGround) {
        super('w', "Fire");
        this.originalGround = originalGround;
    }

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

    @Override
    public boolean canActorEnter(Actor actor) {
        return originalGround.canActorEnter(actor);
    }

}
