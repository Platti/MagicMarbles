package at.fhooe.mc.magicmarbles.menu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import at.fhooe.mc.magicmarbles.App;
import at.fhooe.mc.magicmarbles.R;
import at.fhooe.mc.magicmarbles.game.GameActivity;
import at.fhooe.mc.magicmarbles.game.MVCContainer;
import at.fhooe.mc.magicmarbles.game.Settings;

/**
 * Created by Platti on 23.10.2017.
 */

public class MenuController implements View.OnClickListener{
    Activity activity;

    public MenuController(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startButton: {
                App app = (App) activity.getApplication();
                app.startGame(new Settings(10,10));

                Intent i = new Intent(activity, GameActivity.class);
                activity.startActivity(i);
            }
        }
    }
}
