package com.cundong.recyclerview;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

public class HeaderAndFooterRecyclerViewAdapter extends Adapter<android.support.v7.widget.RecyclerView.ViewHolder> {
    private static final int TYPE_FOOTER_VIEW = -2147483647;
    private static final int TYPE_HEADER_VIEW = Integer.MIN_VALUE;
    private AdapterDataObserver mDataObserver = new C07141();
    private ArrayList<View> mFooterViews = new ArrayList();
    private ArrayList<View> mHeaderViews = new ArrayList();
    private Adapter<android.support.v7.widget.RecyclerView.ViewHolder> mInnerAdapter;

    class C07141 extends AdapterDataObserver {
        C07141() {
        }

        public void onChanged() {
            super.onChanged();
            HeaderAndFooterRecyclerViewAdapter.this.notifyDataSetChanged();
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            HeaderAndFooterRecyclerViewAdapter.this.notifyItemRangeChanged(HeaderAndFooterRecyclerViewAdapter.this.getHeaderViewsCount() + positionStart, itemCount);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            HeaderAndFooterRecyclerViewAdapter.this.notifyItemRangeInserted(HeaderAndFooterRecyclerViewAdapter.this.getHeaderViewsCount() + positionStart, itemCount);
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            HeaderAndFooterRecyclerViewAdapter.this.notifyItemRangeRemoved(HeaderAndFooterRecyclerViewAdapter.this.getHeaderViewsCount() + positionStart, itemCount);
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            int headerViewsCountCount = HeaderAndFooterRecyclerViewAdapter.this.getHeaderViewsCount();
            HeaderAndFooterRecyclerViewAdapter.this.notifyItemRangeChanged(fromPosition + headerViewsCountCount, (toPosition + headerViewsCountCount) + itemCount);
        }
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public HeaderAndFooterRecyclerViewAdapter(Adapter innerAdapter) {
        setAdapter(innerAdapter);
    }

    public void setAdapter(Adapter<android.support.v7.widget.RecyclerView.ViewHolder> adapter) {
        if (adapter == null || (adapter instanceof Adapter)) {
            if (this.mInnerAdapter != null) {
                notifyItemRangeRemoved(getHeaderViewsCount(), this.mInnerAdapter.getItemCount());
                this.mInnerAdapter.unregisterAdapterDataObserver(this.mDataObserver);
            }
            this.mInnerAdapter = adapter;
            this.mInnerAdapter.registerAdapterDataObserver(this.mDataObserver);
            notifyItemRangeInserted(getHeaderViewsCount(), this.mInnerAdapter.getItemCount());
            return;
        }
        throw new RuntimeException("your adapter must be a RecyclerView.Adapter");
    }

    public Adapter getInnerAdapter() {
        return this.mInnerAdapter;
    }

    public void addHeaderView(View header) {
        if (header == null) {
            throw new RuntimeException("header is null");
        }
        this.mHeaderViews.add(header);
        notifyDataSetChanged();
    }

    public void addFooterView(View footer) {
        if (footer == null) {
            throw new RuntimeException("footer is null");
        }
        this.mFooterViews.add(footer);
        notifyDataSetChanged();
    }

    public View getFooterView() {
        return getFooterViewsCount() > 0 ? (View) this.mFooterViews.get(0) : null;
    }

    public View getHeaderView() {
        return getHeaderViewsCount() > 0 ? (View) this.mHeaderViews.get(0) : null;
    }

    public void removeHeaderView(View view) {
        this.mHeaderViews.remove(view);
        notifyDataSetChanged();
    }

    public void removeFooterView(View view) {
        this.mFooterViews.remove(view);
        notifyDataSetChanged();
    }

    public int getHeaderViewsCount() {
        return this.mHeaderViews.size();
    }

    public int getFooterViewsCount() {
        return this.mFooterViews.size();
    }

    public boolean isHeader(int position) {
        return getHeaderViewsCount() > 0 && position == 0;
    }

    public boolean isFooter(int position) {
        return getFooterViewsCount() > 0 && position == getItemCount() - 1;
    }

    public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < Integer.MIN_VALUE + getHeaderViewsCount()) {
            return new ViewHolder((View) this.mHeaderViews.get(viewType - Integer.MIN_VALUE));
        }
        if (viewType < TYPE_FOOTER_VIEW || viewType >= 1073741823) {
            return this.mInnerAdapter.onCreateViewHolder(parent, viewType - 1073741823);
        }
        return new ViewHolder((View) this.mFooterViews.get(viewType - TYPE_FOOTER_VIEW));
    }

    public void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder holder, int position) {
        int headerViewsCountCount = getHeaderViewsCount();
        if (position < headerViewsCountCount || position >= this.mInnerAdapter.getItemCount() + headerViewsCountCount) {
            LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
                return;
            }
            return;
        }
        this.mInnerAdapter.onBindViewHolder(holder, position - headerViewsCountCount);
    }

    public int getItemCount() {
        return (getHeaderViewsCount() + getFooterViewsCount()) + this.mInnerAdapter.getItemCount();
    }

    public int getItemViewType(int position) {
        int innerCount = this.mInnerAdapter.getItemCount();
        int headerViewsCountCount = getHeaderViewsCount();
        if (position < headerViewsCountCount) {
            return Integer.MIN_VALUE + position;
        }
        if (headerViewsCountCount > position || position >= headerViewsCountCount + innerCount) {
            return ((TYPE_FOOTER_VIEW + position) - headerViewsCountCount) - innerCount;
        }
        int innerItemViewType = this.mInnerAdapter.getItemViewType(position - headerViewsCountCount);
        if (innerItemViewType < 1073741823) {
            return innerItemViewType + 1073741823;
        }
        throw new IllegalArgumentException("your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
    }

    public void onViewAttachedToWindow(android.support.v7.widget.RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && (lp instanceof StaggeredGridLayoutManager.LayoutParams) && holder.getLayoutPosition() < getHeaderViewsCount()) {
            ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
        }
    }
}
