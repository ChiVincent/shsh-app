package com.gameball.school;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.api.MMFavor;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Response;

public class MMSmsAuthenticationActivity extends Activity {
    @BindView(2131689656)
    EditText EditTextCode;
    @BindView(2131689657)
    TextView TextViewSendRemain;
    @BindView(2131689609)
    TextView TextViewSendSms;

    class C09511 extends MMCustomCallBack {
        C09511() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            JsonObject retnObject = responseJsonObject.get("retnObject").getAsJsonObject();
            MMFavor.getInstance().account.setToken(responseJsonObject.get("retnObject").getAsJsonObject().get("accToken").getAsString());
            if (!retnObject.has("userEmail") || retnObject.get("userEmail").getAsString().length() <= 0 || retnObject.get("userEmail").getAsString().equals("false")) {
                MMSmsAuthenticationActivity.this.startActivity(new Intent(MMSmsAuthenticationActivity.this, MMEmailActivity.class));
            } else {
                MMSmsAuthenticationActivity.this.finish();
            }
            MMSmsAuthenticationActivity.this.finish();
        }

        public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            return false;
        }

        public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
            return false;
        }

        public void onFinish() {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_sms_authentication);
        ButterKnife.bind((Activity) this);
    }

    public void back(View view) {
        MMApplication.sendGoogleAnalyticsEvent("regist", "back");
        startActivity(new Intent(this, MMRegistActivity.class));
        finish();
    }

    public void next(View view) {
        MMApplication.sendGoogleAnalyticsEvent("regist", "verify");
        MMAPI.getInstance(this).getAccountToken(this.EditTextCode.getText().toString(), new C09511());
    }

    public void contactService(View view) {
        startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:0223780371")));
    }

    public void reSendSms(View view) {
        MMApplication.sendGoogleAnalyticsEvent("regist", "resent");
        this.TextViewSendSms.setVisibility(8);
        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                MMSmsAuthenticationActivity.this.TextViewSendRemain.setText((millisUntilFinished / 1000) + MMSmsAuthenticationActivity.this.getResources().getString(C0308R.string.seconds_to_sent));
            }

            public void onFinish() {
                MMSmsAuthenticationActivity.this.TextViewSendSms.setVisibility(0);
            }
        }.start();
    }
}
