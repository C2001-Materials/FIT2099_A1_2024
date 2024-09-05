package game;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a heavy entity's feet, that can be used to stomp on other actors.
 * @author Aaron Lam Kong Yew
 */
public class Feet  extends IntrinsicWeapon {

    public Feet() {
        super(100, "stomps", 5);

    }
}
