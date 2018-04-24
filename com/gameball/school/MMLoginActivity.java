package com.gameball.school;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.api.MMFavor;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Response;

public class MMLoginActivity extends Fragment {
    private String classNumber = "";
    JsonArray jsonAuthorsArray;
    private CountDownTimer mCountDownTimer = null;
    private View mFragmentView;
    @BindView(2131689627)
    EditText mPassword;
    @BindView(2131689626)
    EditText mPhone;
    @BindView(2131689629)
    LinearLayout mlinear1;
    @BindView(2131689625)
    View mlogin;
    private ArrayList<String> mylist;
    private ArrayList<String> mylist2;
    @BindView(2131689628)
    TextView regist;
    private String yearNumber = "";

    class C02891 implements OnClickListener {
        C02891() {
        }

        public void onClick(View v) {
            Fragment newFragment = new MMLoginActivity();
            Fragment new2Fragment = new MMRegistActivity();
            FragmentTransaction transaction = MMLoginActivity.this.getFragmentManager().beginTransaction();
            transaction.replace(C0308R.id.activity_main, new2Fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            MMLoginActivity.this.mlogin.setVisibility(8);
        }
    }

    class C02902 implements OnClickListener {

        class C09381 extends MMCustomCallBack {
            C09381() {
            }

            public void onResponse() {
            }

            public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                MMFavor.getInstance().account.setToken(responseJsonObject.get("retnObject").getAsJsonObject().get("accToken").getAsString());
                MMFavor.getInstance().account.setUsrGrade(responseJsonObject.get("retnObject").getAsJsonObject().get("usrGrade").getAsString());
                MMFavor.getInstance().account.setExpireTime(responseJsonObject.get("retnObject").getAsJsonObject().get("expireTime").getAsString());
                String title = Event.LOGIN;
                if (responseJsonObject.get("retnObject").getAsJsonObject().get("isBundleEmail").getAsString().equals("0")) {
                    Intent intent = new Intent();
                    intent.setClass(MMLoginActivity.this.getActivity(), MMEmailActivity.class);
                    intent.putExtra("TITLE", title);
                    MMLoginActivity.this.startActivity(intent);
                    return;
                }
                MMLoginActivity.this.startActivity(new Intent(MMLoginActivity.this.getActivity(), MMMainActivity.class));
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

        C02902() {
        }

        public void onClick(View view) {
            Logger.m21e("mPhone:" + MMLoginActivity.this.mPhone.getText().toString() + "mPassword:" + MMLoginActivity.this.mPassword.getText().toString(), new Object[0]);
            MMAPI.getInstance(MMLoginActivity.this.getActivity()).getLogin(MMLoginActivity.this.mPhone.getText().toString(), MMLoginActivity.this.mPassword.getText().toString(), new C09381());
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this.mFragmentView == null) {
            this.mFragmentView = inflater.inflate(C0308R.layout.activity_login, null);
        }
        if (this.mFragmentView.getParent() != null) {
            ((ViewGroup) this.mFragmentView.getParent()).removeAllViews();
        }
        ButterKnife.bind((Object) this, this.mFragmentView);
        this.regist.setOnClickListener(new C02891());
        this.mlinear1.setOnClickListener(new C02902());
        return this.mFragmentView;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public static void startActivity() {
    }
}
