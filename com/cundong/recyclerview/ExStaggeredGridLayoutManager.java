package com.cundong.recyclerview;

import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager.LayoutParams;
import android.view.View;

public class ExStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
    private final String TAG = getClass().getSimpleName();
    SpanSizeLookup mSpanSizeLookup;

    public ExStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public void onMeasure(Recycler recycler, State state, int widthSpec, int heightSpec) {
        for (int i = 0; i < getItemCount(); i++) {
            if (this.mSpanSizeLookup.getSpanSize(i) > 1) {
                try {
                    View view = recycler.getViewForPosition(i);
                    if (view != null) {
                        ((LayoutParams) view.getLayoutParams()).setFullSpan(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}
