package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
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
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        display.println("Hitpoints: (" + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ")");
        display.println("Mana: (" + this.getAttribute(BaseActorAttributes.MANA) + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA) + ")");
        display.println("Strength: (" + this.getAttribute(TarnishedActorAttributes.STRENGTH) + "/" + this.getAttributeMaximum(TarnishedActorAttributes.STRENGTH) + ")");

        return super.playTurn(actions, lastAction, map, display);
    }



    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        // Add actions for picking up and dropping weapons
        for (Item item : map.locationOf(this).getItems()) {

                actions.add(item.getPickUpAction(this));

            {
                actions.add(new  PickUpAction(item));
            }
        }

        for (Item item : this.getItemInventory()) {

                actions.add(item.getDropAction(this));
            }

        return actions;
    }

    private boolean canPickUpWeapon(WeaponItem weapon) {
        int requiredStrength = 0;
        if (weapon instanceof ShortSword) {
            requiredStrength = 10;
        } else if (weapon instanceof GreatKnife) {
            requiredStrength = 5;
        }

        return this.getAttribute(TarnishedActorAttributes.STRENGTH) >= requiredStrength;
    }

    public void pickUpWeapon(WeaponItem weapon) {
        addItemToInventory(weapon);
    }

    public void dropWeapon(WeaponItem weapon) {
        removeItemFromInventory(weapon);
    }


    @Override
    public String toString() {
        return super.toString() + " , "
                + "Mana: (" + this.getAttribute(BaseActorAttributes.MANA) + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA) + " ) "
                + "Strength: (" + this.getAttribute(TarnishedActorAttributes.STRENGTH) + "/" + this.getAttributeMaximum(TarnishedActorAttributes.STRENGTH) + " )";
    }
}