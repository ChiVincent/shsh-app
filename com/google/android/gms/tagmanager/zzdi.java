package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzdi extends zzam {
    private static final String ID = zzag.TIME.toString();

    public zzdi() {
        super(ID, new String[0]);
    }

    public boolean zzOw() {
        return false;
    }

    public zza zzY(Map<String, zza> map) {
        return zzdm.zzR(Long.valueOf(System.currentTimeMillis()));
    }
}
