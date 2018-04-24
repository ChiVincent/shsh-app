package com.gameball.school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.api.MMFavor;
import com.gameball.api.MMToast;
import com.gameball.tools.BadgeUtil;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Response;

public class MMOpeningActivity extends Activity {
    private int NEXT_PAGE = 0;
    private final int NEXT_PAGE_FINISH = 0;
    private final int NEXT_PAGE_LOGIN = 1;
    private final int NEXT_PAGE_MAIN = 2;
    private Call<JsonObject> mCAll = null;
    private CountDownTimer mCountDownTimer = null;

    class C09432 extends MMCustomCallBack {
        C09432() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            MMFavor.getInstance().account.setToken(responseJsonObject.get("retnObject").getAsJsonObject().get("accToken").getAsString());
            MMFavor.getInstance().account.setUsrGrade(responseJsonObject.get("retnObject").getAsJsonObject().get("usrGrade").getAsString());
            MMOpeningActivity.this.NEXT_PAGE = 2;
        }

        public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            MMOpeningActivity.this.NEXT_PAGE = 1;
            MMFavor.getInstance().clean();
            return false;
        }

        public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
            MMOpeningActivity.this.NEXT_PAGE = 0;
            return true;
        }

        public void onFinish() {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_opening);
        BadgeUtil.setBadgeCount(getApplicationContext(), 1);
        this.mCountDownTimer = new CountDownTimer(1500, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                MMOpeningActivity.this.next(null);
            }
        }.start();
        if (MMFavor.getInstance().account.getToken() == null || MMFavor.getInstance().account.getToken().equals("")) {
            this.NEXT_PAGE = 1;
        } else {
            this.mCAll = MMAPI.getInstance().getUpdateToken(new C09432());
        }
        this.NEXT_PAGE = 1;
    }

    public void next(View view) {
        switch (this.NEXT_PAGE) {
            case 0:
                MMToast.showTextToast(MMApplication.getAppContext().getString(C0308R.string.connection_fail));
                break;
            case 1:
                startActivity(new Intent(this, MMMainActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, MMMainActivity.class));
                break;
        }
        finish();
    }

    public void finish() {
        if (!(this.mCAll == null || this.mCAll.isCanceled())) {
            this.mCAll.cancel();
        }
        this.mCountDownTimer.cancel();
        super.finish();
    }
}
