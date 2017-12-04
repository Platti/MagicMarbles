package at.fhooe.mc.magicmarbles.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import at.fhooe.mc.magicmarbles.game.elements.Marble;
import at.fhooe.mc.magicmarbles.game.elements.MarbleFactory;


@SuppressWarnings("Since15")
class GameBoard {
    private GameActivity view;
    private List<Marble> removedMarbles;
    private int score;
    private int numMarbles;

    void setView(GameActivity view) {
        this.view = view;
    }

    GameActivity getView() {
        return view;
    }

    // --------------------------------------------
    private Settings settings;
    private Marble[][] marbles;

    Settings getSettings() {
        return settings;
    }

    List<Marble> getMarbles() {
        ArrayList list = new ArrayList();
        for (Marble[] col : marbles) {
            list.addAll(Arrays.asList(col));
        }
        return list;
    }

    int getScore(){
        return score;
    }

    int getNumMarbles(){
        return numMarbles;
    }

    GameBoard(Settings settings) {
        this.removedMarbles = new ArrayList<>();
        score = 0;
        numMarbles = settings.numCols * settings.numRows;
        this.settings = settings;
        marbles = new Marble[settings.numCols][settings.numRows];
        MarbleFactory factory = new MarbleFactory(numMarbles);
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

    void remove(Marble marble) {
        if (hasNeighborWithSameColor(marble)) {
            // at least one neighbor with same color
            removedMarbles.clear();
            removeInternal(marble, Optional.of(marble));
            countScore();
            moveDown();
            moveRight();
            updatePositions();
            view.update();
            checkGameOver();
        }
    }

    private boolean hasNeighborWithSameColor (Marble marble){
        int col = marble.getPosition().col;
        int row = marble.getPosition().row;

        return (get(col - 1, row).isPresent() && get(col - 1, row).get().getColor().equals(marble.getColor())) ||
                (get(col, row - 1).isPresent() && get(col, row - 1).get().getColor().equals(marble.getColor())) ||
                (get(col + 1, row).isPresent() && get(col + 1, row).get().getColor().equals(marble.getColor())) ||
                (get(col, row + 1).isPresent() && get(col, row + 1).get().getColor().equals(marble.getColor()));
    }

    private void countScore(){
        int numRemovedMarbles = removedMarbles.size();
        numMarbles = numMarbles - numRemovedMarbles;
        score = score + numRemovedMarbles * numRemovedMarbles;
    }

    private void checkGameOver() {
        for (Marble m: getMarbles()) {
            if(m != null && hasNeighborWithSameColor(m)){
                return;
            }
        }

        // game over
        score = score - numMarbles * 10;
        view.update();
        view.gameOver();
    }

    private void removeInternal(Marble source, Optional<Marble> marble) {
        if (marble.isPresent() && marble.get().getColor().equals(source.getColor())) {
            int col = marble.get().getPosition().col;
            int row = marble.get().getPosition().row;

            removedMarbles.add(marbles[col][row]);
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
        if (col >= 0 && col < settings.numCols && row >= 0 && row < settings.numRows) {
            return Optional.ofNullable(marbles[col][row]);
        } else {
            return Optional.empty();
        }
    }
}
