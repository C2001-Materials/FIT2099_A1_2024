package game.positions;

import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

/**
 * A class that contains utility methods for locations calculations and logic.
 * @author Aaron Lam Kong Yew
 */
public class LocationUtils {

    /**
     * Returns true if the two locations are adjacent to each other.
     * Two locations are adjacent if the difference in x and y coordinates is less than or equal to 1.
     * @param a the first location
     * @param b the second location
     * @return true if the two locations are adjacent to each other, false otherwise
     */
    public static boolean isAdjacent(Location a, Location b) {
        return Math.abs(a.x() - b.x()) <= 1 && Math.abs(a.y() - b.y()) <= 1;
    }

    /**
     * Returns a list of locations that are adjacent to the given location.
     * Uses FIT2102's stream API concepts.
     * @param location the location to get the adjacent locations from
     * @return a list of locations that are adjacent to the given location
     */
    public static List<Location> getSurroundingLocations(Location location) {
        return location.getExits().stream()
                .map(exit -> exit.getDestination())
                .toList();
    }
}
