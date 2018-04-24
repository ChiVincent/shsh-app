package com.gameball.school;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MMAboutNoteActivity_ViewBinding<T extends MMAboutNoteActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MMAboutNoteActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.mTextViewServiceNote = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_service_note, "field 'mTextViewServiceNote'", TextView.class);
        target.textview_title = (TextView) Utils.findRequiredViewAsType(source, C0308R.id.textview_title, "field 'textview_title'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.mTextViewServiceNote = null;
        target.textview_title = null;
        this.target = null;
    }
}
