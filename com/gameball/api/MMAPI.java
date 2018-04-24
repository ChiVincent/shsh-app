package com.gameball.api;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.gameball.school.C0308R;
import com.gameball.school.MMApplication;
import com.gameball.tools.MD5Util;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.Calendar;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class MMAPI {
    public static final String BASE_URL = "https://jhs.gameball.com.tw/api/";
    public static final String SECRET_CODE = "jhs1704_1dmfym104l";
    private static int mIntCallCounter = 0;
    private static Retrofit mRetrofit;
    private static Dialog materialDialog;
    private static MMAPI mmApi = null;
    private static ApiEndpointInterface mmApiEndpointInterface;

    public interface ApiEndpointInterface {
        @GET("api_103.php")
        Call<JsonObject> buyProduct(@Query("token") String str, @Query("prodID") String str2, @Query("quantity") String str3, @Query("timestamp") String str4, @Query("keyStr") String str5);

        @GET("api_113.php")
        Call<JsonObject> cancelExchangeOrderBarcode(@Query("token") String str, @Query("orderID") String str2, @Query("timestamp") String str3, @Query("keyStr") String str4);

        @GET("api_002.php")
        Call<JsonObject> getAccountToken(@Query("token") String str, @Query("verifyCode") String str2, @Query("senderID") String str3, @Query("senderOS") String str4, @Query("timestamp") String str5, @Query("keyStr") String str6);

        @GET("api_108.php")
        Call<JsonObject> getCancelOrderMoney(@Query("token") String str, @Query("orderID") String str2, @Query("timestamp") String str3, @Query("keyStr") String str4);

        @GET("api_106.php")
        Call<JsonObject> getExchangeOrderBarcode(@Query("token") String str, @Query("orderID") String str2, @Query("quantity") String str3, @Query("timestamp") String str4, @Query("keyStr") String str5);

        @FormUrlEncoded
        @POST("api_007")
        Call<JsonObject> getGraduationYear(@Field("type") String str, @Field("timestamp") String str2, @Field("keyStr") String str3);

        @FormUrlEncoded
        @POST("api_002")
        Call<JsonObject> getLogin(@Field("device") String str, @Field("phone") String str2, @Field("password") String str3, @Field("noty_token") String str4, @Field("timestamp") String str5, @Field("keyStr") String str6);

        @GET("api_202.php")
        Call<JsonObject> getNotification(@Query("token") String str, @Query("timestamp") String str2, @Query("keyStr") String str3);

        @GET("api_102s.php")
        Call<JsonObject> getOfferProduct(@Query("token") String str, @Query("prodID") String str2, @Query("timestamp") String str3, @Query("keyStr") String str4);

        @GET("api_102.php")
        Call<JsonObject> getOfferProductList(@Query("token") String str, @Query("timestamp") String str2, @Query("keyStr") String str3);

        @GET("api_112.php")
        Call<JsonObject> getOrderDetailList(@Query("token") String str, @Query("pageSize") String str2, @Query("pageNo") String str3, @Query("timestamp") String str4, @Query("keyStr") String str5);

        @FormUrlEncoded
        @POST("api_006")
        Call<JsonObject> getOrderList(@Field("type") String str, @Field("timestamp") String str2, @Field("keyStr") String str3);

        @FormUrlEncoded
        @POST("api_005")
        Call<JsonObject> getPayOrderBarcode(@Field("type") String str, @Field("timestamp") String str2, @Field("keyStr") String str3);

        @GET("api_109.php")
        Call<JsonObject> getReturnProductBarcode(@Query("token") String str, @Query("orderID") String str2, @Query("timestamp") String str3, @Query("keyStr") String str4);

        @FormUrlEncoded
        @POST("api_001")
        Call<JsonObject> getSMSToken(@Field("device") String str, @Field("noty_token") String str2, @Field("phone") String str3, @Field("password1") String str4, @Field("password2") String str5, @Field("birthday") String str6, @Field("username") String str7, @Field("gradu_year1") String str8, @Field("gradu_class1") String str9, @Field("gradu_year2") String str10, @Field("gradu_class2") String str11, @Field("idno") String str12, @Field("timestamp") String str13, @Field("keyStr") String str14);

        @GET("api_201.php")
        Call<JsonObject> getSettingData(@Query("token") String str, @Query("timestamp") String str2, @Query("keyStr") String str3);

        @FormUrlEncoded
        @POST("api_004")
        Call<JsonObject> getUpdateToken(@Field("token") String str, @Field("timestamp") String str2, @Field("keyStr") String str3);

        @GET("api_107.php")
        Call<JsonObject> setCancelOrder(@Query("token") String str, @Query("orderID") String str2, @Query("timestamp") String str3, @Query("keyStr") String str4);

        @GET("api_110.php")
        Call<JsonObject> setCancelReturnProduct(@Query("token") String str, @Query("orderID") String str2, @Query("timestamp") String str3, @Query("keyStr") String str4);

        @FormUrlEncoded
        @POST("api_003")
        Call<JsonObject> setEmail(@Field("token") String str, @Field("email") String str2, @Field("timestamp") String str3, @Field("keyStr") String str4);

        @GET("api_203.php")
        Call<JsonObject> setNotificationClean(@Query("token") String str, @Query("timestamp") String str2, @Query("keyStr") String str3);

        @GET("api_205.php")
        Call<JsonObject> setNotificationRead(@Query("token") String str, @Query("notifyID") String str2, @Query("timestamp") String str3, @Query("keyStr") String str4);

        @GET("api_204.php")
        Call<JsonObject> setNotificationReadAll(@Query("token") String str, @Query("timestamp") String str2, @Query("keyStr") String str3);

        @GET("api_111.php")
        Call<JsonObject> setOrderToHistory(@Query("token") String str, @Query("timestamp") String str2, @Query("keyStr") String str3);

        @GET("api_114.php")
        Call<JsonObject> setReturnedToHistory(@Query("token") String str, @Query("timestamp") String str2, @Query("keyStr") String str3);

        @GET("api_115.php")
        Call<JsonObject> setToArchive(@Query("token") String str, @Query("orderID") String str2, @Query("timestamp") String str3, @Query("keyStr") String str4);
    }

    public static abstract class MMCustomCallBack implements Callback<JsonObject> {
        JsonObject gsonObjectErrorCode = new JsonObject();

        public abstract boolean onFailureQQ(Call<JsonObject> call, Throwable th);

        public abstract void onFinish();

        public abstract void onResponse();

        public abstract void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject jsonObject);

        public abstract boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject jsonObject);

        public MMCustomCallBack() {
            this.gsonObjectErrorCode.addProperty("101", "輸入資料不齊全");
            this.gsonObjectErrorCode.addProperty("102", "驗證錯誤（驗證碼或timestamp錯誤）");
            this.gsonObjectErrorCode.addProperty("103", "會員存取 Token 過期");
            this.gsonObjectErrorCode.addProperty("104", "會員存取 Token 錯誤（不存在）");
            this.gsonObjectErrorCode.addProperty("105", "本站會員 帳號/密碼 錯誤");
            this.gsonObjectErrorCode.addProperty("106", "第三方登入類別代碼錯誤");
            this.gsonObjectErrorCode.addProperty("000", "無錯誤（正確執行完畢）");
        }

        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            onResponse();
            Logger.json(((JsonObject) response.body()).toString());
            if (response.code() == ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                JsonObject responseJsonObject = (JsonObject) response.body();
                String responseCode = responseJsonObject.get("code").getAsString();
                if (responseCode.equals("0")) {
                    onSuccess(call, response, responseJsonObject);
                } else if (responseCode.equals("109")) {
                    onSuccess(call, response, responseJsonObject);
                } else {
                    if (!onSuccessFailure(call, response, responseJsonObject)) {
                        MMToast.showTextToast(responseJsonObject.get(SettingsJsonConstants.PROMPT_MESSAGE_KEY).getAsString());
                    }
                    if (responseCode.equals("103") || responseCode.equals("104")) {
                        MMFavor.getInstance().clean();
                        Intent i = MMApplication.getAppContext().getPackageManager().getLaunchIntentForPackage(MMApplication.getAppContext().getPackageName());
                        i.addFlags(67108864);
                        MMApplication.getAppContext().startActivity(i);
                        System.exit(0);
                        return;
                    }
                }
            } else if (!onFailureQQ(call, new Error(response.raw().toString()))) {
                MMToast.showTextToast("Error");
            }
            MMAPI.cancelProgressDialog();
            onFinish();
        }

        public void onFailure(Call<JsonObject> call, Throwable t) {
            onResponse();
            if (!onFailureQQ(call, t)) {
                MMToast.showTextToast(MMApplication.getAppContext().getString(C0308R.string.connection_fail));
            }
            MMAPI.cancelProgressDialog();
            onFinish();
        }
    }

    public static MMAPI getInstance() {
        if (mmApi == null) {
            mmApi = new MMAPI();
        }
        return mmApi;
    }

    public static MMAPI getInstance(Context activity) {
        if (mmApi == null) {
            mmApi = new MMAPI();
        }
        mIntCallCounter++;
        showProgressDialog(activity);
        return mmApi;
    }

    private MMAPI() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(Level.BODY);
        Builder okHttpClient = new Builder();
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create())).client(okHttpClient.build()).build();
        mmApiEndpointInterface = (ApiEndpointInterface) mRetrofit.create(ApiEndpointInterface.class);
    }

    private String getTimestamp() {
        return ((int) (Calendar.getInstance().getTimeInMillis() / 1000)) + "";
    }

    public void getSMSToken(String phone, String password1, String password2, String birthday, String username, String gradu_year1, String gradu_class1, String gradu_year2, String gradu_class2, String idno, Callback<JsonObject> jsonObjectCallback) {
        String str = phone;
        String str2 = password1;
        String str3 = password2;
        String str4 = birthday;
        String str5 = username;
        String str6 = gradu_year1;
        String str7 = gradu_class1;
        String str8 = gradu_year2;
        String str9 = gradu_class2;
        String str10 = idno;
        mmApiEndpointInterface.getSMSToken(AbstractSpiCall.ANDROID_CLIENT_TYPE, FirebaseInstanceId.getInstance().getToken(), str, str2, str3, str4, str5, str6, str7, str8, str9, str10, getTimestamp(), MD5Util.MD5(SECRET_CODE + phone + password1 + getTimestamp())).enqueue(jsonObjectCallback);
    }

    public void getAccountToken(String verifyCode, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getRegToken();
        String timestamp = getTimestamp();
        String str = verifyCode;
        mmApiEndpointInterface.getAccountToken(token, str, FirebaseInstanceId.getInstance().getToken(), AbstractSpiCall.ANDROID_CLIENT_TYPE, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setEmail(String email, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setEmail(token, email, timestamp, MD5Util.MD5(SECRET_CODE + token + email + timestamp)).enqueue(jsonObjectCallback);
    }

    public Call<JsonObject> getUpdateToken(Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        Call<JsonObject> call = mmApiEndpointInterface.getUpdateToken(token, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp));
        call.enqueue(jsonObjectCallback);
        return call;
    }

    public void getLogin(String phone, String password, Callback<JsonObject> jsonObjectCallback) {
        String device = AbstractSpiCall.ANDROID_CLIENT_TYPE;
        String timestamp = getTimestamp();
        String keyStr = MD5Util.MD5(SECRET_CODE + phone + password + timestamp);
        mmApiEndpointInterface.getLogin(device, phone, password, FirebaseInstanceId.getInstance().getToken(), timestamp, keyStr).enqueue(jsonObjectCallback);
    }

    public void getOfferProductList(Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getOfferProductList(token, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getOfferProduct(String prodID, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getOfferProduct(token, prodID, timestamp, MD5Util.MD5(SECRET_CODE + token + prodID + timestamp)).enqueue(jsonObjectCallback);
    }

    public void buyProduct(String prodID, String quantity, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.buyProduct(token, prodID, quantity, timestamp, MD5Util.MD5(SECRET_CODE + token + prodID + quantity + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getOrderList(Callback<JsonObject> jsonObjectCallback) {
        String type = "senior";
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getOrderList(type, timestamp, MD5Util.MD5(SECRET_CODE + type + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getPayOrderBarcode(Callback<JsonObject> jsonObjectCallback) {
        String type = "senior";
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getPayOrderBarcode(type, timestamp, MD5Util.MD5(SECRET_CODE + type + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getGraduationYear(String type, Callback<JsonObject> jsonObjectCallback) {
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getGraduationYear(type, timestamp, MD5Util.MD5(SECRET_CODE + type + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getExchangeOrderBarcode(String orderID, String quantity, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getExchangeOrderBarcode(token, orderID, quantity, timestamp, MD5Util.MD5(SECRET_CODE + token + orderID + quantity + timestamp)).enqueue(jsonObjectCallback);
    }

    public void cancelExchangeOrderBarcode(String orderID, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.cancelExchangeOrderBarcode(token, orderID, timestamp, MD5Util.MD5(SECRET_CODE + token + orderID + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getSettingData(Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getSettingData(token, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getNotification(Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getNotification(token, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setNotificationClean(Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setNotificationClean(token, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setNotificationReadAll(Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setNotificationReadAll(token, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setNotificationRead(String notifyID, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setNotificationRead(token, notifyID, timestamp, MD5Util.MD5(SECRET_CODE + token + notifyID + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getOrderDetailList(String pageSize, String pageNo, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getOrderDetailList(token, pageSize, pageNo, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setCancelOrder(String orderID, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setCancelOrder(token, orderID, timestamp, MD5Util.MD5(SECRET_CODE + token + orderID + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getCancelOrderMoney(String orderID, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getCancelOrderMoney(token, orderID, timestamp, MD5Util.MD5(SECRET_CODE + token + orderID + timestamp)).enqueue(jsonObjectCallback);
    }

    public void getReturnProductBarcode(String orderID, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.getReturnProductBarcode(token, orderID, timestamp, MD5Util.MD5(SECRET_CODE + token + orderID + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setCancelReturnProduct(String orderID, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setCancelReturnProduct(token, orderID, timestamp, MD5Util.MD5(SECRET_CODE + token + orderID + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setOrderToHistory(Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setOrderToHistory(token, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setReturnedToHistory(Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setReturnedToHistory(token, timestamp, MD5Util.MD5(SECRET_CODE + token + timestamp)).enqueue(jsonObjectCallback);
    }

    public void setToArchive(String orderID, Callback<JsonObject> jsonObjectCallback) {
        String token = MMFavor.getInstance().account.getToken();
        String timestamp = getTimestamp();
        mmApiEndpointInterface.setToArchive(token, orderID, timestamp, MD5Util.MD5(SECRET_CODE + token + orderID + timestamp)).enqueue(jsonObjectCallback);
    }

    private static void showProgressDialog(Context activity) {
        if (materialDialog == null || activity != materialDialog.getContext()) {
            materialDialog = new Dialog(activity, C0308R.style.MyCustomDialog);
            materialDialog.setCancelable(false);
            materialDialog.setContentView(C0308R.layout.activity_mmprogress);
        }
        materialDialog.show();
    }

    private static void cancelProgressDialog() {
        if (materialDialog != null) {
            materialDialog.cancel();
        }
    }
}
