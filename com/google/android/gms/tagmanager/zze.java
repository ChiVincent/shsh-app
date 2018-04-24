package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zze extends zzam {
    private static final String ID = zzag.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzbCK = zzah.COMPONENT.toString();
    private static final String zzbCL = zzah.CONVERSION_ID.toString();
    private final Context zzqr;

    public zze(Context context) {
        super(ID, zzbCL);
        this.zzqr = context;
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        zza com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbCL);
        if (com_google_android_gms_internal_zzaj_zza == null) {
            return zzdm.zzQm();
        }
        String zze = zzdm.zze(com_google_android_gms_internal_zzaj_zza);
        com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbCK);
        String zzj = zzbf.zzj(this.zzqr, zze, com_google_android_gms_internal_zzaj_zza != null ? zzdm.zze(com_google_android_gms_internal_zzaj_zza) : null);
        return zzj != null ? zzdm.zzR(zzj) : zzdm.zzQm();
    }
}
