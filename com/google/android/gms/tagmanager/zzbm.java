package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;

class zzbm implements zzcl {
    private final String zzaca;
    private final long zzafl;
    private final int zzafm;
    private double zzafn;
    private long zzafo;
    private final Object zzafp = new Object();
    private final long zzbEF;
    private final zze zzuI;

    public zzbm(int i, int i2, long j, long j2, String str, zze com_google_android_gms_common_util_zze) {
        this.zzafm = i2;
        this.zzafn = (double) Math.min(i, i2);
        this.zzafl = j;
        this.zzbEF = j2;
        this.zzaca = str;
        this.zzuI = com_google_android_gms_common_util_zze;
    }

    public boolean zzpv() {
        boolean z = false;
        synchronized (this.zzafp) {
            long currentTimeMillis = this.zzuI.currentTimeMillis();
            String str;
            if (currentTimeMillis - this.zzafo < this.zzbEF) {
                str = this.zzaca;
                zzbo.zzbe(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
            } else {
                if (this.zzafn < ((double) this.zzafm)) {
                    double d = ((double) (currentTimeMillis - this.zzafo)) / ((double) this.zzafl);
                    if (d > 0.0d) {
                        this.zzafn = Math.min((double) this.zzafm, d + this.zzafn);
                    }
                }
                this.zzafo = currentTimeMillis;
                if (this.zzafn >= 1.0d) {
                    this.zzafn -= 1.0d;
                    z = true;
                } else {
                    str = this.zzaca;
                    zzbo.zzbe(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                }
            }
        }
        return z;
    }
}
