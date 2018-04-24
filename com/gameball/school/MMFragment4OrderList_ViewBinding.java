package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMFragment4OrderList_ViewBinding<T extends MMFragment4OrderList> implements Unbinder {
    protected T target;

    @UiThread
    public MMFragment4OrderList_ViewBinding(T target, View source) {
        this.target = target;
        target.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0308R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        target.mSwipeRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(source, C0308R.id.swiprefreshlayout, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mRecyclerView = null;
        target.mSwipeRefreshLayout = null;
        this.target = null;
    }
}
