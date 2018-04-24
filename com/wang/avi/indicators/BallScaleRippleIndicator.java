package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;

public class BallScaleRippleIndicator extends BallScaleIndicator {

    class C05171 implements AnimatorUpdateListener {
        C05171() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            BallScaleRippleIndicator.this.scale = ((Float) animation.getAnimatedValue()).floatValue();
            BallScaleRippleIndicator.this.postInvalidate();
        }
    }

    class C05182 implements AnimatorUpdateListener {
        C05182() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            BallScaleRippleIndicator.this.alpha = ((Integer) animation.getAnimatedValue()).intValue();
            BallScaleRippleIndicator.this.postInvalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(3.0f);
        super.draw(canvas, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, new C05171());
        ValueAnimator alphaAnim = ValueAnimator.ofInt(new int[]{0, 255});
        alphaAnim.setInterpolator(new LinearInterpolator());
        alphaAnim.setDuration(1000);
        alphaAnim.setRepeatCount(-1);
        addUpdateListener(alphaAnim, new C05182());
        animators.add(scaleAnim);
        animators.add(alphaAnim);
        return animators;
    }
}
