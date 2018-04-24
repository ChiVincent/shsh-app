package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzbe extends zzam {
    private static final String ID = zzag.INSTALL_REFERRER.toString();
    private static final String zzbCK = zzah.COMPONENT.toString();
    private final Context zzqr;

    public zzbe(Context context) {
        super(ID, new String[0]);
        this.zzqr = context;
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        String zzH = zzbf.zzH(this.zzqr, ((zza) map.get(zzbCK)) != null ? zzdm.zze((zza) map.get(zzbCK)) : null);
        return zzH != null ? zzdm.zzR(zzH) : zzdm.zzQm();
    }
}
