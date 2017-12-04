package at.fhooe.mc.magicmarbles.game.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import at.fhooe.mc.magicmarbles.R;
import at.fhooe.mc.magicmarbles.game.GameActivity;
import at.fhooe.mc.magicmarbles.game.Settings;
import at.fhooe.mc.magicmarbles.game.elements.Marble;

public class MarbleButton extends ImageButton {
    private Marble marble;
    static private int size = 100;
    static private int marginLeft = 0;
    static private int marginTop = 0;

    public Marble getMarble() {
        return marble;
    }

    public MarbleButton(Context context, Marble marble) {
        super(context);
        this.marble = marble;

        setPadding(0, 0, 0, 0);
        setScaleType(ScaleType.FIT_XY);
        setBackgroundColor(Color.TRANSPARENT);
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
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
        params.setMargins(marble.getPosition().col * size + marginLeft, marble.getPosition().row * size + marginTop, 0, 0);
        setLayoutParams(params);
    }

    public static void fitSize(View view, Settings settings) {
        int maxWidth = view.getWidth() / settings.numCols;
        int maxHeight = view.getHeight() / settings.numRows;

        MarbleButton.size = maxWidth < maxHeight ? maxWidth : maxHeight;
        MarbleButton.marginLeft = (view.getWidth() - size * settings.numCols ) / 2;
        MarbleButton.marginTop = (view.getHeight() - size * settings.numRows ) / 2;
    }
}
