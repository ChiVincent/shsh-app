package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMFragment2Event_ViewBinding<T extends MMFragment2Event> implements Unbinder {
    protected T target;

    @UiThread
    public MMFragment2Event_ViewBinding(T target, View source) {
        this.target = target;
        target.mWebView = (WebView) Utils.findRequiredViewAsType(source, C0308R.id.webview, "field 'mWebView'", WebView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mWebView = null;
        this.target = null;
    }
}
