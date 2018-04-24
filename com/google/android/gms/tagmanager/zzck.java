package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzck extends zzam {
    private static final String ID = zzag.RANDOM.toString();
    private static final String zzbFj = zzah.MIN.toString();
    private static final String zzbFk = zzah.MAX.toString();

    public zzck() {
        super(ID, new String[0]);
    }

    public boolean zzOw() {
        return false;
    }

    public zza zzY(Map<String, zza> map) {
        double doubleValue;
        double d;
        zza com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbFj);
        zza com_google_android_gms_internal_zzaj_zza2 = (zza) map.get(zzbFk);
        if (!(com_google_android_gms_internal_zzaj_zza == null || com_google_android_gms_internal_zzaj_zza == zzdm.zzQm() || com_google_android_gms_internal_zzaj_zza2 == null || com_google_android_gms_internal_zzaj_zza2 == zzdm.zzQm())) {
            zzdl zzf = zzdm.zzf(com_google_android_gms_internal_zzaj_zza);
            zzdl zzf2 = zzdm.zzf(com_google_android_gms_internal_zzaj_zza2);
            if (!(zzf == zzdm.zzQk() || zzf2 == zzdm.zzQk())) {
                double doubleValue2 = zzf.doubleValue();
                doubleValue = zzf2.doubleValue();
                if (doubleValue2 <= doubleValue) {
                    d = doubleValue2;
                    return zzdm.zzR(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
                }
            }
        }
        doubleValue = 2.147483647E9d;
        d = 0.0d;
        return zzdm.zzR(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
    }
}
