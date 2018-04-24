package com.gameball.school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMFavor;

public class MMEmailAlreadyAccomplishedActivity extends Activity {
    @BindView(2131689611)
    TextView mTextViewEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_email_alreadt_accomplished);
        ButterKnife.bind((Activity) this);
        this.mTextViewEmail.setText(getString(C0308R.string.bind_already_accomplished_hint) + MMFavor.getInstance().setting.getEmail() + getString(C0308R.string.bind_already_accomplished_hint2));
    }

    public void next(View view) {
        startActivity(new Intent(this, MMMainActivity.class));
        finish();
    }
}
