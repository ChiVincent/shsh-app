package com.gameball.school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.api.MMFavor;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import retrofit2.Call;
import retrofit2.Response;

public class MMEmailActivity extends Activity {
    static String TITLE = "";
    @BindView(2131689608)
    EditText EditTextEmail;
    @BindView(2131689605)
    View imageView;
    @BindView(2131689606)
    View imageView2;
    @BindView(2131689610)
    View mViewFooter;
    @BindView(2131689607)
    TextView texthint;

    class C09241 extends MMCustomCallBack {
        C09241() {
        }

        public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
            return false;
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            MMFavor.getInstance().account.setEmail(MMEmailActivity.this.EditTextEmail.getText().toString());
            MMEmailActivity.this.startActivity(new Intent(MMEmailActivity.this, MMEmailAccomplishedActivity.class));
            MMEmailActivity.this.finish();
        }

        public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            return false;
        }

        public void onFinish() {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        int i = 8;
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_email);
        ButterKnife.bind((Activity) this);
        TITLE = getIntent().getStringExtra("TITLE");
        Logger.m21e("TITLE" + TITLE, new Object[0]);
        if (TITLE.equals(Event.LOGIN)) {
            this.imageView.setVisibility(8);
            this.imageView2.setVisibility(0);
            this.texthint.setText("恭喜你登入成功，建議您也完成Email綁定，當手機門號更換或遺失時，您就可以用來取回您的帳戶喔！");
        } else {
            this.imageView2.setVisibility(8);
            this.imageView.setVisibility(0);
        }
        View view = this.mViewFooter;
        if (!getIntent().getBooleanExtra("HIDE_FOOTER", false)) {
            i = 0;
        }
        view.setVisibility(i);
    }

    public void next(View view) {
        MMApplication.sendGoogleAnalyticsEvent("regist", "home");
        finish();
    }

    public void cancel(View view) {
        startActivity(new Intent(this, MMMainActivity.class));
        finish();
    }

    public void bindEmail(View view) {
        MMAPI.getInstance(this).setEmail(this.EditTextEmail.getText().toString(), new C09241());
    }
}
