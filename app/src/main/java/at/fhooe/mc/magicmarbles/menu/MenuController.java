package at.fhooe.mc.magicmarbles.menu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import at.fhooe.mc.magicmarbles.App;
import at.fhooe.mc.magicmarbles.R;
import at.fhooe.mc.magicmarbles.game.GameActivity;
import at.fhooe.mc.magicmarbles.game.Settings;

class MenuController implements View.OnClickListener {
    private Activity activity;

    MenuController(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton: {
                App app = (App) activity.getApplication();
                EditText etCols = (EditText) (activity.findViewById(R.id.numCols));
                EditText etRows = (EditText) (activity.findViewById(R.id.numRows));

                try {
                    int cols = Integer.parseInt(etCols.getText().toString());
                    int rows = Integer.parseInt(etRows.getText().toString());

                    if (cols <= 100 && rows <= 100 && cols >= 3 && rows >= 3) {
                        app.startGame(new Settings(cols, rows));

                        Intent i = new Intent(activity, GameActivity.class);
                        activity.startActivity(i);
                    } else {
                        etCols.setText("10");
                        etRows.setText("15");
                    }
                } catch (NumberFormatException e) {
                    etCols.setText("10");
                    etRows.setText("15");
                }
            }
        }
    }
}
