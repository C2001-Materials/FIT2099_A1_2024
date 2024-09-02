package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class Tarnished extends Player{
    public Tarnished(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(100));
        this.addAttribute(TarnishedActorAttributes.STRENGTH, new BaseActorAttribute(5));
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        display.println(this.toString());
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String toString() {
        return super.toString() + " , "
                + "Mana: (" + this.getAttribute(BaseActorAttributes.MANA) + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA) + " ) "
                + "Strength: (" + this.getAttribute(TarnishedActorAttributes.STRENGTH) + "/" + this.getAttributeMaximum(TarnishedActorAttributes.STRENGTH) + " )";
    }

}