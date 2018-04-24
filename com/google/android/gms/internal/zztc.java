package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzac;

public final class zztc {
    private static Boolean zzaax;
    private final Context mContext;
    private final Handler mHandler = new Handler();
    private final zza zzafI;

    public interface zza {
        boolean callServiceStopSelfResult(int i);

        Context getContext();
    }

    public zztc(zza com_google_android_gms_internal_zztc_zza) {
        this.mContext = com_google_android_gms_internal_zztc_zza.getContext();
        zzac.zzw(this.mContext);
        this.zzafI = com_google_android_gms_internal_zztc_zza;
    }

    public static boolean zzU(Context context) {
        zzac.zzw(context);
        if (zzaax != null) {
            return zzaax.booleanValue();
        }
        boolean zzr = zztg.zzr(context, "com.google.android.gms.analytics.AnalyticsService");
        zzaax = Boolean.valueOf(zzr);
        return zzr;
    }

    private void zzlS() {
        try {
            synchronized (zztb.zztU) {
                zzayd com_google_android_gms_internal_zzayd = zztb.zzaav;
                if (com_google_android_gms_internal_zzayd != null && com_google_android_gms_internal_zzayd.isHeld()) {
                    com_google_android_gms_internal_zzayd.release();
                }
            }
        } catch (SecurityException e) {
        }
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onCreate() {
        zzrw.zzW(this.mContext).zznr().zzbO("Local AnalyticsService is starting up");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onDestroy() {
        zzrw.zzW(this.mContext).zznr().zzbO("Local AnalyticsService is shutting down");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int onStartCommand(Intent intent, int i, final int i2) {
        zzlS();
        final zzrw zzW = zzrw.zzW(this.mContext);
        final zzsx zznr = zzW.zznr();
        if (intent == null) {
            zznr.zzbR("AnalyticsService started with null intent");
        } else {
            String action = intent.getAction();
            zznr.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
                zzW.zzlZ().zza(new zzso(this) {
                    final /* synthetic */ zztc zzafK;

                    class C04131 implements Runnable {
                        final /* synthetic */ C07751 zzafL;

                        C04131(C07751 c07751) {
                            this.zzafL = c07751;
                        }

                        public void run() {
                            if (this.zzafL.zzafK.zzafI.callServiceStopSelfResult(i2)) {
                                zznr.zzbO("Local AnalyticsService processed last dispatch request");
                            }
                        }
                    }

                    public void zzf(Throwable th) {
                        this.zzafK.mHandler.post(new C04131(this));
                    }
                });
            }
        }
        return 2;
    }
}
