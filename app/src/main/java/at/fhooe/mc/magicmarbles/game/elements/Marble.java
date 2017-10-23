package at.fhooe.mc.magicmarbles.game.elements;

/**
 * Created by Platti on 23.10.2017.
 */

public class Marble {
    private MarbleColor color;
    private Position position;

    public Marble(MarbleColor color) {
        this.color = color;
        this.position = new Position(0,0);
    }

    public MarbleColor getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void vanish(){
        position.col = -1;
        position.row = -1;
    }
}
