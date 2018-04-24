package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;
import java.util.Set;

public abstract class zzci extends zzam {
    private static final String zzbEd = zzah.ARG0.toString();
    private static final String zzbFa = zzah.ARG1.toString();

    public zzci(String str) {
        super(str, zzbEd, zzbFa);
    }

    public boolean zzOw() {
        return true;
    }

    public /* bridge */ /* synthetic */ String zzPg() {
        return super.zzPg();
    }

    public /* bridge */ /* synthetic */ Set zzPh() {
        return super.zzPh();
    }

    public zza zzY(Map<String, zza> map) {
        for (zza com_google_android_gms_internal_zzaj_zza : map.values()) {
            if (com_google_android_gms_internal_zzaj_zza == zzdm.zzQm()) {
                return zzdm.zzR(Boolean.valueOf(false));
            }
        }
        zza com_google_android_gms_internal_zzaj_zza2 = (zza) map.get(zzbEd);
        zza com_google_android_gms_internal_zzaj_zza3 = (zza) map.get(zzbFa);
        boolean zza = (com_google_android_gms_internal_zzaj_zza2 == null || com_google_android_gms_internal_zzaj_zza3 == null) ? false : zza(com_google_android_gms_internal_zzaj_zza2, com_google_android_gms_internal_zzaj_zza3, map);
        return zzdm.zzR(Boolean.valueOf(zza));
    }

    protected abstract boolean zza(zza com_google_android_gms_internal_zzaj_zza, zza com_google_android_gms_internal_zzaj_zza2, Map<String, zza> map);
}
