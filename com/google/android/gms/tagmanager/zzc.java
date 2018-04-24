package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzc extends zzam {
    private static final String ID = zzag.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzbCJ;

    public zzc(Context context) {
        this(zza.zzbA(context));
    }

    zzc(zza com_google_android_gms_tagmanager_zza) {
        super(ID, new String[0]);
        this.zzbCJ = com_google_android_gms_tagmanager_zza;
    }

    public boolean zzOw() {
        return false;
    }

    public zza zzY(Map<String, zza> map) {
        return zzdm.zzR(Boolean.valueOf(!this.zzbCJ.isLimitAdTrackingEnabled()));
    }
}
