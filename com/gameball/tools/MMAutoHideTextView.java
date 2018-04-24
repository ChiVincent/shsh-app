package com.gameball.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;

public class MMAutoHideTextView extends TextView {

    class C03091 implements TextWatcher {
        C03091() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.toString().equals("")) {
                MMAutoHideTextView.this.setVisibility(8);
            } else {
                MMAutoHideTextView.this.setVisibility(0);
            }
        }
    }

    public MMAutoHideTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public MMAutoHideTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MMAutoHideTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        addTextChangedListener(new C03091());
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
