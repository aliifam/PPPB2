package com.aliif.week11;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class PulseAnimationView extends View {

    private float radius;
    private final Paint paint = new Paint();
    private static final int COLOR_ADJUSTER = 5;
    private float x;
    private float y;

    private static final int ANIMATION_DURATION = 4000;
    private static final int ANIMATION_DELAY = 1000;

    private AnimatorSet animatorSet = new AnimatorSet();

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, radius, paint);
    }

    public void setRadius(float radius) {
        this.radius = radius;
        paint.setColor(Color.GREEN + (int) radius / COLOR_ADJUSTER);
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            x = event.getX();
            y = event.getY();
        }

        if(animatorSet != null && animatorSet.isRunning())
        {
            animatorSet.cancel();
        }

        animatorSet.start();

        return super.onTouchEvent(event);
    }

    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight)
    {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this, "radius", 0, getWidth());
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this, "radius", getWidth(), 0);
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        ObjectAnimator repeatAnimator = ObjectAnimator.ofFloat(this, "radius", 0, getWidth());
        repeatAnimator.setStartDelay(ANIMATION_DELAY);
        repeatAnimator.setDuration(ANIMATION_DURATION);

        animatorSet.play(growAnimator).before(shrinkAnimator);
        animatorSet.play(repeatAnimator).after(shrinkAnimator);
    }

    public PulseAnimationView(Context context) {
        super(context);
    }

    public PulseAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PulseAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PulseAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
