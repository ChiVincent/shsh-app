package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class zzdc extends zzdb {
    private static final Object zzbFZ = new Object();
    private static zzdc zzbGl;
    private boolean connected = true;
    private Context zzbGa;
    private zzaw zzbGb;
    private volatile zzau zzbGc;
    private int zzbGd = 1800000;
    private boolean zzbGe = true;
    private boolean zzbGf = false;
    private boolean zzbGg = true;
    private zzax zzbGh = new C07881(this);
    private zza zzbGi;
    private zzbt zzbGj;
    private boolean zzbGk = false;

    class C04292 implements Runnable {
        final /* synthetic */ zzdc zzbGm;

        C04292(zzdc com_google_android_gms_tagmanager_zzdc) {
            this.zzbGm = com_google_android_gms_tagmanager_zzdc;
        }

        public void run() {
            this.zzbGm.zzbGb.dispatch();
        }
    }

    public interface zza {
        void cancel();

        void zzPY();

        void zzx(long j);
    }

    class C07881 implements zzax {
        final /* synthetic */ zzdc zzbGm;

        C07881(zzdc com_google_android_gms_tagmanager_zzdc) {
            this.zzbGm = com_google_android_gms_tagmanager_zzdc;
        }

        public void zzaM(boolean z) {
            this.zzbGm.zze(z, this.zzbGm.connected);
        }
    }

    private class zzb implements zza {
        private Handler handler;
        final /* synthetic */ zzdc zzbGm;

        class C04301 implements Callback {
            final /* synthetic */ zzb zzbGn;

            C04301(zzb com_google_android_gms_tagmanager_zzdc_zzb) {
                this.zzbGn = com_google_android_gms_tagmanager_zzdc_zzb;
            }

            public boolean handleMessage(Message message) {
                if (1 == message.what && zzdc.zzbFZ.equals(message.obj)) {
                    this.zzbGn.zzbGm.dispatch();
                    if (!this.zzbGn.zzbGm.isPowerSaveMode()) {
                        this.zzbGn.zzx((long) this.zzbGn.zzbGm.zzbGd);
                    }
                }
                return true;
            }
        }

        private zzb(zzdc com_google_android_gms_tagmanager_zzdc) {
            this.zzbGm = com_google_android_gms_tagmanager_zzdc;
            this.handler = new Handler(this.zzbGm.zzbGa.getMainLooper(), new C04301(this));
        }

        private Message obtainMessage() {
            return this.handler.obtainMessage(1, zzdc.zzbFZ);
        }

        public void cancel() {
            this.handler.removeMessages(1, zzdc.zzbFZ);
        }

        public void zzPY() {
            this.handler.removeMessages(1, zzdc.zzbFZ);
            this.handler.sendMessage(obtainMessage());
        }

        public void zzx(long j) {
            this.handler.removeMessages(1, zzdc.zzbFZ);
            this.handler.sendMessageDelayed(obtainMessage(), j);
        }
    }

    private zzdc() {
    }

    private boolean isPowerSaveMode() {
        return this.zzbGk || !this.connected || this.zzbGd <= 0;
    }

    public static zzdc zzPT() {
        if (zzbGl == null) {
            zzbGl = new zzdc();
        }
        return zzbGl;
    }

    private void zzPU() {
        this.zzbGj = new zzbt(this);
        this.zzbGj.zzbJ(this.zzbGa);
    }

    private void zzPV() {
        this.zzbGi = new zzb();
        if (this.zzbGd > 0) {
            this.zzbGi.zzx((long) this.zzbGd);
        }
    }

    private void zzog() {
        if (isPowerSaveMode()) {
            this.zzbGi.cancel();
            zzbo.m11v("PowerSaveMode initiated.");
            return;
        }
        this.zzbGi.zzx((long) this.zzbGd);
        zzbo.m11v("PowerSaveMode terminated.");
    }

    public synchronized void dispatch() {
        if (this.zzbGf) {
            this.zzbGc.zzp(new C04292(this));
        } else {
            zzbo.m11v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzbGe = true;
        }
    }

    synchronized zzaw zzPW() {
        if (this.zzbGb == null) {
            if (this.zzbGa == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.zzbGb = new zzcg(this.zzbGh, this.zzbGa);
        }
        if (this.zzbGi == null) {
            zzPV();
        }
        this.zzbGf = true;
        if (this.zzbGe) {
            dispatch();
            this.zzbGe = false;
        }
        if (this.zzbGj == null && this.zzbGg) {
            zzPU();
        }
        return this.zzbGb;
    }

    synchronized void zza(Context context, zzau com_google_android_gms_tagmanager_zzau) {
        if (this.zzbGa == null) {
            this.zzbGa = context.getApplicationContext();
            if (this.zzbGc == null) {
                this.zzbGc = com_google_android_gms_tagmanager_zzau;
            }
        }
    }

    public synchronized void zzaN(boolean z) {
        zze(this.zzbGk, z);
    }

    synchronized void zze(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.zzbGk = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            zzog();
        }
    }

    public synchronized void zznn() {
        if (!isPowerSaveMode()) {
            this.zzbGi.zzPY();
        }
    }
}
