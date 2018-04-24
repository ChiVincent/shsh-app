package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

class zzdf implements zzad {
    private final Context mContext;
    private final String zzHY;
    private final zzb zzbGt;
    private final zza zzbGu;

    public interface zza {
        void zza(zzas com_google_android_gms_tagmanager_zzas);

        void zzb(zzas com_google_android_gms_tagmanager_zzas);

        void zzc(zzas com_google_android_gms_tagmanager_zzas);
    }

    interface zzb {
        HttpURLConnection zzd(URL url) throws IOException;
    }

    class C07891 implements zzb {
        C07891() {
        }

        public HttpURLConnection zzd(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }

    zzdf(Context context, zza com_google_android_gms_tagmanager_zzdf_zza) {
        this(new C07891(), context, com_google_android_gms_tagmanager_zzdf_zza);
    }

    zzdf(zzb com_google_android_gms_tagmanager_zzdf_zzb, Context context, zza com_google_android_gms_tagmanager_zzdf_zza) {
        this.zzbGt = com_google_android_gms_tagmanager_zzdf_zzb;
        this.mContext = context.getApplicationContext();
        this.zzbGu = com_google_android_gms_tagmanager_zzdf_zza;
        this.zzHY = zza("GoogleTagManager", "4.00", VERSION.RELEASE, zzc(Locale.getDefault()), Build.MODEL, Build.ID);
    }

    static String zzc(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage().toLowerCase());
        if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
            stringBuilder.append("-").append(locale.getCountry().toLowerCase());
        }
        return stringBuilder.toString();
    }

    public void zzM(List<zzas> list) {
        Object obj;
        Throwable th;
        InputStream inputStream;
        Object obj2;
        Throwable th2;
        Object obj3;
        IOException iOException;
        int min = Math.min(list.size(), 40);
        Object obj4 = 1;
        int i = 0;
        while (i < min) {
            zzas com_google_android_gms_tagmanager_zzas = (zzas) list.get(i);
            URL zzd = zzd(com_google_android_gms_tagmanager_zzas);
            if (zzd == null) {
                zzbo.zzbe("No destination: discarding hit.");
                this.zzbGu.zzb(com_google_android_gms_tagmanager_zzas);
                obj = obj4;
            } else {
                try {
                    HttpURLConnection zzd2 = this.zzbGt.zzd(zzd);
                    if (obj4 != null) {
                        try {
                            zzbt.zzbK(this.mContext);
                            obj4 = null;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream = null;
                            obj2 = obj4;
                            th2 = th;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    obj3 = obj2;
                                    iOException = e;
                                }
                            }
                            zzd2.disconnect();
                            throw th2;
                        }
                    }
                    zzd2.setRequestProperty("User-Agent", this.zzHY);
                    int responseCode = zzd2.getResponseCode();
                    InputStream inputStream2 = zzd2.getInputStream();
                    if (responseCode != Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                        try {
                            zzbo.zzbe("Bad response: " + responseCode);
                            this.zzbGu.zzc(com_google_android_gms_tagmanager_zzas);
                        } catch (Throwable th32) {
                            th = th32;
                            inputStream = inputStream2;
                            obj2 = obj4;
                            th2 = th;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            zzd2.disconnect();
                            throw th2;
                        }
                    }
                    this.zzbGu.zza(com_google_android_gms_tagmanager_zzas);
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    zzd2.disconnect();
                    obj = obj4;
                } catch (IOException e2) {
                    iOException = e2;
                    obj3 = obj4;
                    String str = "Exception sending hit: ";
                    String valueOf = String.valueOf(iOException.getClass().getSimpleName());
                    zzbo.zzbe(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    zzbo.zzbe(iOException.getMessage());
                    this.zzbGu.zzc(com_google_android_gms_tagmanager_zzas);
                    obj = obj3;
                    i++;
                    obj4 = obj;
                }
            }
            i++;
            obj4 = obj;
        }
    }

    public boolean zzPa() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbo.m11v("...no network connectivity");
        return false;
    }

    String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    URL zzd(zzas com_google_android_gms_tagmanager_zzas) {
        try {
            return new URL(com_google_android_gms_tagmanager_zzas.zzPk());
        } catch (MalformedURLException e) {
            zzbo.m10e("Error trying to parse the GTM url.");
            return null;
        }
    }
}
