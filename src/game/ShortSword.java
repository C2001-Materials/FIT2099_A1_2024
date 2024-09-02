package game;

public class ShortSword extends WeaponItem {
    private final int requiredStrength;

    public ShortSword() {
        super("Short Sword", '!', 100, "slashes", 75);
        this.requiredStrength = 10;
    }

    public int getRequiredStrength() {
        return this.requiredStrength;
    }
}