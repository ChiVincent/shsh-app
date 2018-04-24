package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class BarcodeActivity_ViewBinding<T extends BarcodeActivity> implements Unbinder {
    protected T target;

    @UiThread
    public BarcodeActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mImageViewBarcodeFirst = (ImageView) Utils.findRequiredViewAsType(source, C0308R.id.imageview_first_barcode, "field 'mImageViewBarcodeFirst'", ImageView.class);
        target.mImageViewBarcodeSecond = (ImageView) Utils.findRequiredViewAsType(source, C0308R.id.imageview_second_barcode, "field 'mImageViewBarcodeSecond'", ImageView.class);
        target.mJustifyTextViewFirstBarcode = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.justifytextview_first_barcode, "field 'mJustifyTextViewFirstBarcode'", TextView.class);
        target.mJustifyTextViewSecondBarcode = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.justifytextview_second_barcode, "field 'mJustifyTextViewSecondBarcode'", TextView.class);
        target.mTextViewFirstBarcodeType = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_first_barcode_type, "field 'mTextViewFirstBarcodeType'", TextView.class);
        target.mTextViewSecondBarcodeType = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_second_barcode_type, "field 'mTextViewSecondBarcodeType'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mImageViewBarcodeFirst = null;
        target.mImageViewBarcodeSecond = null;
        target.mJustifyTextViewFirstBarcode = null;
        target.mJustifyTextViewSecondBarcode = null;
        target.mTextViewFirstBarcodeType = null;
        target.mTextViewSecondBarcodeType = null;
        this.target = null;
    }
}
