package com.google.android.gms.tagmanager;

import android.util.Log;

public class zzz implements zzbp {
    private int zzaeb = 5;

    public void mo2212e(String str) {
        if (this.zzaeb <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    public void setLogLevel(int i) {
        this.zzaeb = i;
    }

    public void mo2214v(String str) {
        if (this.zzaeb <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    public void zzb(String str, Throwable th) {
        if (this.zzaeb <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    public void zzbc(String str) {
        if (this.zzaeb <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }

    public void zzbd(String str) {
        if (this.zzaeb <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    public void zzbe(String str) {
        if (this.zzaeb <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }

    public void zzc(String str, Throwable th) {
        if (this.zzaeb <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }
}
