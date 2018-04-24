package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class CubeTransitionIndicator extends Indicator {
    float degrees;
    float scaleFloat = 1.0f;
    float[] translateX = new float[2];
    float[] translateY = new float[2];

    class C05313 implements AnimatorUpdateListener {
        C05313() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            CubeTransitionIndicator.this.scaleFloat = ((Float) animation.getAnimatedValue()).floatValue();
            CubeTransitionIndicator.this.postInvalidate();
        }
    }

    class C05324 implements AnimatorUpdateListener {
        C05324() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            CubeTransitionIndicator.this.degrees = ((Float) animation.getAnimatedValue()).floatValue();
            CubeTransitionIndicator.this.postInvalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        float rWidth = (float) (getWidth() / 5);
        float rHeight = (float) (getHeight() / 5);
        for (int i = 0; i < 2; i++) {
            canvas.save();
            canvas.translate(this.translateX[i], this.translateY[i]);
            canvas.rotate(this.degrees);
            canvas.scale(this.scaleFloat, this.scaleFloat);
            canvas.drawRect(new RectF((-rWidth) / 2.0f, (-rHeight) / 2.0f, rWidth / 2.0f, rHeight / 2.0f), paint);
            canvas.restore();
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        float startX = (float) (getWidth() / 5);
        float startY = (float) (getHeight() / 5);
        for (int i = 0; i < 2; i++) {
            final int index = i;
            this.translateX[index] = startX;
            ValueAnimator translationXAnim = ValueAnimator.ofFloat(new float[]{startX, ((float) getWidth()) - startX, ((float) getWidth()) - startX, startX, startX});
            if (i == 1) {
                translationXAnim = ValueAnimator.ofFloat(new float[]{((float) getWidth()) - startX, startX, startX, ((float) getWidth()) - startX, ((float) getWidth()) - startX});
            }
            translationXAnim.setInterpolator(new LinearInterpolator());
            translationXAnim.setDuration(1600);
            translationXAnim.setRepeatCount(-1);
            translationXAnim.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    CubeTransitionIndicator.this.translateX[index] = ((Float) animation.getAnimatedValue()).floatValue();
                    CubeTransitionIndicator.this.postInvalidate();
                }
            });
            this.translateY[index] = startY;
            ValueAnimator translationYAnim = ValueAnimator.ofFloat(new float[]{startY, startY, ((float) getHeight()) - startY, ((float) getHeight()) - startY, startY});
            if (i == 1) {
                translationYAnim = ValueAnimator.ofFloat(new float[]{((float) getHeight()) - startY, ((float) getHeight()) - startY, startY, startY, ((float) getHeight()) - startY});
            }
            translationYAnim.setDuration(1600);
            translationYAnim.setInterpolator(new LinearInterpolator());
            translationYAnim.setRepeatCount(-1);
            addUpdateListener(translationYAnim, new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    CubeTransitionIndicator.this.translateY[index] = ((Float) animation.getAnimatedValue()).floatValue();
                    CubeTransitionIndicator.this.postInvalidate();
                }
            });
            animators.add(translationXAnim);
            animators.add(translationYAnim);
        }
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(new float[]{1.0f, 0.5f, 1.0f, 0.5f, 1.0f});
        scaleAnim.setDuration(1600);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, new C05313());
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(new float[]{0.0f, 180.0f, 360.0f, 540.0f, 720.0f});
        rotateAnim.setDuration(1600);
        rotateAnim.setInterpolator(new LinearInterpolator());
        rotateAnim.setRepeatCount(-1);
        addUpdateListener(rotateAnim, new C05324());
        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }
}
