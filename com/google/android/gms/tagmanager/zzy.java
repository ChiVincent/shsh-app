package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.List;
import java.util.Map;

class zzy extends zzdk {
    private static final String ID = zzag.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzah.VALUE.toString();
    private static final String zzbDT = zzah.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzbCT;

    public zzy(DataLayer dataLayer) {
        super(ID, VALUE);
        this.zzbCT = dataLayer;
    }

    private void zza(zza com_google_android_gms_internal_zzaj_zza) {
        if (com_google_android_gms_internal_zzaj_zza != null && com_google_android_gms_internal_zzaj_zza != zzdm.zzQg()) {
            String zze = zzdm.zze(com_google_android_gms_internal_zzaj_zza);
            if (zze != zzdm.zzQl()) {
                this.zzbCT.zzhd(zze);
            }
        }
    }

    private void zzb(zza com_google_android_gms_internal_zzaj_zza) {
        if (com_google_android_gms_internal_zzaj_zza != null && com_google_android_gms_internal_zzaj_zza != zzdm.zzQg()) {
            Object zzj = zzdm.zzj(com_google_android_gms_internal_zzaj_zza);
            if (zzj instanceof List) {
                for (Object zzj2 : (List) zzj2) {
                    if (zzj2 instanceof Map) {
                        this.zzbCT.push((Map) zzj2);
                    }
                }
            }
        }
    }

    public void zzaa(Map<String, zza> map) {
        zzb((zza) map.get(VALUE));
        zza((zza) map.get(zzbDT));
    }
}
