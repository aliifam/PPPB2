package com.aliif.week8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DialView extends View {

    private int SELECTION_COUNT = 4;
    private float width;
    private float height;
    private float radius;

    private Paint dialPaint;
    private Paint textPaint;

    private int activeSelection;
    private final StringBuffer tempLabel = new StringBuffer(8);
    private final float[] tempResult = new float[2];


    private void init() {
       textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       textPaint.setColor(Color.BLACK);
       textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
       textPaint.setTextAlign(Paint.Align.CENTER);
       textPaint.setTextSize(height / 5);

       dialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       dialPaint.setColor(Color.GRAY);

       setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               activeSelection = (activeSelection + 1) % SELECTION_COUNT;
               if (activeSelection >= 1) {
                   dialPaint.setColor(Color.GREEN);
               } else {
                   dialPaint.setColor(Color.GRAY);
               }

               invalidate();
           }
       });
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        radius = (float) (Math.min(width, height) / 2 * 0.8);
    }

    private float[] computeXYForPosition(final int pos, final float radius) {
        float[] result = tempResult;
        Double startAngle;
        Double angle;
        startAngle = Math.PI * (9 / 8d);
        angle = startAngle + (pos * (Math.PI / 4));
        result[0] = (float) (radius * Math.cos(angle)) + (width / 2);
        result[1] = (float) (radius * Math.sin(angle)) + (height / 2);
        return result;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(width / 2, height / 2, radius, dialPaint);

        final float labelRadius = radius + 20;
        StringBuffer label = tempLabel;
        for (int i = 0; i < SELECTION_COUNT; i++) {
            float[] xyData = computeXYForPosition(i, labelRadius);
            String labelString = Integer.toString(i);
            canvas.drawText(labelString, xyData[0], xyData[1], textPaint);
        }

        float markerRadius = radius - 35;
        float[] xyData = computeXYForPosition(activeSelection, markerRadius);
        canvas.drawCircle(xyData[0], xyData[1], 20, textPaint);
    }

    public DialView(Context context) {
        super(context);
        init();
    }

    public DialView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DialView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
