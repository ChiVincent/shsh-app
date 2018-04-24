package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMFragment1ContactService_ViewBinding<T extends MMFragment1ContactService> implements Unbinder {
    protected T target;

    @UiThread
    public MMFragment1ContactService_ViewBinding(T target, View source) {
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
