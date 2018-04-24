package com.gameball.school;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMFavor;

public class MMEmailAccomplishedActivity extends Activity {
    @BindView(2131689612)
    ImageView mImageViewLine;
    @BindView(2131689611)
    TextView mTextViewEmail;

    class C02721 implements OnClickListener {
        C02721() {
        }

        public void onClick(View view) {
            MMEmailAccomplishedActivity.this.line(view);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_email_accomplished);
        ButterKnife.bind((Activity) this);
        this.mImageViewLine.setOnClickListener(new C02721());
        this.mTextViewEmail.setText(MMFavor.getInstance().account.getEmail());
    }

    public void next(View view) {
        startActivity(new Intent(this, MMMainActivity.class));
        finish();
    }

    public void line(View view) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse("https://line.me/R/ti/p/%40hqb3295j"));
        startActivity(i);
    }
}
