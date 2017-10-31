package at.fhooe.mc.magicmarbles.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

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
                    MarbleButton.fitSize(view, model.getSettings());
                    model.getView().update();
                }
            });
        }
    }

    public void update(){
        for (MarbleButton marbleButton : marbleButtons) {
            marbleButton.update();
        }
        ((TextView)(findViewById(R.id.marblesLeft))).setText(String.valueOf(model.getNumMarbles()));
        ((TextView)(findViewById(R.id.score))).setText(String.valueOf(model.getScore()));
    }

    public void gameOver() {
        AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
        alertDialog.setTitle("Game Over!");
        alertDialog.setMessage("Congratulations! You achieved " + model.getScore() + " points.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Return to menu",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        GameActivity.this.finish();
                    }
                });
        alertDialog.show();
    }
}
