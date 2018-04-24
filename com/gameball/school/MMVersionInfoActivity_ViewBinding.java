package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMVersionInfoActivity_ViewBinding<T extends MMVersionInfoActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMVersionInfoActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mTextViewVersionInfo = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.text_version_info, "field 'mTextViewVersionInfo'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mTextViewVersionInfo = null;
        this.target = null;
    }
}
