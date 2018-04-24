package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMEmailAlreadyAccomplishedActivity_ViewBinding<T extends MMEmailAlreadyAccomplishedActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMEmailAlreadyAccomplishedActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mTextViewEmail = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_email, "field 'mTextViewEmail'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mTextViewEmail = null;
        this.target = null;
    }
}
