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
    private static final int BTN_SIZE = 105;

    private Marble marble;

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

    public void update() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(BTN_SIZE, BTN_SIZE);
        params.setMargins(marble.getPosition().col * BTN_SIZE, marble.getPosition().row * BTN_SIZE, 0, 0);
        setLayoutParams(params);
    }
}
