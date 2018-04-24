package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class zzas {
    private final long zzafh;
    private final long zzbEk;
    private final long zzbEl;
    private String zzbEm;

    zzas(long j, long j2, long j3) {
        this.zzbEk = j;
        this.zzafh = j2;
        this.zzbEl = j3;
    }

    long zzPi() {
        return this.zzbEk;
    }

    long zzPj() {
        return this.zzbEl;
    }

    String zzPk() {
        return this.zzbEm;
    }

    void zzhl(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzbEm = str;
        }
    }
}
