package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMEmailActivity_ViewBinding<T extends MMEmailActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMEmailActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.EditTextEmail = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.edittext_email, "field 'EditTextEmail'", EditText.class);
        target.mViewFooter = Utils.findRequiredView(source, C0308R.id.linearlayout, "field 'mViewFooter'");
        target.imageView = Utils.findRequiredView(source, C0308R.id.imageView, "field 'imageView'");
        target.imageView2 = Utils.findRequiredView(source, C0308R.id.imageView2, "field 'imageView2'");
        target.texthint = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.texthint, "field 'texthint'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.EditTextEmail = null;
        target.mViewFooter = null;
        target.imageView = null;
        target.imageView2 = null;
        target.texthint = null;
        this.target = null;
    }
}
