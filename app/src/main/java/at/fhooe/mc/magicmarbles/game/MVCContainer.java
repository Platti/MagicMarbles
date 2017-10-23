package at.fhooe.mc.magicmarbles.game;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Platti on 23.10.2017.
 */

public class MVCContainer {
    private GameBoard model;
    private GameActivity view;
    private Controller controller;

    public MVCContainer(Settings settings){
        model = new GameBoard(settings);
        controller = new Controller();
        model.setController(controller);
        controller.setModel(model);
    }

    public void setView(GameActivity activity){
        this.view = activity;
        model.setView(view);
        view.setModel(model);
        view.setController(controller);
        controller.setView(view);
    }
}
