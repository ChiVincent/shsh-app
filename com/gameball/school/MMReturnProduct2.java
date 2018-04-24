package com.gameball.school;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class MMReturnProduct2 extends Activity {
    static String PRODUCT;
    public static int REQUEST_CODE_RETURN_PRODUCT2 = 283;
    @BindView(2131689614)
    ImageView mImageView;
    JsonObject mJsonObjectProduct;
    @BindView(2131689654)
    TextView mTextViewMoney;
    @BindView(2131689619)
    TextView mTextViewOrderId;
    @BindView(2131689622)
    TextView mTextViewQuantity;
    @BindView(2131689621)
    TextView mTextViewSpecification;
    @BindView(2131689615)
    TextView mTextViewSubTitle;
    @BindView(2131689613)
    TextView mTextViewTitle;
    @BindView(2131689653)
    TextView mTextViewUnit;

    class C07222 implements SingleButtonCallback {
        C07222() {
        }

        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            MMReturnProduct2.this.setResult(-1);
            MMReturnProduct2.this.finish();
        }
    }

    class C09491 extends MMCustomCallBack {
        C09491() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            super.onBackPressed();
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

    class C09503 extends MMCustomCallBack {
        C09503() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            BarcodeActivity.startActivity(MMReturnProduct2.this, responseJsonObject.get("retnObject").getAsJsonObject(), "門市退貨");
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

    public static void startActivity(Context context, JsonObject product) {
        Intent intent = new Intent(context, MMReturnProduct2.class);
        intent.putExtra(PRODUCT, product.toString());
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, REQUEST_CODE_RETURN_PRODUCT2);
        } else {
            context.startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_return_product2);
        ButterKnife.bind((Activity) this);
        this.mJsonObjectProduct = new JsonParser().parse(getIntent().getStringExtra(PRODUCT)).getAsJsonObject();
        Logger.json(this.mJsonObjectProduct.toString());
        Picasso.with(this).load(this.mJsonObjectProduct.get("prodImage").getAsString()).into(this.mImageView);
        this.mTextViewTitle.setText(this.mJsonObjectProduct.get("prodTitle").getAsString());
        this.mTextViewSubTitle.setText(this.mJsonObjectProduct.get("subTitle").getAsString());
        this.mTextViewUnit.setText(this.mJsonObjectProduct.get("unitName").getAsString());
        this.mTextViewQuantity.setText(this.mJsonObjectProduct.get("remianingAmount").getAsString());
        this.mTextViewMoney.setText(this.mJsonObjectProduct.get("returnTotal").getAsString());
        this.mTextViewOrderId.setText(this.mJsonObjectProduct.get("orderID").getAsString());
        if (this.mJsonObjectProduct.has("introTitle1")) {
            String 詳細介紹1 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introTitle1").getAsString());
            String 詳細介紹內容1 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introText1").getAsString());
            if (詳細介紹1.length() > 0) {
                詳細介紹1 = 詳細介紹1 + "：<br />";
            }
            if (詳細介紹內容1.length() > 0) {
                詳細介紹內容1 = 詳細介紹內容1 + "<br /><br />";
            }
            String 詳細介紹2 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introTitle2").getAsString());
            String 詳細介紹內容2 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introText2").getAsString());
            if (詳細介紹2.length() > 0) {
                詳細介紹2 = 詳細介紹2 + "：<br />";
            }
            if (詳細介紹內容2.length() > 0) {
                詳細介紹內容2 = 詳細介紹內容2 + "<br /><br />";
            }
            String 詳細介紹3 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introTitle3").getAsString());
            String 詳細介紹內容3 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introText3").getAsString());
            if (詳細介紹3.length() > 0) {
                詳細介紹3 = 詳細介紹3 + "：<br />";
            }
            if (詳細介紹內容3.length() > 0) {
                詳細介紹內容3 = 詳細介紹內容3 + "<br /><br />";
            }
            String 詳細介紹4 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introTitle4").getAsString());
            String 詳細介紹內容4 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introText4").getAsString());
            if (詳細介紹4.length() > 0) {
                詳細介紹4 = 詳細介紹4 + "：<br />";
            }
            if (詳細介紹內容4.length() > 0) {
                詳細介紹內容4 = 詳細介紹內容4 + "<br /><br />";
            }
            String 詳細介紹5 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introTitle5").getAsString());
            String 詳細介紹內容5 = MMTool.slashToHtml(this.mJsonObjectProduct.get("introText5").getAsString());
            if (詳細介紹5.length() > 0) {
                詳細介紹5 = 詳細介紹5 + "：<br />";
            }
            if (詳細介紹內容5.length() > 0) {
                詳細介紹內容5 = 詳細介紹內容5 + "<br /><br />";
            }
            String 退款試算公式 = MMTool.slashToHtml(this.mJsonObjectProduct.get("refundInfo").getAsString());
            if (退款試算公式.length() > 0) {
                退款試算公式 = 退款試算公式 + "<br /><br />";
            }
            String orderInfo = MMTool.slashToHtml(this.mJsonObjectProduct.get("orderInfo").getAsString());
            if (orderInfo.length() > 0) {
                orderInfo = orderInfo + "<br /><br />";
            }
            this.mTextViewSpecification.setText(Html.fromHtml(orderInfo + 詳細介紹1 + 詳細介紹內容1 + 詳細介紹2 + 詳細介紹內容2 + 詳細介紹3 + 詳細介紹內容3 + 詳細介紹4 + 詳細介紹內容4 + 詳細介紹5 + 詳細介紹內容5 + 退款試算公式));
            return;
        }
        this.mTextViewSpecification.setText("");
    }

    public void cancel(View view) {
        onBackPressed();
    }

    public void onBackPressed() {
        if (this.mJsonObjectProduct.get("status").getAsInt() == 6) {
            MMAPI.getInstance(this).setCancelReturnProduct(this.mJsonObjectProduct.get("orderID").getAsString(), new C09491());
        } else {
            super.onBackPressed();
        }
    }

    public void ok(View view) {
        if (this.mJsonObjectProduct.get("status").getAsInt() == 4) {
            MMApplication.sendGoogleAnalyticsEvent("exchange", "return_barcode", this.mJsonObjectProduct.get("prodID").getAsString());
        } else if (this.mJsonObjectProduct.get("prodID").getAsInt() == 7) {
            MMApplication.sendGoogleAnalyticsEvent("expired", "return_barcode", this.mJsonObjectProduct.get("prodID").getAsString());
        }
        if (this.mJsonObjectProduct.get("returnTotal").getAsInt() == 0) {
            new Builder(this).content(getString(C0308R.string.return_hint3)).positiveText((int) C0308R.string.confirm).cancelable(false).onPositive(new C07222()).show();
        } else if (this.mJsonObjectProduct.has("transCode1")) {
            BarcodeActivity.startActivity(this, this.mJsonObjectProduct, "門市退貨");
        } else {
            MMAPI.getInstance(this).getReturnProductBarcode(this.mJsonObjectProduct.get("orderID").getAsString(), new C09503());
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BarcodeActivity.REQUEST_CODE_PAY_BARCODE) {
            setResult(-1);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
