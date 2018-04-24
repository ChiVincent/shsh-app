package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMSmsAuthenticationActivity_ViewBinding<T extends MMSmsAuthenticationActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMSmsAuthenticationActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.EditTextCode = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.edittext_code, "field 'EditTextCode'", EditText.class);
        target.TextViewSendSms = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_send_sms, "field 'TextViewSendSms'", TextView.class);
        target.TextViewSendRemain = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_send_remain, "field 'TextViewSendRemain'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.EditTextCode = null;
        target.TextViewSendSms = null;
        target.TextViewSendRemain = null;
        this.target = null;
    }
}
