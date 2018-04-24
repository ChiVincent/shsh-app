package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzrs extends zzru {
    private final zzsc zzacF;

    class C04015 implements Runnable {
        final /* synthetic */ zzrs zzacH;

        C04015(zzrs com_google_android_gms_internal_zzrs) {
            this.zzacH = com_google_android_gms_internal_zzrs;
        }

        public void run() {
            this.zzacH.zzacF.zznj();
        }
    }

    class C04037 implements Callable<Void> {
        final /* synthetic */ zzrs zzacH;

        C04037(zzrs com_google_android_gms_internal_zzrs) {
            this.zzacH = com_google_android_gms_internal_zzrs;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzbl();
        }

        public Void zzbl() throws Exception {
            this.zzacH.zzacF.zzof();
            return null;
        }
    }

    public zzrs(zzrw com_google_android_gms_internal_zzrw, zzrx com_google_android_gms_internal_zzrx) {
        super(com_google_android_gms_internal_zzrw);
        zzac.zzw(com_google_android_gms_internal_zzrx);
        this.zzacF = com_google_android_gms_internal_zzrx.zzj(com_google_android_gms_internal_zzrw);
    }

    void onServiceConnected() {
        zzmq();
        this.zzacF.onServiceConnected();
    }

    public void setLocalDispatchPeriod(final int i) {
        zznA();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(i));
        zznt().zzg(new Runnable(this) {
            final /* synthetic */ zzrs zzacH;

            public void run() {
                this.zzacH.zzacF.zzw(((long) i) * 1000);
            }
        });
    }

    public void start() {
        this.zzacF.start();
    }

    public void zzV(final boolean z) {
        zza("Network connectivity status changed", Boolean.valueOf(z));
        zznt().zzg(new Runnable(this) {
            final /* synthetic */ zzrs zzacH;

            public void run() {
                this.zzacH.zzacF.zzV(z);
            }
        });
    }

    public long zza(zzry com_google_android_gms_internal_zzry) {
        zznA();
        zzac.zzw(com_google_android_gms_internal_zzry);
        zzmq();
        long zza = this.zzacF.zza(com_google_android_gms_internal_zzry, true);
        if (zza == 0) {
            this.zzacF.zzc(com_google_android_gms_internal_zzry);
        }
        return zza;
    }

    public void zza(final zzso com_google_android_gms_internal_zzso) {
        zznA();
        zznt().zzg(new Runnable(this) {
            final /* synthetic */ zzrs zzacH;

            public void run() {
                this.zzacH.zzacF.zzb(com_google_android_gms_internal_zzso);
            }
        });
    }

    public void zza(final zzst com_google_android_gms_internal_zzst) {
        zzac.zzw(com_google_android_gms_internal_zzst);
        zznA();
        zzb("Hit delivery requested", com_google_android_gms_internal_zzst);
        zznt().zzg(new Runnable(this) {
            final /* synthetic */ zzrs zzacH;

            public void run() {
                this.zzacH.zzacF.zza(com_google_android_gms_internal_zzst);
            }
        });
    }

    public void zza(final String str, final Runnable runnable) {
        zzac.zzh(str, "campaign param can't be empty");
        zznt().zzg(new Runnable(this) {
            final /* synthetic */ zzrs zzacH;

            public void run() {
                this.zzacH.zzacF.zzbW(str);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    protected void zzmr() {
        this.zzacF.initialize();
    }

    public void zznj() {
        zznA();
        zznt().zzg(new C04015(this));
    }

    public void zznk() {
        zznA();
        Context context = getContext();
        if (zztb.zzT(context) && zztc.zzU(context)) {
            Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            context.startService(intent);
            return;
        }
        zza(null);
    }

    public boolean zznl() {
        zznA();
        try {
            zznt().zzc(new C04037(this)).get(4, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            zzd("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zze("syncDispatchLocalHits failed", e2);
            return false;
        } catch (TimeoutException e3) {
            zzd("syncDispatchLocalHits timed out", e3);
            return false;
        }
    }

    public void zznm() {
        zznA();
        zzh.zzmq();
        this.zzacF.zznm();
    }

    public void zznn() {
        zzbO("Radio powered up");
        zznk();
    }

    void zzno() {
        zzmq();
        this.zzacF.zzno();
    }
}
