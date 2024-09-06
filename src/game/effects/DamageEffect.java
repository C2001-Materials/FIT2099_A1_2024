package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

public interface DamageEffect {
    void applyEffect(Actor actor, Location location);
    String applyEffect(Actor actor, List<Location> locations, GameMap map);
}