package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzw extends zzam {
    private static final String ID = zzag.CUSTOM_VAR.toString();
    private static final String NAME = zzah.NAME.toString();
    private static final String zzbDI = zzah.DEFAULT_VALUE.toString();
    private final DataLayer zzbCT;

    public zzw(DataLayer dataLayer) {
        super(ID, NAME);
        this.zzbCT = dataLayer;
    }

    public boolean zzOw() {
        return false;
    }

    public zza zzY(Map<String, zza> map) {
        Object obj = this.zzbCT.get(zzdm.zze((zza) map.get(NAME)));
        if (obj != null) {
            return zzdm.zzR(obj);
        }
        zza com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbDI);
        return com_google_android_gms_internal_zzaj_zza != null ? com_google_android_gms_internal_zzaj_zza : zzdm.zzQm();
    }
}
