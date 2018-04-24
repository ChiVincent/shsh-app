package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzai.zzj;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class zzcu implements zze {
    private boolean mClosed;
    private final Context mContext;
    private final String zzbCS;
    private String zzbDq;
    private zzbn<zzj> zzbFr;
    private zzt zzbFs;
    private final ScheduledExecutorService zzbFu;
    private final zza zzbFv;
    private ScheduledFuture<?> zzbFw;

    interface zza {
        zzct zza(zzt com_google_android_gms_tagmanager_zzt);
    }

    interface zzb {
        ScheduledExecutorService zzPG();
    }

    class C07821 implements zzb {
        C07821(zzcu com_google_android_gms_tagmanager_zzcu) {
        }

        public ScheduledExecutorService zzPG() {
            return Executors.newSingleThreadScheduledExecutor();
        }
    }

    class C07832 implements zza {
        final /* synthetic */ zzcu zzbFx;

        C07832(zzcu com_google_android_gms_tagmanager_zzcu) {
            this.zzbFx = com_google_android_gms_tagmanager_zzcu;
        }

        public zzct zza(zzt com_google_android_gms_tagmanager_zzt) {
            return new zzct(this.zzbFx.mContext, this.zzbFx.zzbCS, com_google_android_gms_tagmanager_zzt);
        }
    }

    public zzcu(Context context, String str, zzt com_google_android_gms_tagmanager_zzt) {
        this(context, str, com_google_android_gms_tagmanager_zzt, null, null);
    }

    zzcu(Context context, String str, zzt com_google_android_gms_tagmanager_zzt, zzb com_google_android_gms_tagmanager_zzcu_zzb, zza com_google_android_gms_tagmanager_zzcu_zza) {
        this.zzbFs = com_google_android_gms_tagmanager_zzt;
        this.mContext = context;
        this.zzbCS = str;
        if (com_google_android_gms_tagmanager_zzcu_zzb == null) {
            com_google_android_gms_tagmanager_zzcu_zzb = new C07821(this);
        }
        this.zzbFu = com_google_android_gms_tagmanager_zzcu_zzb.zzPG();
        if (com_google_android_gms_tagmanager_zzcu_zza == null) {
            this.zzbFv = new C07832(this);
        } else {
            this.zzbFv = com_google_android_gms_tagmanager_zzcu_zza;
        }
    }

    private synchronized void zzPF() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    private zzct zzhs(String str) {
        zzct zza = this.zzbFv.zza(this.zzbFs);
        zza.zza(this.zzbFr);
        zza.zzhc(this.zzbDq);
        zza.zzhr(str);
        return zza;
    }

    public synchronized void release() {
        zzPF();
        if (this.zzbFw != null) {
            this.zzbFw.cancel(false);
        }
        this.zzbFu.shutdown();
        this.mClosed = true;
    }

    public synchronized void zza(zzbn<zzj> com_google_android_gms_tagmanager_zzbn_com_google_android_gms_internal_zzai_zzj) {
        zzPF();
        this.zzbFr = com_google_android_gms_tagmanager_zzbn_com_google_android_gms_internal_zzai_zzj;
    }

    public synchronized void zzf(long j, String str) {
        String str2 = this.zzbCS;
        zzbo.m11v(new StringBuilder(String.valueOf(str2).length() + 55).append("loadAfterDelay: containerId=").append(str2).append(" delay=").append(j).toString());
        zzPF();
        if (this.zzbFr == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.zzbFw != null) {
            this.zzbFw.cancel(false);
        }
        this.zzbFw = this.zzbFu.schedule(zzhs(str), j, TimeUnit.MILLISECONDS);
    }

    public synchronized void zzhc(String str) {
        zzPF();
        this.zzbDq = str;
    }
}
