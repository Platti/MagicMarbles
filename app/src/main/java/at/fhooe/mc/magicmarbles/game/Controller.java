package at.fhooe.mc.magicmarbles.game;

import android.view.View;

import at.fhooe.mc.magicmarbles.game.view.MarbleButton;

/**
 * Created by Platti on 23.10.2017.
 */

public class Controller implements View.OnClickListener {
    private GameBoard model;
    private GameActivity view;

    public void setModel(GameBoard model) {
        this.model = model;
    }

    public void setView(GameActivity view) {
        this.view = view;
    }

    @Override
    public void onClick(View v) {
       model.remove(((MarbleButton) v).getMarble());
    }
}
