package com.gameball.school;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MMVersionInfoActivity extends Activity {
    @BindView(2131689658)
    TextView mTextViewVersionInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_version_info);
        ButterKnife.bind((Activity) this);
        this.mTextViewVersionInfo.setText("V1.0.0");
    }
}
