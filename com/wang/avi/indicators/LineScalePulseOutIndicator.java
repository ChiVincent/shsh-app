package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import java.util.ArrayList;

public class LineScalePulseOutIndicator extends LineScaleIndicator {
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList();
        long[] delays = new long[]{500, 250, 0, 250, 500};
        for (int i = 0; i < 5; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(new float[]{1.0f, 0.3f, 1.0f});
            scaleAnim.setDuration(900);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            addUpdateListener(scaleAnim, new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    LineScalePulseOutIndicator.this.scaleYFloats[index] = ((Float) animation.getAnimatedValue()).floatValue();
                    LineScalePulseOutIndicator.this.postInvalidate();
                }
            });
            animators.add(scaleAnim);
        }
        return animators;
    }
}
