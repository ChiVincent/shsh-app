package com.gameball.school;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.tools.MMTool;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

public class MMPaymentActivity extends Activity {
    static String PRODUCT;
    public static int REQUEST_CODE_PAY_PRODUCT = 395;
    @BindView(2131689614)
    ImageView mImageView;
    JsonObject mJsonObjectProduct;
    @BindView(2131689633)
    TextView mTextViewAmount;
    @BindView(2131689634)
    TextView mTextViewListPrice;
    @BindView(2131689619)
    TextView mTextViewOrderId;
    @BindView(2131689622)
    TextView mTextViewQuantity;
    @BindView(2131689621)
    TextView mTextViewSpecification;
    @BindView(2131689615)
    TextView mTextViewSubTitle;
    @BindView(2131689632)
    TextView mTextViewSubTitle2;
    @BindView(2131689635)
    TextView mTextViewSubTitle3;
    @BindView(2131689613)
    TextView mTextViewTitle;
    @BindView(2131689620)
    TextView mTextViewTotalMoney;

    public static void startActivity(Fragment fragment, JsonObject product) {
        Intent intent = new Intent(fragment.getContext(), MMPaymentActivity.class);
        intent.putExtra(PRODUCT, product.toString());
        fragment.startActivityForResult(intent, REQUEST_CODE_PAY_PRODUCT);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_payment);
        ButterKnife.bind((Activity) this);
        this.mJsonObjectProduct = new JsonParser().parse(getIntent().getStringExtra(PRODUCT)).getAsJsonObject();
        Logger.json(this.mJsonObjectProduct.toString());
        Picasso.with(this).load(this.mJsonObjectProduct.get("prodImage").getAsString()).into(this.mImageView);
        this.mTextViewTitle.setText(this.mJsonObjectProduct.get("prodTitle").getAsString());
        this.mTextViewSubTitle.setText(this.mJsonObjectProduct.get("subTitle").getAsString());
        this.mTextViewSubTitle2.setText(this.mJsonObjectProduct.get("subTitle2").getAsString());
        this.mTextViewSubTitle3.setText(this.mJsonObjectProduct.get("subTitle3").getAsString());
        this.mTextViewListPrice.setText(getString(C0308R.string.original_price) + this.mJsonObjectProduct.get("listPrice").getAsString());
        this.mTextViewQuantity.setText(this.mJsonObjectProduct.get(Param.QUANTITY).getAsString() + getString(C0308R.string.set));
        this.mTextViewAmount.setText("(" + this.mJsonObjectProduct.get("Amount").getAsString() + this.mJsonObjectProduct.get("unitName").getAsString() + ")");
        this.mTextViewTotalMoney.setText(this.mJsonObjectProduct.get("totalAmount").getAsString() + getString(C0308R.string.dolor));
        this.mTextViewOrderId.setText(this.mJsonObjectProduct.get("orderID").getAsString());
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
        Spanned spanned = Html.fromHtml(orderInfo + 詳細介紹1 + 詳細介紹內容1 + 詳細介紹2 + 詳細介紹內容2 + 詳細介紹3 + 詳細介紹內容3 + 詳細介紹4 + 詳細介紹內容4 + 詳細介紹5 + 詳細介紹內容5 + 退款試算公式);
        Logger.m28w(orderInfo + 詳細介紹1 + 詳細介紹內容1 + 詳細介紹2 + 詳細介紹內容2 + 詳細介紹3 + 詳細介紹內容3 + 詳細介紹4 + 詳細介紹內容4 + 詳細介紹5 + 詳細介紹內容5 + 退款試算公式, new Object[0]);
        Logger.m28w(spanned.toString(), new Object[0]);
        this.mTextViewSpecification.setText(spanned);
    }

    public void increase(View view) {
    }

    public void reduce(View view) {
    }

    public void cancel(View view) {
        onBackPressed();
    }

    public void ok(View view) {
        MMApplication.sendGoogleAnalyticsEvent("buy", "barcode", this.mJsonObjectProduct.get("prodID").getAsString());
        if (this.mJsonObjectProduct.has("payBarcode1")) {
            this.mJsonObjectProduct.addProperty("transCode1", this.mJsonObjectProduct.get("payBarcode1").getAsString());
            this.mJsonObjectProduct.addProperty("transCode2", this.mJsonObjectProduct.get("payBarcode2").getAsString());
            BarcodeActivity.startActivity(this, this.mJsonObjectProduct, "門市付款");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BarcodeActivity.REQUEST_CODE_PAY_BARCODE) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
