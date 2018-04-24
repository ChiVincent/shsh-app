package com.wang.avi;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Indicator extends Drawable implements Animatable {
    private static final Rect ZERO_BOUNDS_RECT = new Rect();
    private int alpha = 255;
    protected Rect drawBounds = ZERO_BOUNDS_RECT;
    private ArrayList<ValueAnimator> mAnimators;
    private boolean mHasAnimators;
    private Paint mPaint = new Paint();
    private HashMap<ValueAnimator, AnimatorUpdateListener> mUpdateListeners = new HashMap();

    public abstract void draw(Canvas canvas, Paint paint);

    public abstract ArrayList<ValueAnimator> onCreateAnimators();

    public Indicator() {
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setAntiAlias(true);
    }

    public int getColor() {
        return this.mPaint.getColor();
    }

    public void setColor(int color) {
        this.mPaint.setColor(color);
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public int getOpacity() {
        return -1;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void draw(Canvas canvas) {
        draw(canvas, this.mPaint);
    }

    public void start() {
        ensureAnimators();
        if (this.mAnimators != null && !isStarted()) {
            startAnimators();
            invalidateSelf();
        }
    }

    private void startAnimators() {
        for (int i = 0; i < this.mAnimators.size(); i++) {
            ValueAnimator animator = (ValueAnimator) this.mAnimators.get(i);
            AnimatorUpdateListener updateListener = (AnimatorUpdateListener) this.mUpdateListeners.get(animator);
            if (updateListener != null) {
                animator.addUpdateListener(updateListener);
            }
            animator.start();
        }
    }

    private void stopAnimators() {
        if (this.mAnimators != null) {
            Iterator it = this.mAnimators.iterator();
            while (it.hasNext()) {
                ValueAnimator animator = (ValueAnimator) it.next();
                if (animator != null && animator.isStarted()) {
                    animator.removeAllUpdateListeners();
                    animator.end();
                }
            }
        }
    }

    private void ensureAnimators() {
        if (!this.mHasAnimators) {
            this.mAnimators = onCreateAnimators();
            this.mHasAnimators = true;
        }
    }

    public void stop() {
        stopAnimators();
    }

    private boolean isStarted() {
        Iterator it = this.mAnimators.iterator();
        if (it.hasNext()) {
            return ((ValueAnimator) it.next()).isStarted();
        }
        return false;
    }

    public boolean isRunning() {
        Iterator it = this.mAnimators.iterator();
        if (it.hasNext()) {
            return ((ValueAnimator) it.next()).isRunning();
        }
        return false;
    }

    public void addUpdateListener(ValueAnimator animator, AnimatorUpdateListener updateListener) {
        this.mUpdateListeners.put(animator, updateListener);
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        setDrawBounds(bounds);
    }

    public void setDrawBounds(Rect drawBounds) {
        setDrawBounds(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.bottom);
    }

    public void setDrawBounds(int left, int top, int right, int bottom) {
        this.drawBounds = new Rect(left, top, right, bottom);
    }

    public void postInvalidate() {
        invalidateSelf();
    }

    public Rect getDrawBounds() {
        return this.drawBounds;
    }

    public int getWidth() {
        return this.drawBounds.width();
    }

    public int getHeight() {
        return this.drawBounds.height();
    }

    public int centerX() {
        return this.drawBounds.centerX();
    }

    public int centerY() {
        return this.drawBounds.centerY();
    }

    public float exactCenterX() {
        return this.drawBounds.exactCenterX();
    }

    public float exactCenterY() {
        return this.drawBounds.exactCenterY();
    }
}
