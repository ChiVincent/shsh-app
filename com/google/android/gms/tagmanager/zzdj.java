package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

public class zzdj {
    private Context mContext;
    private Tracker zzaaC;
    private GoogleAnalytics zzaaE;

    static class zza implements Logger {
        zza() {
        }

        private static int zzmX(int i) {
            switch (i) {
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                default:
                    return 3;
            }
        }

        public void error(Exception exception) {
            zzbo.zzb("", exception);
        }

        public void error(String str) {
            zzbo.m10e(str);
        }

        public int getLogLevel() {
            return zzmX(zzbo.getLogLevel());
        }

        public void info(String str) {
            zzbo.zzbd(str);
        }

        public void setLogLevel(int i) {
            zzbo.zzbe("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }

        public void verbose(String str) {
            zzbo.m11v(str);
        }

        public void warn(String str) {
            zzbo.zzbe(str);
        }
    }

    public zzdj(Context context) {
        this.mContext = context;
    }

    private synchronized void zzhx(String str) {
        if (this.zzaaE == null) {
            this.zzaaE = GoogleAnalytics.getInstance(this.mContext);
            this.zzaaE.setLogger(new zza());
            this.zzaaC = this.zzaaE.newTracker(str);
        }
    }

    public Tracker zzhw(String str) {
        zzhx(str);
        return this.zzaaC;
    }
}
