package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class BallClipRotateIndicator extends Indicator {
    float degrees;
    float scaleFloat = 1.0f;

    class C04981 implements AnimatorUpdateListener {
        C04981() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            BallClipRotateIndicator.this.scaleFloat = ((Float) animation.getAnimatedValue()).floatValue();
            BallClipRotateIndicator.this.postInvalidate();
        }
    }

    class C04992 implements AnimatorUpdateListener {
        C04992() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            BallClipRotateIndicator.this.degrees = ((Float) animation.getAnimatedValue()).floatValue();
            BallClipRotateIndicator.this.postInvalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(3.0f);
        float x = (float) (getWidth() / 2);
        float y = (float) (getHeight() / 2);
        canvas.translate(x, y);
        canvas.scale(this.scaleFloat, this.scaleFloat);
        canvas.rotate(this.degrees);
        canvas.drawArc(new RectF((-x) + 12.0f, (-y) + 12.0f, (0.0f + x) - 12.0f, (0.0f + y) - 12.0f), -45.0f, 270.0f, false, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(new float[]{1.0f, 0.6f, 0.5f, 1.0f});
        scaleAnim.setDuration(750);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, new C04981());
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(new float[]{0.0f, 180.0f, 360.0f});
        rotateAnim.setDuration(750);
        rotateAnim.setRepeatCount(-1);
        addUpdateListener(rotateAnim, new C04992());
        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }
}
