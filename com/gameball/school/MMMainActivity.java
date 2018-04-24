package com.gameball.school;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.gameball.api.MMFavor;
import com.gameball.api.MMToast;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.orhanobut.logger.Logger;
import io.fabric.sdk.android.services.settings.AppSettingsData;

public class MMMainActivity extends FragmentActivity {
    public static RadioGroup mRadioGroup;
    private Boolean Feedbackbool;
    private int Feedbacknum = 0;
    private Boolean Photobool;
    private int Photonum = 0;
    private Boolean Settingbool;
    private int Settingnum = 0;
    private Boolean Vipbool;
    private int Vipnum = 0;
    private Class[] cls = new Class[]{MMFragment1ContactService.class, MMFragment2Event.class, MMFragment3NowOnSale.class, MMRegistActivity.class, MMFragment5Setting.class, MMNoServiceActivity.class};
    private FragmentManager mFragmentManager;
    private InputMethodManager mInputMethodManager;
    private FragmentTabHost mTabHost;
    private MMEmailFragment mmEmailFragment = null;
    private String[] tabs = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6"};

    class C02911 implements OnLongClickListener {
        C02911() {
        }

        public boolean onLongClick(View v) {
            MMFavor.getInstance().clean();
            MMToast.showTextToast("已登出！！！");
            return true;
        }
    }

    class C02922 implements OnClickListener {
        C02922() {
        }

        public void onClick(View v) {
            if (MMMainActivity.this.Feedbackbool.booleanValue()) {
                Logger.m21e("我是第二次點擊", new Object[0]);
                MMFavor.getInstance().account.setOriginalPage(AppSettingsData.STATUS_NEW);
                MMFavor.getInstance().account.setNewReappear("Y");
                MMMainActivity.mRadioGroup.check(C0308R.id.nowork);
            }
            MMMainActivity.this.Feedbackbool = Boolean.valueOf(true);
            MMMainActivity.this.Feedbacknum = MMMainActivity.this.Feedbacknum + 1;
        }
    }

    class C02933 implements OnClickListener {
        C02933() {
        }

        public void onClick(View v) {
            if (MMMainActivity.this.Photobool.booleanValue()) {
                MMFavor.getInstance().account.setOriginalPage("photo");
                MMFavor.getInstance().account.setPhotoReappear("Y");
                MMMainActivity.mRadioGroup.check(C0308R.id.nowork);
            }
            MMMainActivity.this.Photobool = Boolean.valueOf(true);
            MMMainActivity.this.Photonum = MMMainActivity.this.Photonum + 1;
        }
    }

    class C02944 implements OnClickListener {
        C02944() {
        }

        public void onClick(View v) {
            if (MMMainActivity.this.Vipbool.booleanValue()) {
                MMFavor.getInstance().account.setOriginalPage("vip");
                MMFavor.getInstance().account.setVipReappear("Y");
                MMMainActivity.mRadioGroup.check(C0308R.id.nowork);
            }
            MMMainActivity.this.Vipbool = Boolean.valueOf(true);
            MMMainActivity.this.Vipnum = MMMainActivity.this.Vipnum + 1;
        }
    }

    class C02955 implements OnClickListener {
        C02955() {
        }

        public void onClick(View v) {
            if (MMMainActivity.this.Settingbool.booleanValue()) {
                MMFavor.getInstance().account.setOriginalPage("setting");
                MMFavor.getInstance().account.setSettingReappear("Y");
                MMMainActivity.mRadioGroup.check(C0308R.id.nowork);
            }
            MMMainActivity.this.Settingbool = Boolean.valueOf(true);
            MMMainActivity.this.Settingnum = MMMainActivity.this.Settingnum + 1;
        }
    }

    class C02966 implements OnCheckedChangeListener {
        C02966() {
        }

        public void onCheckedChanged(RadioGroup group, int checkedId) {
            MMMainActivity.this.getWindow().setSoftInputMode(16);
            switch (checkedId) {
                case C0308R.id.radiobutton_feedback:
                    MMMainActivity.this.mFragmentManager.popBackStack(null, 1);
                    MMMainActivity.this.mTabHost.setCurrentTabByTag(MMMainActivity.this.tabs[0]);
                    if (MMMainActivity.this.Feedbacknum == 0) {
                        MMMainActivity.this.Feedbackbool = Boolean.valueOf(false);
                        MMFavor.getInstance().account.setNewReappear("N");
                    }
                    MMMainActivity.this.Photonum = 0;
                    MMMainActivity.this.Vipnum = 0;
                    MMMainActivity.this.Settingnum = 0;
                    return;
                case C0308R.id.radiobutton_events:
                    MMMainActivity.this.mFragmentManager.popBackStack(null, 1);
                    MMMainActivity.this.mTabHost.setCurrentTabByTag(MMMainActivity.this.tabs[1]);
                    if (MMMainActivity.this.Photonum == 0) {
                        MMMainActivity.this.Photobool = Boolean.valueOf(false);
                        MMFavor.getInstance().account.setPhotoReappear("N");
                    }
                    MMMainActivity.this.Feedbacknum = 0;
                    MMMainActivity.this.Vipnum = 0;
                    MMMainActivity.this.Settingnum = 0;
                    return;
                case C0308R.id.radiobutton_preferential:
                    MMMainActivity.this.mFragmentManager.popBackStack(null, 1);
                    MMMainActivity.this.mTabHost.setCurrentTabByTag(MMMainActivity.this.tabs[2]);
                    if (MMMainActivity.this.Vipnum == 0) {
                        MMMainActivity.this.Vipbool = Boolean.valueOf(false);
                        MMFavor.getInstance().account.setVipReappear("N");
                    }
                    MMMainActivity.this.Feedbacknum = 0;
                    MMMainActivity.this.Photonum = 0;
                    MMMainActivity.this.Settingnum = 0;
                    return;
                case C0308R.id.radiobutton_order:
                    MMMainActivity.this.mFragmentManager.popBackStack(null, 1);
                    MMMainActivity.this.mTabHost.setCurrentTabByTag(MMMainActivity.this.tabs[3]);
                    MMMainActivity.this.Feedbacknum = 0;
                    MMMainActivity.this.Photonum = 0;
                    MMMainActivity.this.Vipnum = 0;
                    MMMainActivity.this.Settingnum = 0;
                    return;
                case C0308R.id.radiobutton_setting:
                    MMMainActivity.this.mFragmentManager.popBackStack(null, 1);
                    MMMainActivity.this.mTabHost.setCurrentTabByTag(MMMainActivity.this.tabs[4]);
                    if (MMMainActivity.this.Settingnum == 0) {
                        MMMainActivity.this.Settingbool = Boolean.valueOf(false);
                        MMFavor.getInstance().account.setSettingReappear("N");
                    }
                    MMMainActivity.this.Feedbacknum = 0;
                    MMMainActivity.this.Photonum = 0;
                    MMMainActivity.this.Vipnum = 0;
                    return;
                case C0308R.id.nowork:
                    MMMainActivity.this.mFragmentManager.popBackStack(null, 1);
                    MMMainActivity.this.mTabHost.setCurrentTabByTag(MMMainActivity.this.tabs[5]);
                    return;
                default:
                    return;
            }
        }
    }

    class C02977 implements Runnable {
        C02977() {
        }

        public void run() {
            MMMainActivity.mRadioGroup.check(C0308R.id.radiobutton_feedback);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_main);
        this.mInputMethodManager = (InputMethodManager) getSystemService("input_method");
        this.mFragmentManager = getSupportFragmentManager();
        this.mTabHost = (FragmentTabHost) findViewById(16908306);
        this.mTabHost.setup(this, this.mFragmentManager, 16908305);
        this.mTabHost.getTabWidget().setVisibility(8);
        for (int i = 0; i < this.tabs.length; i++) {
            this.mTabHost.addTab(this.mTabHost.newTabSpec(this.tabs[i]).setIndicator(this.tabs[i]), this.cls[i], null);
        }
        this.mmEmailFragment = new MMEmailFragment();
        mRadioGroup = (RadioGroup) findViewById(C0308R.id.main_radiogroup);
        RadioButton feedback = (RadioButton) findViewById(C0308R.id.radiobutton_feedback);
        RadioButton photo = (RadioButton) findViewById(C0308R.id.radiobutton_events);
        RadioButton vip = (RadioButton) findViewById(C0308R.id.radiobutton_preferential);
        RadioButton setting = (RadioButton) findViewById(C0308R.id.radiobutton_setting);
        ((RadioButton) findViewById(C0308R.id.radiobutton_order)).setOnLongClickListener(new C02911());
        feedback.setOnClickListener(new C02922());
        photo.setOnClickListener(new C02933());
        vip.setOnClickListener(new C02944());
        setting.setOnClickListener(new C02955());
        mRadioGroup.setOnCheckedChangeListener(new C02966());
        mRadioGroup.check(getIntent().getIntExtra("CHECK", C0308R.id.radiobutton_feedback));
    }

    protected void onResume() {
        super.onResume();
        Logger.m21e("onResume", new Object[0]);
        Logger.m21e(FirebaseInstanceId.getInstance().getToken(), new Object[0]);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.m21e(intent.getScheme(), new Object[0]);
        Logger.m21e(intent.getDataString(), new Object[0]);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MMProductDetail.REQUEST_CODE_PURCHASE_PRODUCT) {
            if (resultCode == -1) {
                mRadioGroup.post(new C02977());
            } else if (resultCode == 0) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public static void gotonews() {
        mRadioGroup.check(C0308R.id.radiobutton_feedback);
    }

    public static void gotophoto() {
        mRadioGroup.check(C0308R.id.radiobutton_events);
    }

    public static void gotovip() {
        mRadioGroup.check(C0308R.id.radiobutton_preferential);
    }

    public static void gotosetting() {
        mRadioGroup.check(C0308R.id.radiobutton_setting);
    }
}
