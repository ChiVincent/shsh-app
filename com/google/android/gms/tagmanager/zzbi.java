package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;
import com.google.android.gms.tagmanager.zzm.zza;

@TargetApi(12)
class zzbi<K, V> implements zzl<K, V> {
    private LruCache<K, V> zzbED;

    zzbi(int i, final zza<K, V> com_google_android_gms_tagmanager_zzm_zza_K__V) {
        this.zzbED = new LruCache<K, V>(this, i) {
            protected int sizeOf(K k, V v) {
                return com_google_android_gms_tagmanager_zzm_zza_K__V.sizeOf(k, v);
            }
        };
    }

    public V get(K k) {
        return this.zzbED.get(k);
    }

    public void zzi(K k, V v) {
        this.zzbED.put(k, v);
    }
}
