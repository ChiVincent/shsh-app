package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzak extends zzam {
    private static final String ID = zzag.EVENT.toString();
    private final zzcx zzbCU;

    public zzak(zzcx com_google_android_gms_tagmanager_zzcx) {
        super(ID, new String[0]);
        this.zzbCU = com_google_android_gms_tagmanager_zzcx;
    }

    public boolean zzOw() {
        return false;
    }

    public zza zzY(Map<String, zza> map) {
        String zzPJ = this.zzbCU.zzPJ();
        return zzPJ == null ? zzdm.zzQm() : zzdm.zzR(zzPJ);
    }
}
