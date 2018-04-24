package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMEmailAccomplishedActivity_ViewBinding<T extends MMEmailAccomplishedActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMEmailAccomplishedActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mTextViewEmail = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_email, "field 'mTextViewEmail'", TextView.class);
        target.mImageViewLine = (ImageView) Utils.findRequiredViewAsType(source, C0308R.id.imageview_line, "field 'mImageViewLine'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mTextViewEmail = null;
        target.mImageViewLine = null;
        this.target = null;
    }
}
