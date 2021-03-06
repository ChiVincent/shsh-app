package com.gameball.school;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.tools.MMTool;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import retrofit2.Call;
import retrofit2.Response;

public class MMReturnProduct extends Activity {
    static String PRODUCT;
    public static int REQUEST_CODE_RETURN_PRODUCT = 920;
    JsonObject mJsonObjectProduct;
    @BindView(2131689652)
    TextView mTextViewShop;
    @BindView(2131689621)
    TextView mTextViewSpecification;

    class C09481 extends MMCustomCallBack {
        C09481() {
        }

        public void onResponse() {
        }

        public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
            JsonObject retnObject = responseJsonObject.get("retnObject").getAsJsonObject();
            retnObject.addProperty("status", MMReturnProduct.this.mJsonObjectProduct.get("status").getAsString());
            MMReturnProduct2.startActivity(MMReturnProduct.this, retnObject);
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
        Intent intent = new Intent(context, MMReturnProduct.class);
        intent.putExtra(PRODUCT, product.toString());
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, MMExchangeActivity2.REQUEST_CODE_EXCHANGE2);
        } else {
            context.startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_return_product);
        ButterKnife.bind((Activity) this);
        this.mJsonObjectProduct = new JsonParser().parse(getIntent().getStringExtra(PRODUCT)).getAsJsonObject();
        Logger.json(this.mJsonObjectProduct.toString());
        this.mTextViewShop.setText("【" + this.mJsonObjectProduct.get("payStoreName").getAsString() + "】");
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

    public void cancel(View view) {
        onBackPressed();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void ok(View view) {
        if (this.mJsonObjectProduct.get("status").getAsInt() == 4) {
            MMApplication.sendGoogleAnalyticsEvent("exchange", "return_info", this.mJsonObjectProduct.get("prodID").getAsString());
        } else if (this.mJsonObjectProduct.get("prodID").getAsInt() == 7) {
            MMApplication.sendGoogleAnalyticsEvent("expired", "return_info", this.mJsonObjectProduct.get("prodID").getAsString());
        }
        MMAPI.getInstance(this).getCancelOrderMoney(this.mJsonObjectProduct.get("orderID").getAsString(), new C09481());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MMReturnProduct2.REQUEST_CODE_RETURN_PRODUCT2 && resultCode == -1) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
