package at.fhooe.mc.magicmarbles;

import android.app.Application;

import at.fhooe.mc.magicmarbles.game.MVCContainer;
import at.fhooe.mc.magicmarbles.game.Settings;

/**
 * Created by Platti on 23.10.2017.
 */

public class App extends Application {
    private MVCContainer mvcContainer;

    public MVCContainer getMVCContainer(){
        return mvcContainer;
    }

    public void startGame(Settings settings){
        mvcContainer = new MVCContainer(settings);
    }

}
