package com.gameball.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.gameball.school.C0308R;

public class MMNumberView extends LinearLayout {
    ImageView f13A;
    ImageView f14B;
    private int mNumbr = 0;
    private int maxNumber = 99;
    private String maxWarning = "";
    private int minNumber = 1;
    private String minWarning = "";
    private int[] numbers = new int[]{C0308R.drawable.btn_number_0, C0308R.drawable.btn_number_1, C0308R.drawable.btn_number_2, C0308R.drawable.btn_number_3, C0308R.drawable.btn_number_4, C0308R.drawable.btn_number_5, C0308R.drawable.btn_number_6, C0308R.drawable.btn_number_7, C0308R.drawable.btn_number_8, C0308R.drawable.btn_number_9};

    public MMNumberView(Context context) {
        super(context);
        init(null, 0);
    }

    public MMNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MMNumberView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0308R.styleable.MMNumberView, defStyle, 0);
        this.mNumbr = a.getInt(6, this.mNumbr);
        this.maxWarning = a.getString(1);
        this.minWarning = a.getString(2);
        a.recycle();
        this.f13A = new ImageView(getContext());
        this.f14B = new ImageView(getContext());
        updateNumber();
        addView(this.f13A);
        addView(this.f14B);
    }

    private void updateNumber() {
        if (this.mNumbr > this.maxNumber && this.maxWarning != null && this.maxWarning.length() > 0) {
            showProgressDialog(this.maxWarning);
        }
        if (this.mNumbr < this.minNumber && this.minWarning != null && this.minWarning.length() > 0) {
            showProgressDialog(this.minWarning);
        }
        this.mNumbr = this.mNumbr > this.maxNumber ? this.maxNumber : this.mNumbr;
        this.mNumbr = this.mNumbr < this.minNumber ? this.minNumber : this.mNumbr;
        this.f13A.setImageResource(this.numbers[this.mNumbr / 10]);
        this.f14B.setImageResource(this.numbers[this.mNumbr % 10]);
    }

    private void showProgressDialog(String string) {
        new Builder(getContext()).positiveText((int) C0308R.string.confirm).title((CharSequence) "提示").content((CharSequence) string).build().show();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void increase() {
        this.mNumbr++;
        updateNumber();
    }

    public void reduce() {
        this.mNumbr--;
        updateNumber();
    }

    public int getNumber() {
        return this.mNumbr;
    }

    public void setMaxNumber(int MAX_NUMBER) {
        this.maxNumber = MAX_NUMBER;
    }
}
