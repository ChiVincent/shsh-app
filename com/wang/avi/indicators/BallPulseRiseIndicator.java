package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class BallPulseRiseIndicator extends Indicator {
    private float degress;
    private Camera mCamera = new Camera();
    private Matrix mMatrix = new Matrix();

    class C05091 implements AnimatorUpdateListener {
        C05091() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            BallPulseRiseIndicator.this.degress = ((Float) animation.getAnimatedValue()).floatValue();
            BallPulseRiseIndicator.this.postInvalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        this.mMatrix.reset();
        this.mCamera.save();
        this.mCamera.rotateX(this.degress);
        this.mCamera.getMatrix(this.mMatrix);
        this.mCamera.restore();
        this.mMatrix.preTranslate((float) (-centerX()), (float) (-centerY()));
        this.mMatrix.postTranslate((float) centerX(), (float) centerY());
        canvas.concat(this.mMatrix);
        float radius = (float) (getWidth() / 10);
        canvas.drawCircle((float) (getWidth() / 4), radius * 2.0f, radius, paint);
        canvas.drawCircle((float) ((getWidth() * 3) / 4), radius * 2.0f, radius, paint);
        canvas.drawCircle(radius, ((float) getHeight()) - (2.0f * radius), radius, paint);
        canvas.drawCircle((float) (getWidth() / 2), ((float) getHeight()) - (2.0f * radius), radius, paint);
        canvas.drawCircle(((float) getWidth()) - radius, ((float) getHeight()) - (2.0f * radius), radius, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{0.0f, 360.0f});
        addUpdateListener(animator, new C05091());
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        animator.setDuration(1500);
        animators.add(animator);
        return animators;
    }
}
