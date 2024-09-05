package game;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents a random puddle of water.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Aaron Lam Kong Yew
 *
 */
public class Puddle extends Ground {
    public Puddle() {
        super('~', "Puddle");
        addCapability(EntityPassiveAbility.FIRE_RESISTANCE);
    }

}
