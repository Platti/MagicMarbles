package at.fhooe.mc.magicmarbles.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

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
        calculateMarbleSize();
        for (Marble marble : model.getMarbles()) {
            MarbleButton btn = new MarbleButton(this, marble);
            marbleButtons.add(btn);
            layout.addView(btn);
        }
    }

    private void calculateMarbleSize(){
        ViewTreeObserver viewTreeObserver = findViewById(R.id.gameBoard).getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    View view = findViewById(R.id.gameBoard);
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int maxWidth = view.getWidth() / model.getSettings().numCols;
                    int maxHeight = view.getHeight() / model.getSettings().numRows;
                    MarbleButton.setMarbleSize(maxWidth < maxHeight ? maxWidth : maxHeight);
                    model.getView().update();
                }
            });
        }
    }

    public void update(){
        for (MarbleButton marbleButton : marbleButtons) {
            marbleButton.update();
        }
    }
}
