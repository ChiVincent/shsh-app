package com.gameball.school;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.gameball.api.MMFavor;
import com.orhanobut.logger.Logger;
import io.fabric.sdk.android.services.settings.AppSettingsData;

public class MMNoServiceActivity extends Fragment {
    private View mFragmentView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        Logger.m21e("我返回拉", new Object[0]);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (MMFavor.getInstance().account.getOriginalPage().equals(AppSettingsData.STATUS_NEW)) {
            MMMainActivity.gotonews();
        } else if (MMFavor.getInstance().account.getOriginalPage().equals("photo")) {
            MMMainActivity.gotophoto();
        } else if (MMFavor.getInstance().account.getOriginalPage().equals("vip")) {
            MMMainActivity.gotovip();
        } else if (MMFavor.getInstance().account.getOriginalPage().equals("setting")) {
            MMMainActivity.gotosetting();
        }
        super.onCreate(savedInstanceState);
        if (this.mFragmentView == null) {
            this.mFragmentView = inflater.inflate(C0308R.layout.fragment_2, null);
        }
        if (this.mFragmentView.getParent() != null) {
            ((ViewGroup) this.mFragmentView.getParent()).removeAllViews();
        }
        ButterKnife.bind((Object) this, this.mFragmentView);
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
