package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.zzm.zza;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class zzde<K, V> implements zzl<K, V> {
    private final Map<K, V> zzbGp = new HashMap();
    private final int zzbGq;
    private final zza<K, V> zzbGr;
    private int zzbGs;

    zzde(int i, zza<K, V> com_google_android_gms_tagmanager_zzm_zza_K__V) {
        this.zzbGq = i;
        this.zzbGr = com_google_android_gms_tagmanager_zzm_zza_K__V;
    }

    public synchronized V get(K k) {
        return this.zzbGp.get(k);
    }

    public synchronized void zzi(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.zzbGs += this.zzbGr.sizeOf(k, v);
        if (this.zzbGs > this.zzbGq) {
            Iterator it = this.zzbGp.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                this.zzbGs -= this.zzbGr.sizeOf(entry.getKey(), entry.getValue());
                it.remove();
                if (this.zzbGs <= this.zzbGq) {
                    break;
                }
            }
        }
        this.zzbGp.put(k, v);
    }
}
