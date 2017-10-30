package at.fhooe.mc.magicmarbles.game.view;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import at.fhooe.mc.magicmarbles.R;
import at.fhooe.mc.magicmarbles.game.GameActivity;
import at.fhooe.mc.magicmarbles.game.elements.Marble;
import at.fhooe.mc.magicmarbles.game.elements.MarbleColor;

/**
 * Created by Platti on 23.10.2017.
 */

public class MarbleButton extends ImageButton {
    private Marble marble;
    static private int size = 100;

    public Marble getMarble() {
        return marble;
    }

    public MarbleButton(Context context, Marble marble) {
        super(context);
        this.marble = marble;

        setPadding(0, 0, 0, 0);
        setScaleType(ScaleType.FIT_XY);
        setBackgroundColor(Color.WHITE);
        setOnClickListener(((GameActivity) context).getController());

        switch (marble.getColor()) {
            case RED:
                setImageResource(R.drawable.marble_red);
                break;
            case BLUE:
                setImageResource(R.drawable.marble_blue);
                break;
            case GREEN:
                setImageResource(R.drawable.marble_green);
                break;
        }

        update();
    }

    public static void setMarbleSize(int size){
        MarbleButton.size = size;
    }

    public void update() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
        params.setMargins(marble.getPosition().col * size, marble.getPosition().row * size, 0, 0);
        setLayoutParams(params);
    }
}
