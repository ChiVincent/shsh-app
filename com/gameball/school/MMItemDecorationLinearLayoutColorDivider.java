package com.gameball.school;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class MMItemDecorationLinearLayoutColorDivider extends ItemDecoration {
    private final Drawable mDivider;
    private final int mMargin;
    private final int mOrientation;
    private final int mSize;

    public MMItemDecorationLinearLayoutColorDivider(Resources resources, @ColorRes int color, int size_in_dp, int margin_in_dp, int orientation) {
        float density = resources.getDisplayMetrics().density;
        this.mDivider = new ColorDrawable(resources.getColor(color));
        this.mSize = (int) (((float) size_in_dp) * density);
        this.mMargin = (int) (((float) margin_in_dp) * density);
        this.mOrientation = orientation;
    }

    public void onDraw(Canvas c, RecyclerView parent, State state) {
        int top;
        int childCount;
        int i;
        if (this.mOrientation == 0) {
            top = parent.getPaddingTop();
            int bottom = parent.getHeight() - parent.getPaddingBottom();
            childCount = parent.getChildCount();
            for (i = 0; i < childCount - 1; i++) {
                View child = parent.getChildAt(i);
                int left = child.getRight() + ((LayoutParams) child.getLayoutParams()).rightMargin;
                this.mDivider.setBounds(left, top, left + this.mSize, bottom);
                this.mDivider.draw(c);
            }
            return;
        }
        left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        childCount = parent.getChildCount();
        for (i = 0; i < childCount; i++) {
            child = parent.getChildAt(i);
            top = child.getBottom() + ((LayoutParams) child.getLayoutParams()).bottomMargin;
            this.mDivider.setBounds(this.mMargin + left, top, right - this.mMargin, top + this.mSize);
            this.mDivider.draw(c);
        }
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        if (this.mOrientation == 0) {
            outRect.set(0, 0, this.mSize, 0);
        } else {
            outRect.set(0, this.mSize, 0, this.mSize);
        }
    }
}
