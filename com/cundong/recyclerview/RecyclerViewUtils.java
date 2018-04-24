package com.cundong.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public class RecyclerViewUtils {
    public static void setHeaderView(RecyclerView recyclerView, View view) {
        Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
            if (headerAndFooterAdapter.getHeaderViewsCount() == 0) {
                headerAndFooterAdapter.addHeaderView(view);
            }
        }
    }

    public static void setFooterView(RecyclerView recyclerView, View view) {
        Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
            if (headerAndFooterAdapter.getFooterViewsCount() == 0) {
                headerAndFooterAdapter.addFooterView(view);
            }
        }
    }

    public static void removeFooterView(RecyclerView recyclerView) {
        Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) && ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterViewsCount() > 0) {
            ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).removeFooterView(((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterView());
        }
    }

    public static void removeHeaderView(RecyclerView recyclerView) {
        Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) && ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount() > 0) {
            ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).removeFooterView(((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderView());
        }
    }

    public static int getLayoutPosition(RecyclerView recyclerView, ViewHolder holder) {
        Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getLayoutPosition() - headerViewCounter;
            }
        }
        return holder.getLayoutPosition();
    }

    public static int getAdapterPosition(RecyclerView recyclerView, ViewHolder holder) {
        Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getAdapterPosition() - headerViewCounter;
            }
        }
        return holder.getAdapterPosition();
    }
}
