package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaj.zza;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class zzam {
    private final Set<String> zzbEh;
    private final String zzbEi;

    public zzam(String str, String... strArr) {
        this.zzbEi = str;
        this.zzbEh = new HashSet(strArr.length);
        for (Object add : strArr) {
            this.zzbEh.add(add);
        }
    }

    public abstract boolean zzOw();

    public String zzPg() {
        return this.zzbEi;
    }

    public Set<String> zzPh() {
        return this.zzbEh;
    }

    public abstract zza zzY(Map<String, zza> map);

    boolean zzf(Set<String> set) {
        return set.containsAll(this.zzbEh);
    }
}
