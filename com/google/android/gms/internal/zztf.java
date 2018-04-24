package com.google.android.gms.internal;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

public class zztf implements zzsh {
    public String zzaar;
    public double zzafN = -1.0d;
    public int zzafO = -1;
    public int zzafP = -1;
    public int zzafQ = -1;
    public int zzafR = -1;
    public Map<String, String> zzafS = new HashMap();

    public int getSessionTimeout() {
        return this.zzafO;
    }

    public String getTrackingId() {
        return this.zzaar;
    }

    public String zzcc(String str) {
        String str2 = (String) this.zzafS.get(str);
        return str2 != null ? str2 : str;
    }

    public boolean zzpS() {
        return this.zzaar != null;
    }

    public boolean zzpT() {
        return this.zzafN >= 0.0d;
    }

    public double zzpU() {
        return this.zzafN;
    }

    public boolean zzpV() {
        return this.zzafO >= 0;
    }

    public boolean zzpW() {
        return this.zzafP != -1;
    }

    public boolean zzpX() {
        return this.zzafP == 1;
    }

    public boolean zzpY() {
        return this.zzafQ != -1;
    }

    public boolean zzpZ() {
        return this.zzafQ == 1;
    }

    public boolean zzqa() {
        return this.zzafR == 1;
    }

    public String zzr(Activity activity) {
        return zzcc(activity.getClass().getCanonicalName());
    }
}
