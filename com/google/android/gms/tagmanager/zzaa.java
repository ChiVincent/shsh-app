package com.google.android.gms.tagmanager;

import android.content.Context;

public class zzaa implements zzat {
    private static final Object zzbCG = new Object();
    private static zzaa zzbDU;
    private zzau zzbDV;
    private zzcl zzbDi;

    private zzaa(Context context) {
        this(zzav.zzbI(context), new zzda());
    }

    zzaa(zzau com_google_android_gms_tagmanager_zzau, zzcl com_google_android_gms_tagmanager_zzcl) {
        this.zzbDV = com_google_android_gms_tagmanager_zzau;
        this.zzbDi = com_google_android_gms_tagmanager_zzcl;
    }

    public static zzat zzbB(Context context) {
        zzat com_google_android_gms_tagmanager_zzat;
        synchronized (zzbCG) {
            if (zzbDU == null) {
                zzbDU = new zzaa(context);
            }
            com_google_android_gms_tagmanager_zzat = zzbDU;
        }
        return com_google_android_gms_tagmanager_zzat;
    }

    public boolean zzhi(String str) {
        if (this.zzbDi.zzpv()) {
            this.zzbDV.zzhm(str);
            return true;
        }
        zzbo.zzbe("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
        return false;
    }
}
