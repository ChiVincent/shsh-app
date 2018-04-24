package com.wang.avi;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.wang.avi.indicators.BallPulseIndicator;

public class AVLoadingIndicatorView extends View {
    private static final BallPulseIndicator DEFAULT_INDICATOR = new BallPulseIndicator();
    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;
    private static final String TAG = "AVLoadingIndicatorView";
    private final Runnable mDelayedHide = new C04931();
    private final Runnable mDelayedShow = new C04942();
    private boolean mDismissed = false;
    private Indicator mIndicator;
    private int mIndicatorColor;
    int mMaxHeight;
    int mMaxWidth;
    int mMinHeight;
    int mMinWidth;
    private boolean mPostedHide = false;
    private boolean mPostedShow = false;
    private boolean mShouldStartAnimationDrawable;
    private long mStartTime = -1;

    class C04931 implements Runnable {
        C04931() {
        }

        public void run() {
            AVLoadingIndicatorView.this.mPostedHide = false;
            AVLoadingIndicatorView.this.mStartTime = -1;
            AVLoadingIndicatorView.this.setVisibility(8);
        }
    }

    class C04942 implements Runnable {
        C04942() {
        }

        public void run() {
            AVLoadingIndicatorView.this.mPostedShow = false;
            if (!AVLoadingIndicatorView.this.mDismissed) {
                AVLoadingIndicatorView.this.mStartTime = System.currentTimeMillis();
                AVLoadingIndicatorView.this.setVisibility(0);
            }
        }
    }

    public AVLoadingIndicatorView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, C0495R.style.AVLoadingIndicatorView);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, C0495R.style.AVLoadingIndicatorView);
    }

    @TargetApi(21)
    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, C0495R.style.AVLoadingIndicatorView);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.mMinWidth = 24;
        this.mMaxWidth = 48;
        this.mMinHeight = 24;
        this.mMaxHeight = 48;
        TypedArray a = context.obtainStyledAttributes(attrs, C0495R.styleable.AVLoadingIndicatorView, defStyleAttr, defStyleRes);
        this.mMinWidth = a.getDimensionPixelSize(C0495R.styleable.AVLoadingIndicatorView_minWidth, this.mMinWidth);
        this.mMaxWidth = a.getDimensionPixelSize(C0495R.styleable.AVLoadingIndicatorView_maxWidth, this.mMaxWidth);
        this.mMinHeight = a.getDimensionPixelSize(C0495R.styleable.AVLoadingIndicatorView_minHeight, this.mMinHeight);
        this.mMaxHeight = a.getDimensionPixelSize(C0495R.styleable.AVLoadingIndicatorView_maxHeight, this.mMaxHeight);
        String indicatorName = a.getString(C0495R.styleable.AVLoadingIndicatorView_indicatorName);
        this.mIndicatorColor = a.getColor(C0495R.styleable.AVLoadingIndicatorView_indicatorColor, -1);
        setIndicator(indicatorName);
        if (this.mIndicator == null) {
            setIndicator(DEFAULT_INDICATOR);
        }
        a.recycle();
    }

    public Indicator getIndicator() {
        return this.mIndicator;
    }

    public void setIndicator(Indicator d) {
        if (this.mIndicator != d) {
            if (this.mIndicator != null) {
                this.mIndicator.setCallback(null);
                unscheduleDrawable(this.mIndicator);
            }
            this.mIndicator = d;
            setIndicatorColor(this.mIndicatorColor);
            if (d != null) {
                d.setCallback(this);
            }
            postInvalidate();
        }
    }

    public void setIndicatorColor(int color) {
        this.mIndicatorColor = color;
        this.mIndicator.setColor(color);
    }

    public void setIndicator(String indicatorName) {
        if (!TextUtils.isEmpty(indicatorName)) {
            StringBuilder drawableClassName = new StringBuilder();
            if (!indicatorName.contains(".")) {
                drawableClassName.append(getClass().getPackage().getName()).append(".indicators").append(".");
            }
            drawableClassName.append(indicatorName);
            try {
                setIndicator((Indicator) Class.forName(drawableClassName.toString()).newInstance());
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "Didn't find your class , check the name again !");
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void smoothToShow() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
        setVisibility(0);
    }

    public void smoothToHide() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
        setVisibility(8);
    }

    public void hide() {
        this.mDismissed = true;
        removeCallbacks(this.mDelayedShow);
        long diff = System.currentTimeMillis() - this.mStartTime;
        if (diff >= 500 || this.mStartTime == -1) {
            setVisibility(8);
        } else if (!this.mPostedHide) {
            postDelayed(this.mDelayedHide, 500 - diff);
            this.mPostedHide = true;
        }
    }

    public void show() {
        this.mStartTime = -1;
        this.mDismissed = false;
        removeCallbacks(this.mDelayedHide);
        if (!this.mPostedShow) {
            postDelayed(this.mDelayedShow, 500);
            this.mPostedShow = true;
        }
    }

    protected boolean verifyDrawable(Drawable who) {
        return who == this.mIndicator || super.verifyDrawable(who);
    }

    void startAnimation() {
        if (getVisibility() == 0) {
            if (this.mIndicator instanceof Animatable) {
                this.mShouldStartAnimationDrawable = true;
            }
            postInvalidate();
        }
    }

    void stopAnimation() {
        if (this.mIndicator instanceof Animatable) {
            this.mIndicator.stop();
            this.mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    public void setVisibility(int v) {
        if (getVisibility() != v) {
            super.setVisibility(v);
            if (v == 8 || v == 4) {
                stopAnimation();
            } else {
                startAnimation();
            }
        }
    }

    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == 8 || visibility == 4) {
            stopAnimation();
        } else {
            startAnimation();
        }
    }

    public void invalidateDrawable(Drawable dr) {
        if (verifyDrawable(dr)) {
            Rect dirty = dr.getBounds();
            int scrollX = getScrollX() + getPaddingLeft();
            int scrollY = getScrollY() + getPaddingTop();
            invalidate(dirty.left + scrollX, dirty.top + scrollY, dirty.right + scrollX, dirty.bottom + scrollY);
            return;
        }
        super.invalidateDrawable(dr);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        updateDrawableBounds(w, h);
    }

    private void updateDrawableBounds(int w, int h) {
        w -= getPaddingRight() + getPaddingLeft();
        h -= getPaddingTop() + getPaddingBottom();
        int right = w;
        int bottom = h;
        int top = 0;
        int left = 0;
        if (this.mIndicator != null) {
            float intrinsicAspect = ((float) this.mIndicator.getIntrinsicWidth()) / ((float) this.mIndicator.getIntrinsicHeight());
            float boundAspect = ((float) w) / ((float) h);
            if (intrinsicAspect != boundAspect) {
                if (boundAspect > intrinsicAspect) {
                    int width = (int) (((float) h) * intrinsicAspect);
                    left = (w - width) / 2;
                    right = left + width;
                } else {
                    int height = (int) (((float) w) * (1.0f / intrinsicAspect));
                    top = (h - height) / 2;
                    bottom = top + height;
                }
            }
            this.mIndicator.setBounds(left, top, right, bottom);
        }
    }

    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTrack(canvas);
    }

    void drawTrack(Canvas canvas) {
        Drawable d = this.mIndicator;
        if (d != null) {
            int saveCount = canvas.save();
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            d.draw(canvas);
            canvas.restoreToCount(saveCount);
            if (this.mShouldStartAnimationDrawable && (d instanceof Animatable)) {
                ((Animatable) d).start();
                this.mShouldStartAnimationDrawable = false;
            }
        }
    }

    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int dw = 0;
        int dh = 0;
        Drawable d = this.mIndicator;
        if (d != null) {
            dw = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, d.getIntrinsicWidth()));
            dh = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, d.getIntrinsicHeight()));
        }
        updateDrawableState();
        setMeasuredDimension(resolveSizeAndState(dw + (getPaddingLeft() + getPaddingRight()), widthMeasureSpec, 0), resolveSizeAndState(dh + (getPaddingTop() + getPaddingBottom()), heightMeasureSpec, 0));
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    private void updateDrawableState() {
        int[] state = getDrawableState();
        if (this.mIndicator != null && this.mIndicator.isStateful()) {
            this.mIndicator.setState(state);
        }
    }

    @TargetApi(21)
    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
        if (this.mIndicator != null) {
            this.mIndicator.setHotspot(x, y);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
        removeCallbacks();
    }

    protected void onDetachedFromWindow() {
        stopAnimation();
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    private void removeCallbacks() {
        removeCallbacks(this.mDelayedHide);
        removeCallbacks(this.mDelayedShow);
    }
}
