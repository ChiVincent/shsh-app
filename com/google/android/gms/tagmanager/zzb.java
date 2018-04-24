package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzb extends zzam {
    private static final String ID = zzag.ADVERTISER_ID.toString();
    private final zza zzbCJ;

    public zzb(Context context) {
        this(zza.zzbA(context));
    }

    zzb(zza com_google_android_gms_tagmanager_zza) {
        super(ID, new String[0]);
        this.zzbCJ = com_google_android_gms_tagmanager_zza;
        this.zzbCJ.zzOq();
    }

    public boolean zzOw() {
        return false;
    }

    public zza zzY(Map<String, zza> map) {
        String zzOq = this.zzbCJ.zzOq();
        return zzOq == null ? zzdm.zzQm() : zzdm.zzR(zzOq);
    }
}
