package at.fhooe.mc.magicmarbles.game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import at.fhooe.mc.magicmarbles.App;
import at.fhooe.mc.magicmarbles.R;
import at.fhooe.mc.magicmarbles.game.elements.Marble;
import at.fhooe.mc.magicmarbles.game.view.MarbleButton;

public class GameActivity extends Activity {
    private GameBoard model;

    private Controller controller;
    private List<MarbleButton> marbleButtons;

    public void setModel(GameBoard model){
        this.model = model;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ((App) getApplication()).getMVCContainer().setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        marbleButtons = new ArrayList<>();
        FrameLayout layout = (FrameLayout) findViewById(R.id.gameBoard);
        for (Marble marble : model.getMarbles()) {
            MarbleButton btn = new MarbleButton(this, marble);
            marbleButtons.add(btn);
            layout.addView(btn);
        }
    }

    public void update(){
        for (MarbleButton marbleButton : marbleButtons) {
            marbleButton.update();
        }
    }
}
