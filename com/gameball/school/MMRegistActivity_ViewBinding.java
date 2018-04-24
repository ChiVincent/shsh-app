package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMRegistActivity_ViewBinding<T extends MMRegistActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMRegistActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mlinear1 = (LinearLayout) Utils.findRequiredViewAsType(source, C0308R.id.linear1, "field 'mlinear1'", LinearLayout.class);
        target.mSpinner = (AppCompatSpinner) Utils.findRequiredViewAsType(source, C0308R.id.Spinner1, "field 'mSpinner'", AppCompatSpinner.class);
        target.mSpinner2 = (AppCompatSpinner) Utils.findRequiredViewAsType(source, C0308R.id.Spinner2, "field 'mSpinner2'", AppCompatSpinner.class);
        target.mSpinner3 = (AppCompatSpinner) Utils.findRequiredViewAsType(source, C0308R.id.Spinner3, "field 'mSpinner3'", AppCompatSpinner.class);
        target.mSpinner4 = (AppCompatSpinner) Utils.findRequiredViewAsType(source, C0308R.id.Spinner4, "field 'mSpinner4'", AppCompatSpinner.class);
        target.mName = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.name, "field 'mName'", EditText.class);
        target.mPhone = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.phone, "field 'mPhone'", EditText.class);
        target.mPassword = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.password, "field 'mPassword'", EditText.class);
        target.mPasswordChack = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.password_chack, "field 'mPasswordChack'", EditText.class);
        target.mIdentity = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.identity, "field 'mIdentity'", EditText.class);
        target.birthday = (EditText) Utils.findRequiredViewAsType(source, C0308R.id.birthday, "field 'birthday'", EditText.class);
        target.mLogin = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.login, "field 'mLogin'", TextView.class);
        target.mTitlePhone = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.titlephone, "field 'mTitlePhone'", TextView.class);
        target.mTitlePassword = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.title_password, "field 'mTitlePassword'", TextView.class);
        target.mTitlepasswordChack = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.title_password_chack, "field 'mTitlepasswordChack'", TextView.class);
        target.mTitleName = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.title_name, "field 'mTitleName'", TextView.class);
        target.mTitleBirthday = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.title_birthday, "field 'mTitleBirthday'", TextView.class);
        target.mregist = Utils.findRequiredView(source, C0308R.id.activity_regist, "field 'mregist'");
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mlinear1 = null;
        target.mSpinner = null;
        target.mSpinner2 = null;
        target.mSpinner3 = null;
        target.mSpinner4 = null;
        target.mName = null;
        target.mPhone = null;
        target.mPassword = null;
        target.mPasswordChack = null;
        target.mIdentity = null;
        target.birthday = null;
        target.mLogin = null;
        target.mTitlePhone = null;
        target.mTitlePassword = null;
        target.mTitlepasswordChack = null;
        target.mTitleName = null;
        target.mTitleBirthday = null;
        target.mregist = null;
        this.target = null;
    }
}
