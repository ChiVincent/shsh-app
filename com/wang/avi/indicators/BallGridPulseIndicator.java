package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class BallGridPulseIndicator extends Indicator {
    public static final int ALPHA = 255;
    public static final float SCALE = 1.0f;
    int[] alphas = new int[]{255, 255, 255, 255, 255, 255, 255, 255, 255};
    float[] scaleFloats = new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};

    public void draw(Canvas canvas, Paint paint) {
        float radius = (((float) getWidth()) - (4.0f * 4.0f)) / 6.0f;
        float x = ((float) (getWidth() / 2)) - ((2.0f * radius) + 4.0f);
        float y = ((float) (getWidth() / 2)) - ((2.0f * radius) + 4.0f);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.save();
                canvas.translate((((2.0f * radius) * ((float) j)) + x) + (((float) j) * 4.0f), (((2.0f * radius) * ((float) i)) + y) + (((float) i) * 4.0f));
                canvas.scale(this.scaleFloats[(i * 3) + j], this.scaleFloats[(i * 3) + j]);
                paint.setAlpha(this.alphas[(i * 3) + j]);
                canvas.drawCircle(0.0f, 0.0f, radius, paint);
                canvas.restore();
            }
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        int[] durations = new int[]{720, PointerIconCompat.TYPE_GRAB, 1280, 1420, 1450, 1180, 870, 1450, 1060};
        int[] delays = new int[]{-60, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, -170, 480, 310, 30, 460, 780, 450};
        for (int i = 0; i < 9; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(new float[]{1.0f, 0.5f, 1.0f});
            scaleAnim.setDuration((long) durations[i]);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay((long) delays[i]);
            addUpdateListener(scaleAnim, new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    BallGridPulseIndicator.this.scaleFloats[index] = ((Float) animation.getAnimatedValue()).floatValue();
                    BallGridPulseIndicator.this.postInvalidate();
                }
            });
            ValueAnimator alphaAnim = ValueAnimator.ofInt(new int[]{255, 210, 122, 255});
            alphaAnim.setDuration((long) durations[i]);
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay((long) delays[i]);
            addUpdateListener(alphaAnim, new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    BallGridPulseIndicator.this.alphas[index] = ((Integer) animation.getAnimatedValue()).intValue();
                    BallGridPulseIndicator.this.postInvalidate();
                }
            });
            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }
}
