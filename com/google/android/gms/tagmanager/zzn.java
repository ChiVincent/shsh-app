package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzn extends zzam {
    private static final String ID = zzag.CONSTANT.toString();
    private static final String VALUE = zzah.VALUE.toString();

    public zzn() {
        super(ID, VALUE);
    }

    public static String zzOy() {
        return ID;
    }

    public static String zzOz() {
        return VALUE;
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        return (zza) map.get(VALUE);
    }
}
