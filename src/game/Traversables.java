package game;

/**
 * This enum is used to represent the wether a player can traverse a certain type of ground.
 * Example #1: if the player is capable of traversing floors,  attach GroundCapabilities.FLOORTRAVERSABLE to the player class
 */
public enum Traversables {
    FLOORTRAVERSABLE,
    WALLTRAVERSABLE, // Add this capability in the future for spiders to traverse walls
}
