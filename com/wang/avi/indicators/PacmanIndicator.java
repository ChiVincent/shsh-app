package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class PacmanIndicator extends Indicator {
    private int alpha;
    private float degrees1;
    private float degrees2;
    private float translateX;

    class C05371 implements AnimatorUpdateListener {
        C05371() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PacmanIndicator.this.translateX = ((Float) animation.getAnimatedValue()).floatValue();
            PacmanIndicator.this.postInvalidate();
        }
    }

    class C05382 implements AnimatorUpdateListener {
        C05382() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PacmanIndicator.this.alpha = ((Integer) animation.getAnimatedValue()).intValue();
            PacmanIndicator.this.postInvalidate();
        }
    }

    class C05393 implements AnimatorUpdateListener {
        C05393() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PacmanIndicator.this.degrees1 = ((Float) animation.getAnimatedValue()).floatValue();
            PacmanIndicator.this.postInvalidate();
        }
    }

    class C05404 implements AnimatorUpdateListener {
        C05404() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PacmanIndicator.this.degrees2 = ((Float) animation.getAnimatedValue()).floatValue();
            PacmanIndicator.this.postInvalidate();
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        drawPacman(canvas, paint);
        drawCircle(canvas, paint);
    }

    private void drawPacman(Canvas canvas, Paint paint) {
        float x = (float) (getWidth() / 2);
        float y = (float) (getHeight() / 2);
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(this.degrees1);
        paint.setAlpha(255);
        canvas.drawArc(new RectF((-x) / 1.7f, (-y) / 1.7f, x / 1.7f, y / 1.7f), 0.0f, 270.0f, true, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(this.degrees2);
        paint.setAlpha(255);
        canvas.drawArc(new RectF((-x) / 1.7f, (-y) / 1.7f, x / 1.7f, y / 1.7f), 90.0f, 270.0f, true, paint);
        canvas.restore();
    }

    private void drawCircle(Canvas canvas, Paint paint) {
        float radius = (float) (getWidth() / 11);
        paint.setAlpha(this.alpha);
        canvas.drawCircle(this.translateX, (float) (getHeight() / 2), radius, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        float startT = (float) (getWidth() / 11);
        ValueAnimator translationAnim = ValueAnimator.ofFloat(new float[]{((float) getWidth()) - startT, (float) (getWidth() / 2)});
        translationAnim.setDuration(650);
        translationAnim.setInterpolator(new LinearInterpolator());
        translationAnim.setRepeatCount(-1);
        addUpdateListener(translationAnim, new C05371());
        ValueAnimator alphaAnim = ValueAnimator.ofInt(new int[]{255, 122});
        alphaAnim.setDuration(650);
        alphaAnim.setRepeatCount(-1);
        addUpdateListener(alphaAnim, new C05382());
        ValueAnimator rotateAnim1 = ValueAnimator.ofFloat(new float[]{0.0f, 45.0f, 0.0f});
        rotateAnim1.setDuration(650);
        rotateAnim1.setRepeatCount(-1);
        addUpdateListener(rotateAnim1, new C05393());
        ValueAnimator rotateAnim2 = ValueAnimator.ofFloat(new float[]{0.0f, -45.0f, 0.0f});
        rotateAnim2.setDuration(650);
        rotateAnim2.setRepeatCount(-1);
        addUpdateListener(rotateAnim2, new C05404());
        animators.add(translationAnim);
        animators.add(alphaAnim);
        animators.add(rotateAnim1);
        animators.add(rotateAnim2);
        return animators;
    }
}
