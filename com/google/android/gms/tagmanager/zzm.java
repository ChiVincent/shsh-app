package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class zzm<K, V> {
    final zza<K, V> zzbCR = new C07911(this);

    public interface zza<K, V> {
        int sizeOf(K k, V v);
    }

    class C07911 implements zza<K, V> {
        C07911(zzm com_google_android_gms_tagmanager_zzm) {
        }

        public int sizeOf(K k, V v) {
            return 1;
        }
    }

    public zzl<K, V> zza(int i, zza<K, V> com_google_android_gms_tagmanager_zzm_zza_K__V) {
        if (i > 0) {
            return zzyS() < 12 ? new zzde(i, com_google_android_gms_tagmanager_zzm_zza_K__V) : new zzbi(i, com_google_android_gms_tagmanager_zzm_zza_K__V);
        } else {
            throw new IllegalArgumentException("maxSize <= 0");
        }
    }

    int zzyS() {
        return VERSION.SDK_INT;
    }
}
