package com.gameball.school;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.tools.MMNumberView;
import com.gameball.tools.MMTool;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Response;

public class MMExchangeActivity extends Activity {
    static String PRODUCT;
    @BindView(2131689614)
    ImageView mImageView;
    JsonObject mJsonObjectProduct;
    @BindView(2131689619)
    TextView mTextViewOrderId;
    @BindView(2131689616)
    TextView mTextViewRemain;
    @BindView(2131689621)
    TextView mTextViewSpecification;
    @BindView(2131689615)
    TextView mTextViewSubTitle;
    @BindView(2131689613)
    TextView mTextViewTitle;
    @BindView(2131689620)
    TextView mTextViewTotalMoney;
    @BindView(2131689618)
    TextView mTextViewUnitName;
    @BindView(2131689617)
    MMNumberView mmNumberView;

    class C09251 extends MMCustomCallBack {
        C09251() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            BarcodeActivity.startActivity(MMExchangeActivity.this, responseJsonObject.get("retnObject").getAsJsonObject(), "門市兌換");
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
        Intent intent = new Intent(context, MMExchangeActivity.class);
        intent.putExtra(PRODUCT, product.toString());
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_exchange);
        ButterKnife.bind((Activity) this);
        this.mJsonObjectProduct = new JsonParser().parse(getIntent().getStringExtra(PRODUCT)).getAsJsonObject();
        Logger.json(this.mJsonObjectProduct.toString());
        Picasso.with(this).load(this.mJsonObjectProduct.get("prodImage").getAsString()).into(this.mImageView);
        this.mTextViewTitle.setText(this.mJsonObjectProduct.get("prodTitle").getAsString());
        this.mTextViewSubTitle.setText(this.mJsonObjectProduct.get("subTitle").getAsString());
        this.mTextViewRemain.setText(getString(C0308R.string.exchanged_short) + this.mJsonObjectProduct.get("exchangeAmount").getAsString() + this.mJsonObjectProduct.get("unitName").getAsString() + " / " + getString(C0308R.string.un_exchanged_short) + this.mJsonObjectProduct.get("remianingAmount") + this.mJsonObjectProduct.get("unitName").getAsString());
        this.mTextViewOrderId.setText(this.mJsonObjectProduct.get("orderID").getAsString());
        this.mTextViewUnitName.setText(this.mJsonObjectProduct.get("unitName").getAsString());
        this.mmNumberView.setMaxNumber(this.mJsonObjectProduct.get("remianingAmount").getAsInt());
        String 規格 = getResources().getString(C0308R.string.specification);
        String 規格內容 = this.mJsonObjectProduct.get("specification").getAsString();
        String 原價 = getResources().getString(C0308R.string.original_price);
        String 原價內容 = this.mJsonObjectProduct.get("listPrice").getAsString();
        String 特價 = getResources().getString(C0308R.string.special_price);
        String 特價內容 = this.mJsonObjectProduct.get("specialPrice").getAsString();
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
    }

    public void increase(View view) {
        this.mmNumberView.increase();
        this.mTextViewTotalMoney.setText(getString(C0308R.string.total_money) + (this.mJsonObjectProduct.get("specialPrice").getAsInt() * this.mmNumberView.getNumber()) + "");
    }

    public void reduce(View view) {
        this.mmNumberView.reduce();
        this.mTextViewTotalMoney.setText(getString(C0308R.string.total_money) + (this.mJsonObjectProduct.get("specialPrice").getAsInt() * this.mmNumberView.getNumber()) + "");
    }

    public void cancel(View view) {
        onBackPressed();
    }

    public void ok(View view) {
        MMAPI.getInstance(this).getExchangeOrderBarcode(this.mJsonObjectProduct.get("orderID").getAsString(), String.valueOf(this.mmNumberView.getNumber()), new C09251());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BarcodeActivity.REQUEST_CODE_PAY_BARCODE) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
