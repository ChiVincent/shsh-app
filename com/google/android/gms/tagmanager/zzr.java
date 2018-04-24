package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzr extends zzam {
    private static final String ID = zzag.CONTAINER_VERSION.toString();
    private final String zzaux;

    public zzr(String str) {
        super(ID, new String[0]);
        this.zzaux = str;
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        return this.zzaux == null ? zzdm.zzQm() : zzdm.zzR(this.zzaux);
    }
}
