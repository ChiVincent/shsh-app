package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gameball.tools.MMNumberView;

public class MMExchangeActivity_ViewBinding<T extends MMExchangeActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMExchangeActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mImageView = (ImageView) Utils.findRequiredViewAsType(source, C0308R.id.imageview, "field 'mImageView'", ImageView.class);
        target.mTextViewTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_title, "field 'mTextViewTitle'", TextView.class);
        target.mTextViewSubTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_subtitle, "field 'mTextViewSubTitle'", TextView.class);
        target.mTextViewRemain = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_remain, "field 'mTextViewRemain'", TextView.class);
        target.mTextViewTotalMoney = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_total_money, "field 'mTextViewTotalMoney'", TextView.class);
        target.mTextViewOrderId = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_order_id, "field 'mTextViewOrderId'", TextView.class);
        target.mTextViewUnitName = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_unit_name, "field 'mTextViewUnitName'", TextView.class);
        target.mmNumberView = (MMNumberView) Utils.findRequiredViewAsType(source, C0308R.id.number_view, "field 'mmNumberView'", MMNumberView.class);
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
        target.mTextViewRemain = null;
        target.mTextViewTotalMoney = null;
        target.mTextViewOrderId = null;
        target.mTextViewUnitName = null;
        target.mmNumberView = null;
        target.mTextViewSpecification = null;
        this.target = null;
    }
}
