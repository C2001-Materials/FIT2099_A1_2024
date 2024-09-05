package game;

import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

public class LocationUtils {
    public static boolean isAdjacent(Location a, Location b) {
        return Math.abs(a.x() - b.x()) <= 1 && Math.abs(a.y() - b.y()) <= 1; // TODO: Change this to 1
    }

    public static List<Location> getSurroundingLocations(Location location) {
        return location.getExits().stream()
                .map(exit -> exit.getDestination())
                .toList();
    }
}
