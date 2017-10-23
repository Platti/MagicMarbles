package at.fhooe.mc.magicmarbles.game.elements;

/**
 * Created by Platti on 23.10.2017.
 */

public class MarbleFactory {
    private int total;
    private int[] used;

    public MarbleFactory(int total){
        this.total = total;
        used = new int[MarbleColor.values().length];
    }

    public Marble create() {
        while (true) {
            int random = (int) (Math.random() * MarbleColor.values().length);
            if (used[random] < total / 3 + 1) {
                used[random]++;
                return new Marble(MarbleColor.get(random));
            }
        }
    }
}
