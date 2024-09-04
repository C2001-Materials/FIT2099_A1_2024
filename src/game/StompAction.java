package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;


public class StompAction extends AttackAction{
    private Actor target;

    public StompAction(Actor target) {
        super(target, "here?", null); // TODO???
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        IntrinsicWeapon weapon = actor.getIntrinsicWeapon();

        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " stomps " + target;
    }
}