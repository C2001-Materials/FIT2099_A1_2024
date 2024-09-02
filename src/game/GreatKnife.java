package game;

public class GreatKnife extends WeaponItem {
    private final int requiredStrength;

    public GreatKnife() {
        super("Great Knife", '†', 75, "stabs", 60);
        this.requiredStrength = 5;
    }

    public int getRequiredStrength() {
        return this.requiredStrength;
    }
}