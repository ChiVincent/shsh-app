package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class SemiCircleSpinIndicator extends Indicator {
    private float degress;

    class C05411 implements AnimatorUpdateListener {
        C05411() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            SemiCircleSpinIndicator.this.degress = ((Float) animation.getAnimatedValue()).floatValue();
            SemiCircleSpinIndicator.this.postInvalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.rotate(this.degress, (float) centerX(), (float) centerY());
        canvas.drawArc(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), -60.0f, 120.0f, false, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(new float[]{0.0f, 180.0f, 360.0f});
        addUpdateListener(rotateAnim, new C05411());
        rotateAnim.setDuration(600);
        rotateAnim.setRepeatCount(-1);
        animators.add(rotateAnim);
        return animators;
    }
}
