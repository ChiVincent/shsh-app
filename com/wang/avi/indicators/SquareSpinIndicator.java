package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class SquareSpinIndicator extends Indicator {
    private Camera mCamera = new Camera();
    private Matrix mMatrix = new Matrix();
    private float rotateX;
    private float rotateY;

    class C05421 implements AnimatorUpdateListener {
        C05421() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            SquareSpinIndicator.this.rotateX = ((Float) animation.getAnimatedValue()).floatValue();
            SquareSpinIndicator.this.postInvalidate();
        }
    }

    class C05432 implements AnimatorUpdateListener {
        C05432() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            SquareSpinIndicator.this.rotateY = ((Float) animation.getAnimatedValue()).floatValue();
            SquareSpinIndicator.this.postInvalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        this.mMatrix.reset();
        this.mCamera.save();
        this.mCamera.rotateX(this.rotateX);
        this.mCamera.rotateY(this.rotateY);
        this.mCamera.getMatrix(this.mMatrix);
        this.mCamera.restore();
        this.mMatrix.preTranslate((float) (-centerX()), (float) (-centerY()));
        this.mMatrix.postTranslate((float) centerX(), (float) centerY());
        canvas.concat(this.mMatrix);
        canvas.drawRect(new RectF((float) (getWidth() / 5), (float) (getHeight() / 5), (float) ((getWidth() * 4) / 5), (float) ((getHeight() * 4) / 5)), paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{0.0f, 180.0f, 180.0f, 0.0f, 0.0f});
        addUpdateListener(animator, new C05421());
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        animator.setDuration(2500);
        ValueAnimator animator1 = ValueAnimator.ofFloat(new float[]{0.0f, 0.0f, 180.0f, 180.0f, 0.0f});
        addUpdateListener(animator1, new C05432());
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setRepeatCount(-1);
        animator1.setDuration(2500);
        animators.add(animator);
        animators.add(animator1);
        return animators;
    }
}
