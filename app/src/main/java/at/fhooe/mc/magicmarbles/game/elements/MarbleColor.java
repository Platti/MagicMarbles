package at.fhooe.mc.magicmarbles.game.elements;

/**
 * Created by Platti on 23.10.2017.
 */

public enum MarbleColor {
    RED(0),
    BLUE(1),
    GREEN(2);

    int id;

    MarbleColor(int id) {
        this.id = id;
    }

    public static MarbleColor get(int id) {
        for (MarbleColor marbleColor : MarbleColor.values()) {
            if (marbleColor.id == id) {
                return marbleColor;
            }
        }
        throw new IllegalArgumentException("ID not found in MarbleColor values.");
    }
}
