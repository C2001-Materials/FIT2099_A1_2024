package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class Tarnished extends Player {
    private int mana;
    private int strength;

    public Tarnished(String name, char displayChar) {
        super(name, displayChar, 150);
        this.mana = 100;
        this.strength = 5;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        display.println("Hitpoints: " + super.toString());
        display.println("Mana: " + this.mana);
        display.println("Strength: " + this.strength);
        return super.playTurn(actions, lastAction, map, display);
    }

    public int getStrength() {
        return strength;
    }
}