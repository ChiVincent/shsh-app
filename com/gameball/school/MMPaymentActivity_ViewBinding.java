package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMPaymentActivity_ViewBinding<T extends MMPaymentActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMPaymentActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mImageView = (ImageView) Utils.findRequiredViewAsType(source, C0308R.id.imageview, "field 'mImageView'", ImageView.class);
        target.mTextViewTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_title, "field 'mTextViewTitle'", TextView.class);
        target.mTextViewSubTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_subtitle, "field 'mTextViewSubTitle'", TextView.class);
        target.mTextViewSubTitle2 = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_subtitle2, "field 'mTextViewSubTitle2'", TextView.class);
        target.mTextViewSubTitle3 = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_subtitle3, "field 'mTextViewSubTitle3'", TextView.class);
        target.mTextViewListPrice = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_list_price, "field 'mTextViewListPrice'", TextView.class);
        target.mTextViewAmount = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_amount, "field 'mTextViewAmount'", TextView.class);
        target.mTextViewQuantity = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_quantity, "field 'mTextViewQuantity'", TextView.class);
        target.mTextViewTotalMoney = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_total_money, "field 'mTextViewTotalMoney'", TextView.class);
        target.mTextViewOrderId = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_order_id, "field 'mTextViewOrderId'", TextView.class);
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
        target.mTextViewSubTitle2 = null;
        target.mTextViewSubTitle3 = null;
        target.mTextViewListPrice = null;
        target.mTextViewAmount = null;
        target.mTextViewQuantity = null;
        target.mTextViewTotalMoney = null;
        target.mTextViewOrderId = null;
        target.mTextViewSpecification = null;
        this.target = null;
    }
}
