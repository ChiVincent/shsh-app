package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzcf extends zzam {
    private static final String ID = zzag.OS_VERSION.toString();

    public zzcf() {
        super(ID, new String[0]);
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        return zzdm.zzR(VERSION.RELEASE);
    }
}
