package game;

import edu.monash.fit2099.engine.positions.Location;

public class FollowBehaviourUtil {
    public static boolean isAdjacent(Location a, Location b) {
        return Math.abs(a.x() - b.x()) <= 2 && Math.abs(a.y() - b.y()) <= 2; // TODO: Change this to 1
    }
}