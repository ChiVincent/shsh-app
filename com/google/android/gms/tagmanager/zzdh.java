package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

abstract class zzdh extends zzci {
    public zzdh(String str) {
        super(str);
    }

    protected boolean zza(zza com_google_android_gms_internal_zzaj_zza, zza com_google_android_gms_internal_zzaj_zza2, Map<String, zza> map) {
        String zze = zzdm.zze(com_google_android_gms_internal_zzaj_zza);
        String zze2 = zzdm.zze(com_google_android_gms_internal_zzaj_zza2);
        return (zze == zzdm.zzQl() || zze2 == zzdm.zzQl()) ? false : zza(zze, zze2, (Map) map);
    }

    protected abstract boolean zza(String str, String str2, Map<String, zza> map);
}
