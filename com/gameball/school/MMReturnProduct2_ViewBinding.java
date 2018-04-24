package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMReturnProduct2_ViewBinding<T extends MMReturnProduct2> implements Unbinder {
    protected T target;

    @UiThread
    public MMReturnProduct2_ViewBinding(T target, View source) {
        this.target = target;
        target.mImageView = (ImageView) Utils.findRequiredViewAsType(source, C0308R.id.imageview, "field 'mImageView'", ImageView.class);
        target.mTextViewTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_title, "field 'mTextViewTitle'", TextView.class);
        target.mTextViewSubTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_subtitle, "field 'mTextViewSubTitle'", TextView.class);
        target.mTextViewQuantity = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_quantity, "field 'mTextViewQuantity'", TextView.class);
        target.mTextViewOrderId = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_order_id, "field 'mTextViewOrderId'", TextView.class);
        target.mTextViewUnit = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_unit, "field 'mTextViewUnit'", TextView.class);
        target.mTextViewMoney = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_money, "field 'mTextViewMoney'", TextView.class);
        target.mTextViewSpecification = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_specification, "field 'mTextViewSpecification'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mImageView = null;
        target.mTextViewTitle = null;
        target.mTextViewSubTitle = null;
        target.mTextViewQuantity = null;
        target.mTextViewOrderId = null;
        target.mTextViewUnit = null;
        target.mTextViewMoney = null;
        target.mTextViewSpecification = null;
        this.target = null;
    }
}
