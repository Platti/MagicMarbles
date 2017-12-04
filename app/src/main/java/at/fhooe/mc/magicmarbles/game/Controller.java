package at.fhooe.mc.magicmarbles.game;

import android.view.View;

import at.fhooe.mc.magicmarbles.game.view.MarbleButton;

class Controller implements View.OnClickListener {
    private GameBoard model;

    void setModel(GameBoard model) {
        this.model = model;
    }

    @Override
    public void onClick(View v) {
       model.remove(((MarbleButton) v).getMarble());
    }
}
