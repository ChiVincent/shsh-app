package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class BallRotateIndicator extends Indicator {
    float degress;
    private Matrix mMatrix = new Matrix();
    float scaleFloat = 0.5f;

    class C05111 implements AnimatorUpdateListener {
        C05111() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            BallRotateIndicator.this.scaleFloat = ((Float) animation.getAnimatedValue()).floatValue();
            BallRotateIndicator.this.postInvalidate();
        }
    }

    class C05122 implements AnimatorUpdateListener {
        C05122() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            BallRotateIndicator.this.degress = ((Float) animation.getAnimatedValue()).floatValue();
            BallRotateIndicator.this.postInvalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        float radius = (float) (getWidth() / 10);
        float x = (float) (getWidth() / 2);
        float y = (float) (getHeight() / 2);
        canvas.rotate(this.degress, (float) centerX(), (float) centerY());
        canvas.save();
        canvas.translate((x - (radius * 2.0f)) - radius, y);
        canvas.scale(this.scaleFloat, this.scaleFloat);
        canvas.drawCircle(0.0f, 0.0f, radius, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(this.scaleFloat, this.scaleFloat);
        canvas.drawCircle(0.0f, 0.0f, radius, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(((radius * 2.0f) + x) + radius, y);
        canvas.scale(this.scaleFloat, this.scaleFloat);
        canvas.drawCircle(0.0f, 0.0f, radius, paint);
        canvas.restore();
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(new float[]{0.5f, 1.0f, 0.5f});
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, new C05111());
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(new float[]{0.0f, 180.0f, 360.0f});
        addUpdateListener(rotateAnim, new C05122());
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(-1);
        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }
}
