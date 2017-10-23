package at.fhooe.mc.magicmarbles.menu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import at.fhooe.mc.magicmarbles.R;

public class StartActivity extends Activity {

    MenuController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        controller = new MenuController(this);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(controller);
    }
}
