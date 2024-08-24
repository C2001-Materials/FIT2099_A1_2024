package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Tarnished extends Player {

    public Tarnished(String name, char displayChar) {
        super(name, displayChar, 150);
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(100));
        this.addAttribute(TarnishedActorAttributes.STRENGTH, new BaseActorAttribute(5));
    }

    public int getMana() {
        return this.getAttribute(BaseActorAttributes.MANA);
    }

    public int getStrength() {
        return this.getAttribute(TarnishedActorAttributes.STRENGTH);
    }

    @Override
    public String toString() {
        return super.toString() + " , "
                + "Mana: (" + this.getAttribute(BaseActorAttributes.MANA) + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA) + " ) "
                + "Strength: (" + this.getAttribute(TarnishedActorAttributes.STRENGTH) + "/" + this.getAttributeMaximum(TarnishedActorAttributes.STRENGTH) + " )";
    }
}