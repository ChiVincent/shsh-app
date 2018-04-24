package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMLoginActivity_ViewBinding<T extends MMLoginActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMLoginActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mlinear1 = (LinearLayout) Utils.findRequiredViewAsType(source, C0308R.id.linear2, "field 'mlinear1'", LinearLayout.class);
        target.mPhone = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.phone, "field 'mPhone'", EditText.class);
        target.mPassword = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.password, "field 'mPassword'", EditText.class);
        target.mlogin = Utils.findRequiredView(source, C0308R.id.activity_login, "field 'mlogin'");
        target.regist = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.regist, "field 'regist'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mlinear1 = null;
        target.mPhone = null;
        target.mPassword = null;
        target.mlogin = null;
        target.regist = null;
        this.target = null;
    }
}
