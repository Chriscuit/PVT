package com.humansignals.pvt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by christophergill on 2/21/18.
 */

public class DrawingTheRectangle extends View {

    public DrawingTheRectangle(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        /* create background of PVT display */
        Rect backRect = new Rect();
        backRect.set(0, 0, canvas.getWidth(), canvas.getHeight());

        Paint black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);

        canvas.drawRect(backRect, black);

        /* create box outline of PVT counter */
        Rect boxRect = new Rect();
        boxRect.set(canvas.getWidth()/8*3, canvas.getHeight()/16*4, canvas.getWidth()/8*5, canvas.getHeight()/16*5);

        Paint red = new Paint();
        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);

        canvas.drawRect(boxRect, red);

        /* create inside of box of PVT counter */
        Rect inRect = new Rect();
        inRect.set(canvas.getWidth()/8*3 + 5, canvas.getHeight()/16*4 + 5, canvas.getWidth()/8*5 - 5, canvas.getHeight()/16*5 - 5);

        canvas.drawRect(inRect, black);

        /* create timer text */
//        LinearLayout linearLayout = new LinearLayout(this.getContext());
//        TextView counter = new TextView(this.getContext());
//        counter.setText("lol, let's see if this works");
//        counter.setTextSize(10);
//        counter.setPadding(20, 300, 20, 100);
//
//        linearLayout.addView(counter);
    }
}
