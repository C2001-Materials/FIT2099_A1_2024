package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class Tarnished extends Player {
    private int mana;
    private int strength;

    public Tarnished(String name, char displayChar) {
        super(name, displayChar, 150);
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(100));
        //this.addAttribute(BaseActorAttributes.STRENGTH, new BaseActorAttribute(5));
    }

//    @Override
//    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
//        display.println("Hitpoints: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH));
//        display.println("Mana: " + this.getAttribute(BaseActorAttributes.MANA) + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA));
//        //display.println("Strength: " + this.getAttribute(GameActorAttributes.STRENGTH) + "/" + this.getAttributeMaximum(GameActorAttributes.STRENGTH));
//        return super.playTurn(actions, lastAction, map, display);
//    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return super.toString() + " , "
                + "Mana: " + this.getAttribute(BaseActorAttributes.MANA) + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA) + " , ";
                //+ "Strength: " + this.getAttribute(BaseActorAttributes.STRENGTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.STRENGTH);
    }
}