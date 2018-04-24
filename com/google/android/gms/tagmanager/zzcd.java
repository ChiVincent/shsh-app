package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

abstract class zzcd extends zzci {
    public zzcd(String str) {
        super(str);
    }

    protected boolean zza(zza com_google_android_gms_internal_zzaj_zza, zza com_google_android_gms_internal_zzaj_zza2, Map<String, zza> map) {
        zzdl zzf = zzdm.zzf(com_google_android_gms_internal_zzaj_zza);
        zzdl zzf2 = zzdm.zzf(com_google_android_gms_internal_zzaj_zza2);
        return (zzf == zzdm.zzQk() || zzf2 == zzdm.zzQk()) ? false : zza(zzf, zzf2, (Map) map);
    }

    protected abstract boolean zza(zzdl com_google_android_gms_tagmanager_zzdl, zzdl com_google_android_gms_tagmanager_zzdl2, Map<String, zza> map);
}
