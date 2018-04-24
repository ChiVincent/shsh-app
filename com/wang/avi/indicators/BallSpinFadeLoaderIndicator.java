package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.wang.avi.Indicator;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;

public class BallSpinFadeLoaderIndicator extends Indicator {
    public static final int ALPHA = 255;
    public static final float SCALE = 1.0f;
    int[] alphas = new int[]{255, 255, 255, 255, 255, 255, 255, 255};
    float[] scaleFloats = new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};

    final class Point {
        public float f26x;
        public float f27y;

        public Point(float x, float y) {
            this.f26x = x;
            this.f27y = y;
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        float radius = (float) (getWidth() / 10);
        for (int i = 0; i < 8; i++) {
            canvas.save();
            Point point = circleAt(getWidth(), getHeight(), ((float) (getWidth() / 2)) - radius, ((double) i) * 0.7853981633974483d);
            canvas.translate(point.f26x, point.f27y);
            canvas.scale(this.scaleFloats[i], this.scaleFloats[i]);
            paint.setAlpha(this.alphas[i]);
            canvas.drawCircle(0.0f, 0.0f, radius, paint);
            canvas.restore();
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        int[] delays = new int[]{0, 120, 240, 360, 480, SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT, 720, 780, 840};
        for (int i = 0; i < 8; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(new float[]{1.0f, 0.4f, 1.0f});
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay((long) delays[i]);
            addUpdateListener(scaleAnim, new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    BallSpinFadeLoaderIndicator.this.scaleFloats[index] = ((Float) animation.getAnimatedValue()).floatValue();
                    BallSpinFadeLoaderIndicator.this.postInvalidate();
                }
            });
            ValueAnimator alphaAnim = ValueAnimator.ofInt(new int[]{255, 77, 255});
            alphaAnim.setDuration(1000);
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay((long) delays[i]);
            addUpdateListener(alphaAnim, new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    BallSpinFadeLoaderIndicator.this.alphas[index] = ((Integer) animation.getAnimatedValue()).intValue();
                    BallSpinFadeLoaderIndicator.this.postInvalidate();
                }
            });
            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }

    Point circleAt(int width, int height, float radius, double angle) {
        return new Point((float) (((double) (width / 2)) + (((double) radius) * Math.cos(angle))), (float) (((double) (height / 2)) + (((double) radius) * Math.sin(angle))));
    }
}
