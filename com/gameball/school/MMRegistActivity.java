package com.gameball.school;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.api.MMFavor;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Response;

public class MMRegistActivity extends Fragment {
    @BindView(2131689644)
    EditText birthday;
    private String classNumber = "";
    private JsonArray jsonAuthorsArray;
    private JsonArray jsonAuthorsArray2;
    private JsonArray jsonAuthorsArray3;
    private JsonArray jsonAuthorsArray4;
    private String juniorclassNumber = "";
    private String junioryearNumber = "";
    private CountDownTimer mCountDownTimer = null;
    private View mFragmentView;
    @BindView(2131689649)
    EditText mIdentity;
    @BindView(2131689650)
    TextView mLogin;
    @BindView(2131689642)
    EditText mName;
    @BindView(2131689627)
    EditText mPassword;
    @BindView(2131689640)
    EditText mPasswordChack;
    @BindView(2131689626)
    EditText mPhone;
    @BindView(2131689645)
    AppCompatSpinner mSpinner;
    @BindView(2131689646)
    AppCompatSpinner mSpinner2;
    @BindView(2131689647)
    AppCompatSpinner mSpinner3;
    @BindView(2131689648)
    AppCompatSpinner mSpinner4;
    @BindView(2131689643)
    TextView mTitleBirthday;
    @BindView(2131689641)
    TextView mTitleName;
    @BindView(2131689638)
    TextView mTitlePassword;
    @BindView(2131689637)
    TextView mTitlePhone;
    @BindView(2131689639)
    TextView mTitlepasswordChack;
    @BindView(2131689651)
    LinearLayout mlinear1;
    @BindView(2131689636)
    View mregist;
    private ArrayList<String> mylist;
    private ArrayList<String> mylist2;
    private ArrayList<String> mylist3;
    private ArrayList<String> mylist4;
    private String yearNumber = "";

    class C03022 implements OnFocusChangeListener {
        C03022() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                MMRegistActivity.this.showDatePickerDialog();
            }
        }
    }

    class C03033 implements OnClickListener {
        C03033() {
        }

        public void onClick(View v) {
            MMRegistActivity.this.showDatePickerDialog();
        }
    }

    class C03046 implements OnClickListener {
        C03046() {
        }

        public void onClick(View v) {
            Fragment newFragment = new MMLoginActivity();
            Fragment new2Fragment = new MMRegistActivity();
            FragmentTransaction transaction = MMRegistActivity.this.getFragmentManager().beginTransaction();
            transaction.replace(C0308R.id.activity_main, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            MMRegistActivity.this.mregist.setVisibility(8);
        }
    }

    class C03057 implements OnClickListener {

        class C09471 extends MMCustomCallBack {
            C09471() {
            }

            public void onResponse() {
            }

            public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                MMFavor.getInstance().account.setToken(responseJsonObject.get("retnObject").getAsJsonObject().get("accToken").getAsString());
                MMFavor.getInstance().account.setUsrGrade(responseJsonObject.get("retnObject").getAsJsonObject().get("usrGrade").getAsString());
                MMFavor.getInstance().account.setExpireTime(responseJsonObject.get("retnObject").getAsJsonObject().get("expireTime").getAsString());
                Intent intent = new Intent();
                intent.setClass(MMRegistActivity.this.getActivity(), MMEmailActivity.class);
                intent.putExtra("TITLE", "regist");
                MMRegistActivity.this.startActivity(intent);
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

        C03057() {
        }

        public void onClick(View view) {
            if (MMRegistActivity.this.mSpinner.getSelectedItem().toString().equals("請選擇")) {
                MMRegistActivity.this.yearNumber = "";
            } else {
                MMRegistActivity.this.yearNumber = MMRegistActivity.this.changeStringYear(MMRegistActivity.this.mSpinner.getSelectedItem().toString());
            }
            if (MMRegistActivity.this.mSpinner2.getSelectedItem().toString().equals("請選擇")) {
                MMRegistActivity.this.classNumber = "";
            } else {
                MMRegistActivity.this.classNumber = MMRegistActivity.this.mSpinner2.getSelectedItem().toString();
            }
            if (MMRegistActivity.this.mSpinner3.getSelectedItem().toString().equals("請選擇")) {
                MMRegistActivity.this.junioryearNumber = "";
            } else {
                MMRegistActivity.this.junioryearNumber = MMRegistActivity.this.changeStringYear(MMRegistActivity.this.mSpinner3.getSelectedItem().toString());
            }
            if (MMRegistActivity.this.mSpinner4.getSelectedItem().toString().equals("請選擇")) {
                MMRegistActivity.this.juniorclassNumber = "";
            } else {
                MMRegistActivity.this.juniorclassNumber = MMRegistActivity.this.mSpinner4.getSelectedItem().toString();
            }
            MMAPI.getInstance(MMRegistActivity.this.getActivity()).getSMSToken(MMRegistActivity.this.mPhone.getText().toString(), MMRegistActivity.this.mPassword.getText().toString(), MMRegistActivity.this.mPasswordChack.getText().toString(), MMRegistActivity.this.birthday.getText().toString(), MMRegistActivity.this.mName.getText().toString(), MMRegistActivity.this.yearNumber, MMRegistActivity.this.classNumber, MMRegistActivity.this.junioryearNumber, MMRegistActivity.this.juniorclassNumber, MMRegistActivity.this.mIdentity.getText().toString(), new C09471());
        }
    }

    class C03068 implements OnDateSetListener {
        C03068() {
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            MMRegistActivity.this.birthday.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    }

    class C07211 implements SingleButtonCallback {
        C07211() {
        }

        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            MMMainActivity.gotonews();
        }
    }

    class C09454 extends MMCustomCallBack {
        C09454() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            int i;
            MMRegistActivity.this.jsonAuthorsArray = responseJsonObject.get("retnObject").getAsJsonObject().get("year").getAsJsonArray();
            for (i = 0; i < MMRegistActivity.this.jsonAuthorsArray.size(); i++) {
                MMRegistActivity.this.mylist.add("第" + (i + 1) + "屆(民國" + (Integer.valueOf(MMRegistActivity.this.jsonAuthorsArray.get(i).getAsString()).intValue() - 1911) + "年)");
            }
            MMRegistActivity.this.jsonAuthorsArray2 = responseJsonObject.get("retnObject").getAsJsonObject().get("class").getAsJsonArray();
            for (i = 0; i < MMRegistActivity.this.jsonAuthorsArray2.size(); i++) {
                MMRegistActivity.this.mylist2.add(MMRegistActivity.this.jsonAuthorsArray2.get(i).getAsString());
            }
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

    class C09465 extends MMCustomCallBack {
        C09465() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            int i;
            MMRegistActivity.this.jsonAuthorsArray3 = responseJsonObject.get("retnObject").getAsJsonObject().get("year").getAsJsonArray();
            for (i = 0; i < MMRegistActivity.this.jsonAuthorsArray3.size(); i++) {
                MMRegistActivity.this.mylist3.add("第" + (i + 1) + "屆(民國" + (Integer.valueOf(MMRegistActivity.this.jsonAuthorsArray3.get(i).getAsString()).intValue() - 1911) + "年)");
            }
            MMRegistActivity.this.jsonAuthorsArray4 = responseJsonObject.get("retnObject").getAsJsonObject().get("class").getAsJsonArray();
            for (i = 0; i < MMRegistActivity.this.jsonAuthorsArray4.size(); i++) {
                MMRegistActivity.this.mylist4.add(MMRegistActivity.this.jsonAuthorsArray4.get(i).getAsString());
            }
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        Logger.m21e("我返回拉", new Object[0]);
        this.mregist.setVisibility(0);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mFragmentView == null) {
            this.mFragmentView = inflater.inflate(C0308R.layout.activity_regist, null);
        }
        if (this.mFragmentView.getParent() != null) {
            ((ViewGroup) this.mFragmentView.getParent()).removeAllViews();
        }
        ButterKnife.bind((Object) this, this.mFragmentView);
        this.mTitlePhone.setText(Html.fromHtml("<font color=\"#ff0000\"><b>*</b></font>行動電話"));
        this.mTitlePassword.setText(Html.fromHtml("<font color=\"#ff0000\"><b>*</b></font>會員密碼"));
        this.mTitlepasswordChack.setText(Html.fromHtml("<font color=\"#ff0000\"><b>*</b></font>密碼確認"));
        this.mTitleName.setText(Html.fromHtml("<font color=\"#ff0000\"><b>*</b></font>姓名"));
        this.mTitleBirthday.setText(Html.fromHtml("<font color=\"#ff0000\"><b>*</b></font>生日"));
        Logger.m21e("test55:" + MMFavor.getInstance().account.getToken(), new Object[0]);
        if (!(MMFavor.getInstance().account.getToken() == null || MMFavor.getInstance().account.getToken().equals(""))) {
            new Builder(getActivity()).content((int) C0308R.string.login_hint).positiveColorRes(C0308R.color.colorPrimary).positiveText((int) C0308R.string.know).cancelable(false).onPositive(new C07211()).show();
        }
        this.birthday.setInputType(0);
        this.birthday.setOnFocusChangeListener(new C03022());
        this.birthday.setOnClickListener(new C03033());
        this.mylist = new ArrayList();
        this.mylist.add("請選擇");
        this.mylist2 = new ArrayList();
        this.mylist2.add("請選擇");
        MMAPI.getInstance().getGraduationYear("senior", new C09454());
        SpinnerAdapter adapter = new ArrayAdapter(getActivity(), 17367048, this.mylist);
        adapter.setDropDownViewResource(17367049);
        this.mSpinner.setAdapter(adapter);
        SpinnerAdapter adapter2 = new ArrayAdapter(getActivity(), 17367048, this.mylist2);
        adapter2.setDropDownViewResource(17367049);
        this.mSpinner2.setAdapter(adapter2);
        this.mylist3 = new ArrayList();
        this.mylist3.add("請選擇");
        this.mylist4 = new ArrayList();
        this.mylist4.add("請選擇");
        MMAPI.getInstance().getGraduationYear("junior", new C09465());
        SpinnerAdapter adapter3 = new ArrayAdapter(getActivity(), 17367048, this.mylist3);
        adapter3.setDropDownViewResource(17367049);
        this.mSpinner3.setAdapter(adapter3);
        SpinnerAdapter adapter4 = new ArrayAdapter(getActivity(), 17367048, this.mylist4);
        adapter4.setDropDownViewResource(17367049);
        this.mSpinner4.setAdapter(adapter4);
        this.mLogin.setOnClickListener(new C03046());
        this.mlinear1.setOnClickListener(new C03057());
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

    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(getActivity(), new C03068(), c.get(1), c.get(2), c.get(5)).show();
    }

    private String changeStringYear(String text) {
        int searchstrat = text.indexOf("國");
        return String.valueOf(Integer.valueOf(text.substring(searchstrat + 1, text.indexOf("年"))).intValue() + 1911);
    }
}
