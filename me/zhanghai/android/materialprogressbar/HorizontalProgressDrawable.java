package me.zhanghai.android.materialprogressbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.util.Log;
import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

public class HorizontalProgressDrawable extends LayerDrawable implements IntrinsicPaddingDrawable, ShowBackgroundDrawable, TintableDrawable {
    private static final String TAG = HorizontalProgressDrawable.class.getSimpleName();
    private float mBackgroundAlpha;
    private HorizontalProgressBackgroundDrawable mBackgroundDrawable = ((HorizontalProgressBackgroundDrawable) getDrawable(0));
    private boolean mHasSecondaryProgressTint;
    private boolean mHasSecondaryProgressTintColor;
    private SingleHorizontalProgressDrawable mProgressDrawable;
    private SingleHorizontalProgressDrawable mSecondaryProgressDrawable;
    private ColorStateList mSecondaryProgressTint;
    private int mSecondaryProgressTintColor;

    public HorizontalProgressDrawable(Context context) {
        super(new Drawable[]{new HorizontalProgressBackgroundDrawable(context), new SingleHorizontalProgressDrawable(context), new SingleHorizontalProgressDrawable(context)});
        this.mBackgroundAlpha = ThemeUtils.getFloatFromAttrRes(16842803, context);
        setId(0, 16908288);
        setId(1, 16908303);
        this.mSecondaryProgressDrawable = (SingleHorizontalProgressDrawable) getDrawable(1);
        setId(2, 16908301);
        this.mProgressDrawable = (SingleHorizontalProgressDrawable) getDrawable(2);
        setTint(ThemeUtils.getColorFromAttrRes(C0567R.attr.colorControlActivated, context));
    }

    public boolean getShowBackground() {
        return this.mBackgroundDrawable.getShowBackground();
    }

    public void setShowBackground(boolean show) {
        if (this.mBackgroundDrawable.getShowBackground() != show) {
            this.mBackgroundDrawable.setShowBackground(show);
            updateSecondaryProgressTint();
        }
    }

    public boolean getUseIntrinsicPadding() {
        return this.mBackgroundDrawable.getUseIntrinsicPadding();
    }

    public void setUseIntrinsicPadding(boolean useIntrinsicPadding) {
        this.mBackgroundDrawable.setUseIntrinsicPadding(useIntrinsicPadding);
        this.mSecondaryProgressDrawable.setUseIntrinsicPadding(useIntrinsicPadding);
        this.mProgressDrawable.setUseIntrinsicPadding(useIntrinsicPadding);
    }

    @SuppressLint({"NewApi"})
    public void setTint(@ColorInt int tintColor) {
        int backgroundTintColor = ColorUtils.setAlphaComponent(tintColor, Math.round(((float) Color.alpha(tintColor)) * this.mBackgroundAlpha));
        this.mBackgroundDrawable.setTint(backgroundTintColor);
        setSecondaryProgressTint(backgroundTintColor);
        this.mProgressDrawable.setTint(tintColor);
    }

    @SuppressLint({"NewApi"})
    public void setTintList(@Nullable ColorStateList tint) {
        ColorStateList backgroundTint;
        if (tint != null) {
            if (!tint.isOpaque()) {
                Log.w(TAG, "setTintList() called with a non-opaque ColorStateList, its original alpha will be discarded");
            }
            backgroundTint = tint.withAlpha(Math.round(255.0f * this.mBackgroundAlpha));
        } else {
            backgroundTint = null;
        }
        this.mBackgroundDrawable.setTintList(backgroundTint);
        setSecondaryProgressTintList(backgroundTint);
        this.mProgressDrawable.setTintList(tint);
    }

    @SuppressLint({"NewApi"})
    public void setTintMode(@NonNull Mode tintMode) {
        this.mBackgroundDrawable.setTintMode(tintMode);
        this.mSecondaryProgressDrawable.setTintMode(tintMode);
        this.mProgressDrawable.setTintMode(tintMode);
    }

    private void setSecondaryProgressTint(int tintColor) {
        this.mHasSecondaryProgressTintColor = true;
        this.mSecondaryProgressTintColor = tintColor;
        this.mHasSecondaryProgressTint = false;
        updateSecondaryProgressTint();
    }

    private void setSecondaryProgressTintList(ColorStateList tint) {
        this.mHasSecondaryProgressTintColor = false;
        this.mHasSecondaryProgressTint = true;
        this.mSecondaryProgressTint = tint;
        updateSecondaryProgressTint();
    }

    @SuppressLint({"NewApi"})
    private void updateSecondaryProgressTint() {
        if (this.mHasSecondaryProgressTintColor) {
            int tintColor = this.mSecondaryProgressTintColor;
            if (!getShowBackground()) {
                float backgroundAlpha = ((float) Color.alpha(tintColor)) / 255.0f;
                tintColor = ColorUtils.setAlphaComponent(tintColor, Math.round(compositeAlpha(backgroundAlpha, backgroundAlpha) * 255.0f));
            }
            this.mSecondaryProgressDrawable.setTint(tintColor);
        } else if (this.mHasSecondaryProgressTint) {
            ColorStateList tint = this.mSecondaryProgressTint;
            if (!getShowBackground()) {
                tint = tint.withAlpha(Math.round(compositeAlpha(this.mBackgroundAlpha, this.mBackgroundAlpha) * 255.0f));
            }
            this.mSecondaryProgressDrawable.setTintList(tint);
        }
    }

    private float compositeAlpha(float alpha1, float alpha2) {
        return ((1.0f - alpha1) * alpha2) + alpha1;
    }
}
