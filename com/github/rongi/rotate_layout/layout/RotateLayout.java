package com.github.rongi.rotate_layout.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.github.rongi.rotate_layout.C0310R;

public class RotateLayout extends ViewGroup {
    private int angle;
    private boolean angleChanged;
    private final float[] childTouchPoint;
    private final Matrix rotateMatrix;
    private final RectF tempRectF1;
    private final RectF tempRectF2;
    private final Rect viewRectRotated;
    private final float[] viewTouchPoint;

    public RotateLayout(Context context) {
        this(context, null);
    }

    public RotateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        this.rotateMatrix = new Matrix();
        this.viewRectRotated = new Rect();
        this.tempRectF1 = new RectF();
        this.tempRectF2 = new RectF();
        this.viewTouchPoint = new float[2];
        this.childTouchPoint = new float[2];
        this.angleChanged = true;
        TypedArray a = context.obtainStyledAttributes(attrs, C0310R.styleable.RotateLayout);
        this.angle = fixAngle(a.getInt(C0310R.styleable.RotateLayout_angle, 0));
        a.recycle();
        setWillNotDraw(false);
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int angle) {
        int fixedAngle = fixAngle(angle);
        if (this.angle != fixedAngle) {
            this.angle = fixedAngle;
            this.angleChanged = true;
            requestLayout();
        }
    }

    public View getView() {
        if (getChildCount() > 0) {
            return getChildAt(0);
        }
        return null;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View view = getView();
        if (view == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else if (Math.abs(this.angle % 180) == 90) {
            measureChild(view, heightMeasureSpec, widthMeasureSpec);
            setMeasuredDimension(resolveSize(view.getMeasuredHeight(), widthMeasureSpec), resolveSize(view.getMeasuredWidth(), heightMeasureSpec));
        } else {
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(resolveSize(view.getMeasuredWidth(), widthMeasureSpec), resolveSize(view.getMeasuredHeight(), heightMeasureSpec));
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (this.angleChanged || changed) {
            RectF layoutRect = this.tempRectF1;
            RectF layoutRectRotated = this.tempRectF2;
            layoutRect.set(0.0f, 0.0f, (float) (r - l), (float) (b - t));
            this.rotateMatrix.setRotate((float) this.angle, layoutRect.centerX(), layoutRect.centerY());
            this.rotateMatrix.mapRect(layoutRectRotated, layoutRect);
            layoutRectRotated.round(this.viewRectRotated);
            this.angleChanged = false;
        }
        View view = getView();
        if (view != null) {
            view.layout(this.viewRectRotated.left, this.viewRectRotated.top, this.viewRectRotated.right, this.viewRectRotated.bottom);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) (-this.angle), ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
        invalidate();
        return super.invalidateChildInParent(location, dirty);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        this.viewTouchPoint[0] = event.getX();
        this.viewTouchPoint[1] = event.getY();
        this.rotateMatrix.mapPoints(this.childTouchPoint, this.viewTouchPoint);
        event.setLocation(this.childTouchPoint[0], this.childTouchPoint[1]);
        boolean result = super.dispatchTouchEvent(event);
        event.setLocation(this.viewTouchPoint[0], this.viewTouchPoint[1]);
        return result;
    }

    private static int fixAngle(int angle) {
        return (angle / 90) * 90;
    }
}
