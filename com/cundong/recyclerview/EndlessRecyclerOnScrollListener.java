package com.cundong.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class EndlessRecyclerOnScrollListener extends OnScrollListener implements OnListLoadNextPageListener {
    private int currentScrollState = 0;
    private int[] lastPositions;
    private int lastVisibleItemPosition;
    protected LayoutManagerType layoutManagerType;

    public enum LayoutManagerType {
        LinearLayout,
        StaggeredGridLayout,
        GridLayout
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (this.layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                this.layoutManagerType = LayoutManagerType.LinearLayout;
            } else if (layoutManager instanceof GridLayoutManager) {
                this.layoutManagerType = LayoutManagerType.GridLayout;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                this.layoutManagerType = LayoutManagerType.StaggeredGridLayout;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }
        switch (this.layoutManagerType) {
            case LinearLayout:
                this.lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                return;
            case GridLayout:
                this.lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                return;
            case StaggeredGridLayout:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (this.lastPositions == null) {
                    this.lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(this.lastPositions);
                this.lastVisibleItemPosition = findMax(this.lastPositions);
                return;
            default:
                return;
        }
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        this.currentScrollState = newState;
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if (visibleItemCount > 0 && this.currentScrollState == 0 && this.lastVisibleItemPosition >= totalItemCount - 1) {
            onLoadNextPage(recyclerView);
        }
    }

    private int findMax(int[] lastPositions) {
        int i = 0;
        int max = lastPositions[0];
        int length = lastPositions.length;
        while (i < length) {
            int value = lastPositions[i];
            if (value > max) {
                max = value;
            }
            i++;
        }
        return max;
    }

    public void onLoadNextPage(View view) {
    }
}
