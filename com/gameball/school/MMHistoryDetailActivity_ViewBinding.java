package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMHistoryDetailActivity_ViewBinding<T extends MMHistoryDetailActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMHistoryDetailActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mImageView = (ImageView) Utils.findRequiredViewAsType(source, C0308R.id.imageview, "field 'mImageView'", ImageView.class);
        target.mTextViewTitleBar = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_title_bar, "field 'mTextViewTitleBar'", TextView.class);
        target.mTextViewTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_title, "field 'mTextViewTitle'", TextView.class);
        target.mTextViewSubTitle = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_subtitle, "field 'mTextViewSubTitle'", TextView.class);
        target.mTextViewSpecification = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_specification, "field 'mTextViewSpecification'", TextView.class);
        target.mViewArchive = Utils.findRequiredView(source, C0308R.id.view_archive, "field 'mViewArchive'");
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mImageView = null;
        target.mTextViewTitleBar = null;
        target.mTextViewTitle = null;
        target.mTextViewSubTitle = null;
        target.mTextViewSpecification = null;
        target.mViewArchive = null;
        this.target = null;
    }
}
