package at.fhooe.mc.magicmarbles.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import at.fhooe.mc.magicmarbles.game.elements.Marble;
import at.fhooe.mc.magicmarbles.game.elements.MarbleFactory;

/**
 * Created by Platti on 23.10.2017.
 */

@SuppressWarnings("Since15")
public class GameBoard {
    private GameActivity view;
    private Controller controller;

    public void setView(GameActivity view) {
        this.view = view;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    // --------------------------------------------
    private Settings settings;
    private Marble[][] marbles;

    public Settings getSettings() {
        return settings;
    }

    public List<Marble> getMarbles() {
        ArrayList list = new ArrayList();
        for (Marble[] col : marbles) {
            list.addAll(Arrays.asList(col));
        }
        return list;
    }

    public GameBoard(Settings settings) {
        this.settings = settings;
        marbles = new Marble[settings.numCols][settings.numRows];
        MarbleFactory factory = new MarbleFactory(settings.numCols * settings.numRows);
        for (int col = 0; col < marbles.length; col++) {
            for (int row = 0; row < marbles[col].length; row++) {
                marbles[col][row] = factory.create();
            }
        }
        updatePositions();

    }

    private void updatePositions() {
        for (int col = 0; col < marbles.length; col++) {
            for (int row = 0; row < marbles[col].length; row++) {
                if (marbles[col][row] != null) {
                    marbles[col][row].getPosition().col = col;
                    marbles[col][row].getPosition().row = row;
                }
            }
        }
    }

    public void remove(Marble marble) {
        int col = marble.getPosition().col;
        int row = marble.getPosition().row;

        if ((get(col - 1, row).isPresent() && get(col - 1, row).get().getColor().equals(marble.getColor())) ||
                (get(col, row - 1).isPresent() && get(col, row - 1).get().getColor().equals(marble.getColor())) ||
                (get(col + 1, row).isPresent() && get(col + 1, row).get().getColor().equals(marble.getColor())) ||
                (get(col, row + 1).isPresent() && get(col, row + 1).get().getColor().equals(marble.getColor()))) {
            // at least one neighbor with same color
            removeInternal(marble, Optional.of(marble));
            moveDown();
            moveRight();
            updatePositions();
            view.update();
        }
    }

    private void removeInternal(Marble source, Optional<Marble> marble) {
        if (marble.isPresent() && marble.get().getColor().equals(source.getColor())) {
            int col = marble.get().getPosition().col;
            int row = marble.get().getPosition().row;

            marbles[col][row] = null;
            removeInternal(source, get(col - 1, row));
            removeInternal(source, get(col, row - 1));
            removeInternal(source, get(col + 1, row));
            removeInternal(source, get(col, row + 1));
            marble.get().vanish();
        }
    }

    private void moveDown() {
        int col = 0;
        while (col < marbles.length) {
            boolean moved = false;
            for (int row = marbles[col].length - 1; row > 0; row--) {
                if (!get(col, row).isPresent() && get(col, row - 1).isPresent()) {
                    marbles[col][row] = get(col, row - 1).get();
                    marbles[col][row - 1] = null;
                    moved = true;
                }
            }
            if (!moved) {
                col++;
            }
        }
    }

    private void moveRight() {
        boolean moved = true;
        while (moved) {
            moved = false;
            for (int col = marbles.length - 1; col > 0; col--) {
                boolean isEmpty = true;
                for (int row = 0; row < marbles[col].length; row++) {
                    if (get(col, row).isPresent()) {
                        isEmpty = false;
                    }
                }
                if (isEmpty) {
                    for (int row = 0; row < marbles[col].length; row++) {
                        if (get(col - 1, row).isPresent()) {
                            moved = true;
                        }
                        marbles[col][row] = get(col - 1, row).orElse(null);
                        marbles[col - 1][row] = null;
                    }
                }
            }
        }

    }


    private Optional<Marble> get(int col, int row) {
        if (col >= 0 && col < settings.numCols && row >= 0 && row < settings.numCols) {
            return Optional.ofNullable(marbles[col][row]);
        } else {
            return Optional.empty();
        }
    }
}
