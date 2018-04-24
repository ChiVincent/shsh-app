package com.gameball.school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.tools.MMTool;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Response;

public class MMHistoryDetailActivity extends Activity {
    static String PRODUCT;
    public static int REQUEST_CODE_HISTORY_DETAIL = 371;
    private JsonObject TYPE = new JsonObject();
    @BindView(2131689614)
    ImageView mImageView;
    JsonObject mJsonObjectProduct;
    @BindView(2131689621)
    TextView mTextViewSpecification;
    @BindView(2131689615)
    TextView mTextViewSubTitle;
    @BindView(2131689613)
    TextView mTextViewTitle;
    @BindView(2131689623)
    TextView mTextViewTitleBar;
    @BindView(2131689624)
    View mViewArchive;

    class C07181 implements SingleButtonCallback {

        class C09351 extends MMCustomCallBack {
            C09351() {
            }

            public void onResponse() {
            }

            public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                MMHistoryDetailActivity.this.finish();
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

        C07181() {
        }

        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            MMAPI.getInstance(MMHistoryDetailActivity.this).setToArchive(MMHistoryDetailActivity.this.mJsonObjectProduct.get("orderID").getAsString(), new C09351());
        }
    }

    public static void startActivity(Fragment fragment, JsonObject product) {
        Intent intent = new Intent(fragment.getContext(), MMHistoryDetailActivity.class);
        intent.putExtra(PRODUCT, product.toString());
        fragment.startActivityForResult(intent, REQUEST_CODE_HISTORY_DETAIL);
    }

    public static void startActivity(Activity activity, JsonObject product) {
        Intent intent = new Intent(activity, MMHistoryDetailActivity.class);
        intent.putExtra(PRODUCT, product.toString());
        activity.startActivityForResult(intent, REQUEST_CODE_HISTORY_DETAIL);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_history_detail);
        ButterKnife.bind((Activity) this);
        this.mJsonObjectProduct = new JsonParser().parse(getIntent().getStringExtra(PRODUCT)).getAsJsonObject();
        Logger.json(this.mJsonObjectProduct.toString());
        if (this.mJsonObjectProduct.has("prodImage")) {
            Picasso.with(this).load(this.mJsonObjectProduct.get("prodImage").getAsString()).into(this.mImageView);
        } else {
            this.mImageView.setVisibility(4);
        }
        this.mTextViewTitle.setText(this.mJsonObjectProduct.get("prodTitle").getAsString());
        this.mTextViewSubTitle.setText(this.mJsonObjectProduct.get("subTitle").getAsString());
        this.TYPE.addProperty("1", "未付款");
        this.TYPE.addProperty("4", "已過期");
        this.TYPE.addProperty("7", "已過期");
        this.TYPE.addProperty("8", "已兌換完畢");
        this.TYPE.addProperty("9", "已退貨");
        if (this.mJsonObjectProduct.has("returnAccess") && this.mJsonObjectProduct.get("returnAccess").getAsString().equals("n")) {
            this.mViewArchive.setVisibility(0);
        } else {
            this.mViewArchive.setVisibility(8);
            if (this.TYPE.has(this.mJsonObjectProduct.get("status").getAsString())) {
                this.mTextViewTitleBar.setText(this.TYPE.get(this.mJsonObjectProduct.get("status").getAsString()).getAsString());
            } else {
                this.mTextViewTitleBar.setText("");
            }
        }
        String orderInfo = MMTool.slashToHtml(this.mJsonObjectProduct.get("orderInfo").getAsString());
        if (orderInfo.length() > 0) {
            orderInfo = orderInfo + "<br /><br />";
        }
        this.mTextViewSpecification.setText(Html.fromHtml(orderInfo));
    }

    public void increase(View view) {
    }

    public void reduce(View view) {
    }

    public void cancel(View view) {
        onBackPressed();
    }

    public void ok(View view) {
        MMApplication.sendGoogleAnalyticsEvent("expired", "archive", this.mJsonObjectProduct.get("prodID").getAsString());
        new Builder(this).content((int) C0308R.string.archive_hint).positiveText((int) C0308R.string.confirm).negativeText((int) C0308R.string.cancel).cancelable(false).onPositive(new C07181()).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BarcodeActivity.REQUEST_CODE_PAY_BARCODE) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
