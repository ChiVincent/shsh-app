package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

class zzda implements zzcl {
    private final long zzafl;
    private final int zzafm;
    private double zzafn;
    private final Object zzafp;
    private long zzbFY;
    private final zze zzuI;

    public zzda() {
        this(60, 2000);
    }

    public zzda(int i, long j) {
        this.zzafp = new Object();
        this.zzafm = i;
        this.zzafn = (double) this.zzafm;
        this.zzafl = j;
        this.zzuI = zzh.zzyv();
    }

    public boolean zzpv() {
        boolean z;
        synchronized (this.zzafp) {
            long currentTimeMillis = this.zzuI.currentTimeMillis();
            if (this.zzafn < ((double) this.zzafm)) {
                double d = ((double) (currentTimeMillis - this.zzbFY)) / ((double) this.zzafl);
                if (d > 0.0d) {
                    this.zzafn = Math.min((double) this.zzafm, d + this.zzafn);
                }
            }
            this.zzbFY = currentTimeMillis;
            if (this.zzafn >= 1.0d) {
                this.zzafn -= 1.0d;
                z = true;
            } else {
                zzbo.zzbe("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
