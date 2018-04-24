package me.zhanghai.android.materialprogressbar;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

abstract class BaseDrawable extends Drawable implements TintableDrawable {
    protected int mAlpha = 255;
    protected ColorFilter mColorFilter;
    private DummyConstantState mConstantState = new DummyConstantState();
    protected PorterDuffColorFilter mTintFilter;
    protected ColorStateList mTintList;
    protected Mode mTintMode = Mode.SRC_IN;

    private class DummyConstantState extends ConstantState {
        private DummyConstantState() {
        }

        public int getChangingConfigurations() {
            return 0;
        }

        @NonNull
        public Drawable newDrawable() {
            return BaseDrawable.this;
        }
    }

    protected abstract void onDraw(Canvas canvas, int i, int i2);

    BaseDrawable() {
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(int alpha) {
        if (this.mAlpha != alpha) {
            this.mAlpha = alpha;
            invalidateSelf();
        }
    }

    public ColorFilter getColorFilter() {
        return this.mColorFilter;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mColorFilter = colorFilter;
        invalidateSelf();
    }

    public void setTint(@ColorInt int tintColor) {
        setTintList(ColorStateList.valueOf(tintColor));
    }

    public void setTintList(@Nullable ColorStateList tint) {
        this.mTintList = tint;
        if (updateTintFilter()) {
            invalidateSelf();
        }
    }

    public void setTintMode(@NonNull Mode tintMode) {
        this.mTintMode = tintMode;
        if (updateTintFilter()) {
            invalidateSelf();
        }
    }

    public boolean isStateful() {
        return this.mTintList != null && this.mTintList.isStateful();
    }

    protected boolean onStateChange(int[] state) {
        return updateTintFilter();
    }

    private boolean updateTintFilter() {
        boolean hadTintFilter = true;
        if (this.mTintList == null || this.mTintMode == null) {
            if (this.mTintFilter == null) {
                hadTintFilter = false;
            }
            this.mTintFilter = null;
        } else {
            this.mTintFilter = new PorterDuffColorFilter(this.mTintList.getColorForState(getState(), 0), this.mTintMode);
        }
        return hadTintFilter;
    }

    public int getOpacity() {
        return -3;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.width() != 0 && bounds.height() != 0) {
            int saveCount = canvas.save();
            canvas.translate((float) bounds.left, (float) bounds.top);
            onDraw(canvas, bounds.width(), bounds.height());
            canvas.restoreToCount(saveCount);
        }
    }

    protected ColorFilter getColorFilterForDrawing() {
        return this.mColorFilter != null ? this.mColorFilter : this.mTintFilter;
    }

    public ConstantState getConstantState() {
        return this.mConstantState;
    }
}
