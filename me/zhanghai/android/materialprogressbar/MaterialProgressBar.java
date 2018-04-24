package me.zhanghai.android.materialprogressbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;
import me.zhanghai.android.materialprogressbar.internal.DrawableCompat;

public class MaterialProgressBar extends ProgressBar {
    public static final int PROGRESS_STYLE_CIRCULAR = 0;
    public static final int PROGRESS_STYLE_HORIZONTAL = 1;
    private static final String TAG = MaterialProgressBar.class.getSimpleName();
    private int mProgressStyle;
    private TintInfo mProgressTintInfo = new TintInfo();

    private static class TintInfo {
        public boolean mHasIndeterminateTint;
        public boolean mHasIndeterminateTintMode;
        public boolean mHasProgressBackgroundTint;
        public boolean mHasProgressBackgroundTintMode;
        public boolean mHasProgressTint;
        public boolean mHasProgressTintMode;
        public boolean mHasSecondaryProgressTint;
        public boolean mHasSecondaryProgressTintMode;
        public ColorStateList mIndeterminateTint;
        public Mode mIndeterminateTintMode;
        public ColorStateList mProgressBackgroundTint;
        public Mode mProgressBackgroundTintMode;
        public ColorStateList mProgressTint;
        public Mode mProgressTintMode;
        public ColorStateList mSecondaryProgressTint;
        public Mode mSecondaryProgressTintMode;

        private TintInfo() {
        }
    }

    public MaterialProgressBar(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, 0);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        boolean z = false;
        Context context = getContext();
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, C0567R.styleable.MaterialProgressBar, defStyleAttr, defStyleRes);
        this.mProgressStyle = a.getInt(C0567R.styleable.MaterialProgressBar_mpb_progressStyle, 0);
        boolean setBothDrawables = a.getBoolean(C0567R.styleable.MaterialProgressBar_mpb_setBothDrawables, false);
        boolean useIntrinsicPadding = a.getBoolean(C0567R.styleable.MaterialProgressBar_mpb_useIntrinsicPadding, true);
        int i = C0567R.styleable.MaterialProgressBar_mpb_showProgressBackground;
        if (this.mProgressStyle == 1) {
            z = true;
        }
        boolean showProgressBackground = a.getBoolean(i, z);
        if (a.hasValue(C0567R.styleable.MaterialProgressBar_mpb_progressTint)) {
            this.mProgressTintInfo.mProgressTint = a.getColorStateList(C0567R.styleable.MaterialProgressBar_mpb_progressTint);
            this.mProgressTintInfo.mHasProgressTint = true;
        }
        if (a.hasValue(C0567R.styleable.MaterialProgressBar_mpb_progressTintMode)) {
            this.mProgressTintInfo.mProgressTintMode = DrawableCompat.parseTintMode(a.getInt(C0567R.styleable.MaterialProgressBar_mpb_progressTintMode, -1), null);
            this.mProgressTintInfo.mHasProgressTintMode = true;
        }
        if (a.hasValue(C0567R.styleable.MaterialProgressBar_mpb_secondaryProgressTint)) {
            this.mProgressTintInfo.mSecondaryProgressTint = a.getColorStateList(C0567R.styleable.MaterialProgressBar_mpb_secondaryProgressTint);
            this.mProgressTintInfo.mHasSecondaryProgressTint = true;
        }
        if (a.hasValue(C0567R.styleable.MaterialProgressBar_mpb_secondaryProgressTintMode)) {
            this.mProgressTintInfo.mSecondaryProgressTintMode = DrawableCompat.parseTintMode(a.getInt(C0567R.styleable.MaterialProgressBar_mpb_secondaryProgressTintMode, -1), null);
            this.mProgressTintInfo.mHasSecondaryProgressTintMode = true;
        }
        if (a.hasValue(C0567R.styleable.MaterialProgressBar_mpb_progressBackgroundTint)) {
            this.mProgressTintInfo.mProgressBackgroundTint = a.getColorStateList(C0567R.styleable.MaterialProgressBar_mpb_progressBackgroundTint);
            this.mProgressTintInfo.mHasProgressBackgroundTint = true;
        }
        if (a.hasValue(C0567R.styleable.MaterialProgressBar_mpb_progressBackgroundTintMode)) {
            this.mProgressTintInfo.mProgressBackgroundTintMode = DrawableCompat.parseTintMode(a.getInt(C0567R.styleable.MaterialProgressBar_mpb_progressBackgroundTintMode, -1), null);
            this.mProgressTintInfo.mHasProgressBackgroundTintMode = true;
        }
        if (a.hasValue(C0567R.styleable.MaterialProgressBar_mpb_indeterminateTint)) {
            this.mProgressTintInfo.mIndeterminateTint = a.getColorStateList(C0567R.styleable.MaterialProgressBar_mpb_indeterminateTint);
            this.mProgressTintInfo.mHasIndeterminateTint = true;
        }
        if (a.hasValue(C0567R.styleable.MaterialProgressBar_mpb_indeterminateTintMode)) {
            this.mProgressTintInfo.mIndeterminateTintMode = DrawableCompat.parseTintMode(a.getInt(C0567R.styleable.MaterialProgressBar_mpb_indeterminateTintMode, -1), null);
            this.mProgressTintInfo.mHasIndeterminateTintMode = true;
        }
        a.recycle();
        switch (this.mProgressStyle) {
            case 0:
                if (isIndeterminate() && !setBothDrawables) {
                    if (!isInEditMode()) {
                        setIndeterminateDrawable(new IndeterminateProgressDrawable(context));
                        break;
                    }
                }
                throw new UnsupportedOperationException("Determinate circular drawable is not yet supported");
                break;
            case 1:
                if ((isIndeterminate() || setBothDrawables) && !isInEditMode()) {
                    setIndeterminateDrawable(new IndeterminateHorizontalProgressDrawable(context));
                }
                if (!isIndeterminate() || setBothDrawables) {
                    setProgressDrawable(new HorizontalProgressDrawable(context));
                    break;
                }
            default:
                throw new IllegalArgumentException("Unknown progress style: " + this.mProgressStyle);
        }
        setUseIntrinsicPadding(useIntrinsicPadding);
        setShowProgressBackground(showProgressBackground);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        fixCanvasScalingWhenHardwareAccelerated();
    }

    private void fixCanvasScalingWhenHardwareAccelerated() {
        if (VERSION.SDK_INT < 18 && isHardwareAccelerated() && getLayerType() != 1) {
            setLayerType(1, null);
        }
    }

    public int getProgressStyle() {
        return this.mProgressStyle;
    }

    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public boolean getUseIntrinsicPadding() {
        Drawable drawable = getCurrentDrawable();
        if (drawable instanceof IntrinsicPaddingDrawable) {
            return ((IntrinsicPaddingDrawable) drawable).getUseIntrinsicPadding();
        }
        throw new IllegalStateException("Drawable does not implement IntrinsicPaddingDrawable");
    }

    public void setUseIntrinsicPadding(boolean useIntrinsicPadding) {
        Drawable drawable = getCurrentDrawable();
        if (drawable instanceof IntrinsicPaddingDrawable) {
            ((IntrinsicPaddingDrawable) drawable).setUseIntrinsicPadding(useIntrinsicPadding);
        }
        Drawable indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable instanceof IntrinsicPaddingDrawable) {
            ((IntrinsicPaddingDrawable) indeterminateDrawable).setUseIntrinsicPadding(useIntrinsicPadding);
        }
    }

    public boolean getShowProgressBackground() {
        Drawable drawable = getCurrentDrawable();
        if (drawable instanceof ShowBackgroundDrawable) {
            return ((ShowBackgroundDrawable) drawable).getShowBackground();
        }
        return false;
    }

    public void setShowProgressBackground(boolean show) {
        Drawable drawable = getCurrentDrawable();
        if (drawable instanceof ShowBackgroundDrawable) {
            ((ShowBackgroundDrawable) drawable).setShowBackground(show);
        }
        Drawable indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable instanceof ShowBackgroundDrawable) {
            ((ShowBackgroundDrawable) indeterminateDrawable).setShowBackground(show);
        }
    }

    public void setProgressDrawable(Drawable d) {
        super.setProgressDrawable(d);
        if (this.mProgressTintInfo != null) {
            applyProgressTints();
        }
    }

    public void setIndeterminateDrawable(Drawable d) {
        super.setIndeterminateDrawable(d);
        if (this.mProgressTintInfo != null) {
            applyIndeterminateTint();
        }
    }

    @Nullable
    public ColorStateList getProgressTintList() {
        return this.mProgressTintInfo.mProgressTint;
    }

    public void setProgressTintList(@Nullable ColorStateList tint) {
        this.mProgressTintInfo.mProgressTint = tint;
        this.mProgressTintInfo.mHasProgressTint = true;
        applyPrimaryProgressTint();
    }

    @Nullable
    public Mode getProgressTintMode() {
        return this.mProgressTintInfo.mProgressTintMode;
    }

    public void setProgressTintMode(@Nullable Mode tintMode) {
        this.mProgressTintInfo.mProgressTintMode = tintMode;
        this.mProgressTintInfo.mHasProgressTintMode = true;
        applyPrimaryProgressTint();
    }

    @Nullable
    public ColorStateList getSecondaryProgressTintList() {
        return this.mProgressTintInfo.mSecondaryProgressTint;
    }

    public void setSecondaryProgressTintList(@Nullable ColorStateList tint) {
        this.mProgressTintInfo.mSecondaryProgressTint = tint;
        this.mProgressTintInfo.mHasSecondaryProgressTint = true;
        applySecondaryProgressTint();
    }

    @Nullable
    public Mode getSecondaryProgressTintMode() {
        return this.mProgressTintInfo.mSecondaryProgressTintMode;
    }

    public void setSecondaryProgressTintMode(@Nullable Mode tintMode) {
        this.mProgressTintInfo.mSecondaryProgressTintMode = tintMode;
        this.mProgressTintInfo.mHasSecondaryProgressTintMode = true;
        applySecondaryProgressTint();
    }

    @Nullable
    public ColorStateList getProgressBackgroundTintList() {
        return this.mProgressTintInfo.mProgressBackgroundTint;
    }

    public void setProgressBackgroundTintList(@Nullable ColorStateList tint) {
        this.mProgressTintInfo.mProgressBackgroundTint = tint;
        this.mProgressTintInfo.mHasProgressBackgroundTint = true;
        applyProgressBackgroundTint();
    }

    @Nullable
    public Mode getProgressBackgroundTintMode() {
        return this.mProgressTintInfo.mProgressBackgroundTintMode;
    }

    public void setProgressBackgroundTintMode(@Nullable Mode tintMode) {
        this.mProgressTintInfo.mProgressBackgroundTintMode = tintMode;
        this.mProgressTintInfo.mHasProgressBackgroundTintMode = true;
        applyProgressBackgroundTint();
    }

    @Nullable
    public ColorStateList getIndeterminateTintList() {
        return this.mProgressTintInfo.mIndeterminateTint;
    }

    public void setIndeterminateTintList(@Nullable ColorStateList tint) {
        this.mProgressTintInfo.mIndeterminateTint = tint;
        this.mProgressTintInfo.mHasIndeterminateTint = true;
        applyIndeterminateTint();
    }

    @Nullable
    public Mode getIndeterminateTintMode() {
        return this.mProgressTintInfo.mIndeterminateTintMode;
    }

    public void setIndeterminateTintMode(@Nullable Mode tintMode) {
        this.mProgressTintInfo.mIndeterminateTintMode = tintMode;
        this.mProgressTintInfo.mHasIndeterminateTintMode = true;
        applyIndeterminateTint();
    }

    private void applyProgressTints() {
        if (getProgressDrawable() != null) {
            applyPrimaryProgressTint();
            applyProgressBackgroundTint();
            applySecondaryProgressTint();
        }
    }

    private void applyPrimaryProgressTint() {
        if (getProgressDrawable() != null) {
            if (this.mProgressTintInfo.mHasProgressTint || this.mProgressTintInfo.mHasProgressTintMode) {
                Drawable target = getTintTargetFromProgressDrawable(16908301, true);
                if (target != null) {
                    applyTintForDrawable(target, this.mProgressTintInfo.mProgressTint, this.mProgressTintInfo.mHasProgressTint, this.mProgressTintInfo.mProgressTintMode, this.mProgressTintInfo.mHasProgressTintMode);
                }
            }
        }
    }

    private void applySecondaryProgressTint() {
        if (getProgressDrawable() != null) {
            if (this.mProgressTintInfo.mHasSecondaryProgressTint || this.mProgressTintInfo.mHasSecondaryProgressTintMode) {
                Drawable target = getTintTargetFromProgressDrawable(16908303, false);
                if (target != null) {
                    applyTintForDrawable(target, this.mProgressTintInfo.mSecondaryProgressTint, this.mProgressTintInfo.mHasSecondaryProgressTint, this.mProgressTintInfo.mSecondaryProgressTintMode, this.mProgressTintInfo.mHasSecondaryProgressTintMode);
                }
            }
        }
    }

    private void applyProgressBackgroundTint() {
        if (getProgressDrawable() != null) {
            if (this.mProgressTintInfo.mHasProgressBackgroundTint || this.mProgressTintInfo.mHasProgressBackgroundTintMode) {
                Drawable target = getTintTargetFromProgressDrawable(16908288, false);
                if (target != null) {
                    applyTintForDrawable(target, this.mProgressTintInfo.mProgressBackgroundTint, this.mProgressTintInfo.mHasProgressBackgroundTint, this.mProgressTintInfo.mProgressBackgroundTintMode, this.mProgressTintInfo.mHasProgressBackgroundTintMode);
                }
            }
        }
    }

    private Drawable getTintTargetFromProgressDrawable(int layerId, boolean shouldFallback) {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable == null) {
            return null;
        }
        progressDrawable.mutate();
        Drawable layerDrawable = null;
        if (progressDrawable instanceof LayerDrawable) {
            layerDrawable = ((LayerDrawable) progressDrawable).findDrawableByLayerId(layerId);
        }
        if (layerDrawable == null && shouldFallback) {
            return progressDrawable;
        }
        return layerDrawable;
    }

    private void applyIndeterminateTint() {
        Drawable indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            if (this.mProgressTintInfo.mHasIndeterminateTint || this.mProgressTintInfo.mHasIndeterminateTintMode) {
                indeterminateDrawable.mutate();
                applyTintForDrawable(indeterminateDrawable, this.mProgressTintInfo.mIndeterminateTint, this.mProgressTintInfo.mHasIndeterminateTint, this.mProgressTintInfo.mIndeterminateTintMode, this.mProgressTintInfo.mHasIndeterminateTintMode);
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void applyTintForDrawable(Drawable drawable, ColorStateList tint, boolean hasTint, Mode tintMode, boolean hasTintMode) {
        if (hasTint || hasTintMode) {
            if (hasTint) {
                if (drawable instanceof TintableDrawable) {
                    ((TintableDrawable) drawable).setTintList(tint);
                } else {
                    Log.w(TAG, "Drawable did not implement TintableDrawable, it won't be tinted below Lollipop");
                    if (VERSION.SDK_INT >= 21) {
                        drawable.setTintList(tint);
                    }
                }
            }
            if (hasTintMode) {
                if (drawable instanceof TintableDrawable) {
                    ((TintableDrawable) drawable).setTintMode(tintMode);
                } else {
                    Log.w(TAG, "Drawable did not implement TintableDrawable, it won't be tinted below Lollipop");
                    if (VERSION.SDK_INT >= 21) {
                        drawable.setTintMode(tintMode);
                    }
                }
            }
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
    }
}
