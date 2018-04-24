package com.gameball.school;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import com.gameball.api.MMAPI;
import com.gameball.api.MMAPI.MMCustomCallBack;
import com.gameball.api.MMFavor;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import retrofit2.Call;
import retrofit2.Response;

public class MMLinkActivity extends Activity {
    public static final String INBOX_DEEP_LINK = "/inbox/messages";
    public static final String PREFERENCE_DEEP_LINK = "/home/preferences";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.m21e("Open Link", new Object[0]);
        Intent intent = getIntent();
        if (intent == null || intent.getData() == null) {
            finish();
        }
        openDeepLink(intent.getData());
        finish();
    }

    private void openDeepLink(Uri deepLink) {
        String host = deepLink.getHost();
        final String path = deepLink.getPath();
        String token = MMFavor.getInstance().account.getToken();
        Logger.m21e("host:" + host, new Object[0]);
        Logger.m21e("path:" + path, new Object[0]);
        Logger.m21e("token:" + token, new Object[0]);
        final TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        Intent route = null;
        stackBuilder.addNextIntent(new Intent(getApplicationContext(), MMOpeningActivity.class));
        Logger.m21e("host:" + host, new Object[0]);
        Object obj = -1;
        switch (host.hashCode()) {
            case -1039689911:
                if (host.equals("notify")) {
                    obj = 7;
                    break;
                }
                break;
            case -902467304:
                if (host.equals("signup")) {
                    obj = 2;
                    break;
                }
                break;
            case 3208415:
                if (host.equals("home")) {
                    obj = null;
                    break;
                }
                break;
            case 92896879:
                if (host.equals("album")) {
                    obj = 3;
                    break;
                }
                break;
            case 96891546:
                if (host.equals("event")) {
                    obj = 4;
                    break;
                }
                break;
            case 600121888:
                if (host.equals("productDetail")) {
                    obj = 1;
                    break;
                }
                break;
            case 1984153269:
                if (host.equals("service")) {
                    obj = 5;
                    break;
                }
                break;
            case 1985941072:
                if (host.equals("setting")) {
                    obj = 6;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                Logger.m21e("我在home", new Object[0]);
                route = new Intent(getApplicationContext(), MMMainActivity.class);
                stackBuilder.addNextIntent(route);
                stackBuilder.startActivities();
                break;
            case 1:
                MMAPI.getInstance().getOfferProduct(deepLink.getLastPathSegment(), new MMCustomCallBack() {
                    public void onResponse() {
                    }

                    public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                        Intent intent = MMProductDetail.creatIntent(MMLinkActivity.this.getApplicationContext(), responseJsonObject.get("retnObject").getAsJsonObject());
                        stackBuilder.addNextIntent(new Intent(MMLinkActivity.this.getApplicationContext(), MMMainActivity.class));
                        stackBuilder.addNextIntent(intent);
                        stackBuilder.startActivities();
                    }

                    public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                        return false;
                    }

                    public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
                        return false;
                    }

                    public void onFinish() {
                    }
                });
                break;
            case 2:
                Logger.m21e("我有進來唷signup", new Object[0]);
                MMFavor.getInstance().clean();
                route = new Intent(getApplicationContext(), MMMainActivity.class);
                route.putExtra("CHECK", C0308R.id.radiobutton_order);
                stackBuilder.addNextIntent(route);
                stackBuilder.startActivities();
                break;
            case 3:
                Logger.m21e("我在album", new Object[0]);
                String albumId = deepLink.getLastPathSegment();
                Logger.m21e("albumId" + albumId, new Object[0]);
                MMFavor.getInstance().account.setAlbumUrl(getString(C0308R.string.url) + "album/photo/" + albumId);
                route = new Intent(getApplicationContext(), MMMainActivity.class);
                route.putExtra("CHECK", C0308R.id.radiobutton_events);
                stackBuilder.addNextIntent(route);
                stackBuilder.startActivities();
                break;
            case 4:
                String eventId = deepLink.getLastPathSegment();
                if (eventId == null) {
                    route = new Intent(getApplicationContext(), MMMainActivity.class);
                    route.putExtra("CHECK", C0308R.id.radiobutton_events);
                    stackBuilder.addNextIntent(route);
                    stackBuilder.startActivities();
                    break;
                }
                String url = "http://apibeta.hilife.taipei/api_event.php?eventID=" + eventId;
                Intent i = new Intent("android.intent.action.VIEW");
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case 5:
                route = new Intent(getApplicationContext(), MMMainActivity.class);
                route.putExtra("CHECK", C0308R.id.radiobutton_feedback);
                stackBuilder.addNextIntent(route);
                stackBuilder.startActivities();
                break;
            case 6:
                MMAPI.getInstance().getSettingData(new MMCustomCallBack() {
                    public void onResponse() {
                    }

                    public void onSuccess(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                        JsonObject retnObject = responseJsonObject.get("retnObject").getAsJsonObject();
                        MMFavor.getInstance().setting.setVersionInfo(retnObject.get("versionInfo").getAsString());
                        MMFavor.getInstance().setting.setServiceNote(retnObject.get("ServiceNote").getAsString());
                        MMFavor.getInstance().setting.setNoServiceStore(retnObject.get("noServiceStore").getAsJsonArray().toString());
                        if (!retnObject.has("userEmail") || retnObject.get("userEmail").getAsString().length() <= 0 || retnObject.get("userEmail").getAsString().equals("false")) {
                            MMFavor.getInstance().setting.setEmail("");
                        } else {
                            MMFavor.getInstance().setting.setEmail(retnObject.get("userEmail").getAsString());
                        }
                        Intent home = new Intent(MMLinkActivity.this.getApplicationContext(), MMMainActivity.class);
                        home.putExtra("CHECK", C0308R.id.radiobutton_setting);
                        stackBuilder.addNextIntent(home);
                        String str = path;
                        boolean z = true;
                        switch (str.hashCode()) {
                            case -1127506001:
                                if (str.equals("/emailBundle")) {
                                    z = false;
                                    break;
                                }
                                break;
                            case -6791047:
                                if (str.equals("/privacy")) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 368932220:
                                if (str.equals("/noServiceStore")) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 659315145:
                                if (str.equals("/version")) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 1234641285:
                                if (str.equals("/history")) {
                                    z = true;
                                    break;
                                }
                                break;
                        }
                        switch (z) {
                            case false:
                                Intent intent;
                                if (MMFavor.getInstance().setting.getEmail() == null || MMFavor.getInstance().setting.getEmail().length() <= 0) {
                                    intent = new Intent(MMLinkActivity.this.getApplicationContext(), MMEmailActivity.class);
                                } else {
                                    intent = new Intent(MMLinkActivity.this.getApplicationContext(), MMEmailAlreadyAccomplishedActivity.class);
                                }
                                intent.putExtra("HIDE_FOOTER", true);
                                stackBuilder.addNextIntent(intent);
                                break;
                            case true:
                                stackBuilder.addNextIntent(new Intent(MMLinkActivity.this.getApplicationContext(), MMHistoryActivity.class));
                                break;
                            case true:
                                stackBuilder.addNextIntent(new Intent(MMLinkActivity.this.getApplicationContext(), MMServiceNoteActivity.class));
                                break;
                            case true:
                                stackBuilder.addNextIntent(new Intent(MMLinkActivity.this.getApplicationContext(), MMNoServiceActivity.class));
                                break;
                            case true:
                                stackBuilder.addNextIntent(new Intent(MMLinkActivity.this.getApplicationContext(), MMVersionInfoActivity.class));
                                break;
                        }
                        stackBuilder.startActivities();
                    }

                    public boolean onSuccessFailure(Call<JsonObject> call, Response<JsonObject> response, JsonObject responseJsonObject) {
                        return false;
                    }

                    public boolean onFailureQQ(Call<JsonObject> call, Throwable t) {
                        return false;
                    }

                    public void onFinish() {
                    }
                });
                break;
            case 7:
                Intent home = new Intent(getApplicationContext(), MMMainActivity.class);
                home.putExtra("CHECK", C0308R.id.radiobutton_setting);
                stackBuilder.addNextIntent(home);
                stackBuilder.addNextIntent(new Intent(getApplicationContext(), MMNotificationActivity.class));
                stackBuilder.startActivities();
                break;
            default:
                Logger.m21e("我在default", new Object[0]);
                break;
        }
        if (route != null) {
        }
        if (stackBuilder.getIntentCount() != 0) {
        }
    }
}
