package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzdo extends zzam {
    private static final String ID = zzag.UPPERCASE_STRING.toString();
    private static final String zzbEd = zzah.ARG0.toString();

    public zzdo() {
        super(ID, zzbEd);
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        return zzdm.zzR(zzdm.zze((zza) map.get(zzbEd)).toUpperCase());
    }
}
