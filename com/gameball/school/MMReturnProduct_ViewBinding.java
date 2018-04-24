package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMReturnProduct_ViewBinding<T extends MMReturnProduct> implements Unbinder {
    protected T target;

    @UiThread
    public MMReturnProduct_ViewBinding(T target, View source) {
        this.target = target;
        target.mTextViewShop = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_shop, "field 'mTextViewShop'", TextView.class);
        target.mTextViewSpecification = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_specification, "field 'mTextViewSpecification'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mTextViewShop = null;
        target.mTextViewSpecification = null;
        this.target = null;
    }
}
