package at.fhooe.mc.magicmarbles.game.elements;

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
        position.col = -100;
        position.row = -100;
    }
}
