package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzbf {
    private static String zzbEt;
    static Map<String, String> zzbEu = new HashMap();

    static void zzG(Context context, String str) {
        zzdd.zzc(context, "gtm_install_referrer", "referrer", str);
        zzI(context, str);
    }

    public static String zzH(Context context, String str) {
        if (zzbEt == null) {
            synchronized (zzbf.class) {
                if (zzbEt == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        zzbEt = sharedPreferences.getString("referrer", "");
                    } else {
                        zzbEt = "";
                    }
                }
            }
        }
        return zzag(zzbEt, str);
    }

    public static void zzI(Context context, String str) {
        String zzag = zzag(str, "conv");
        if (zzag != null && zzag.length() > 0) {
            zzbEu.put(zzag, str);
            zzdd.zzc(context, "gtm_click_referrers", zzag, str);
        }
    }

    public static String zzag(String str, String str2) {
        if (str2 == null) {
            return str.length() > 0 ? str : null;
        } else {
            String str3 = "http://hostname/?";
            String valueOf = String.valueOf(str);
            return Uri.parse(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3)).getQueryParameter(str2);
        }
    }

    public static void zzhn(String str) {
        synchronized (zzbf.class) {
            zzbEt = str;
        }
    }

    public static String zzj(Context context, String str, String str2) {
        String str3 = (String) zzbEu.get(str);
        if (str3 == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str3 = sharedPreferences != null ? sharedPreferences.getString(str, "") : "";
            zzbEu.put(str, str3);
        }
        return zzag(str3, str2);
    }
}
