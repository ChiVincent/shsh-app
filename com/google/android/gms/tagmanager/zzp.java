package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzai.zzj;
import com.google.android.gms.internal.zzbgh;
import com.google.android.gms.internal.zzzx;

public class zzp extends zzzx<ContainerHolder> {
    private final Context mContext;
    private final String zzbCS;
    private long zzbCX;
    private final TagManager zzbDe;
    private final zzd zzbDh;
    private final zzcl zzbDi;
    private final int zzbDj;
    private final zzq zzbDk;
    private zzf zzbDl;
    private zzbgh zzbDm;
    private volatile zzo zzbDn;
    private volatile boolean zzbDo;
    private zzj zzbDp;
    private String zzbDq;
    private zze zzbDr;
    private zza zzbDs;
    private final Looper zzrx;
    private final com.google.android.gms.common.util.zze zzuI;

    class C04321 {
    }

    interface zza {
        boolean zzb(Container container);
    }

    class C07922 implements com.google.android.gms.tagmanager.zzo.zza {
        final /* synthetic */ zzp zzbDt;

        C07922(zzp com_google_android_gms_tagmanager_zzp) {
            this.zzbDt = com_google_android_gms_tagmanager_zzp;
        }

        public String zzOC() {
            return this.zzbDt.zzOC();
        }

        public void zzOE() {
            zzbo.zzbe("Refresh ignored: container loaded as default only.");
        }

        public void zzgZ(String str) {
            this.zzbDt.zzgZ(str);
        }
    }

    private class zzb implements zzbn<com.google.android.gms.internal.zzbgg.zza> {
        final /* synthetic */ zzp zzbDt;

        private zzb(zzp com_google_android_gms_tagmanager_zzp) {
            this.zzbDt = com_google_android_gms_tagmanager_zzp;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            zza((com.google.android.gms.internal.zzbgg.zza) obj);
        }

        public void zza(com.google.android.gms.internal.zzbgg.zza com_google_android_gms_internal_zzbgg_zza) {
            zzj com_google_android_gms_internal_zzai_zzj;
            if (com_google_android_gms_internal_zzbgg_zza.zzbLi != null) {
                com_google_android_gms_internal_zzai_zzj = com_google_android_gms_internal_zzbgg_zza.zzbLi;
            } else {
                com.google.android.gms.internal.zzai.zzf com_google_android_gms_internal_zzai_zzf = com_google_android_gms_internal_zzbgg_zza.zzlu;
                com_google_android_gms_internal_zzai_zzj = new zzj();
                com_google_android_gms_internal_zzai_zzj.zzlu = com_google_android_gms_internal_zzai_zzf;
                com_google_android_gms_internal_zzai_zzj.zzlt = null;
                com_google_android_gms_internal_zzai_zzj.zzlv = com_google_android_gms_internal_zzai_zzf.version;
            }
            this.zzbDt.zza(com_google_android_gms_internal_zzai_zzj, com_google_android_gms_internal_zzbgg_zza.zzbLh, true);
        }

        public void zza(com.google.android.gms.tagmanager.zzbn.zza com_google_android_gms_tagmanager_zzbn_zza) {
            if (!this.zzbDt.zzbDo) {
                this.zzbDt.zzav(0);
            }
        }
    }

    private class zzc implements zzbn<zzj> {
        final /* synthetic */ zzp zzbDt;

        private zzc(zzp com_google_android_gms_tagmanager_zzp) {
            this.zzbDt = com_google_android_gms_tagmanager_zzp;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            zzb((zzj) obj);
        }

        public void zza(com.google.android.gms.tagmanager.zzbn.zza com_google_android_gms_tagmanager_zzbn_zza) {
            if (com_google_android_gms_tagmanager_zzbn_zza == com.google.android.gms.tagmanager.zzbn.zza.SERVER_UNAVAILABLE_ERROR) {
                this.zzbDt.zzbDk.zzON();
            }
            synchronized (this.zzbDt) {
                if (!this.zzbDt.isReady()) {
                    if (this.zzbDt.zzbDn != null) {
                        this.zzbDt.zzb(this.zzbDt.zzbDn);
                    } else {
                        this.zzbDt.zzb(this.zzbDt.zzbK(Status.zzayk));
                    }
                }
            }
            this.zzbDt.zzav(this.zzbDt.zzbDk.zzOM());
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void zzb(com.google.android.gms.internal.zzai.zzj r6) {
            /*
            r5 = this;
            r0 = r5.zzbDt;
            r0 = r0.zzbDk;
            r0.zzOO();
            r1 = r5.zzbDt;
            monitor-enter(r1);
            r0 = r6.zzlu;	 Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x003a;
        L_0x0010:
            r0 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r0 = r0.zzbDp;	 Catch:{ all -> 0x0077 }
            r0 = r0.zzlu;	 Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x0030;
        L_0x001a:
            r0 = "Current resource is null; network resource is also null";
            com.google.android.gms.tagmanager.zzbo.m10e(r0);	 Catch:{ all -> 0x0077 }
            r0 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r0 = r0.zzbDk;	 Catch:{ all -> 0x0077 }
            r2 = r0.zzOM();	 Catch:{ all -> 0x0077 }
            r0 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r0.zzav(r2);	 Catch:{ all -> 0x0077 }
            monitor-exit(r1);	 Catch:{ all -> 0x0077 }
        L_0x002f:
            return;
        L_0x0030:
            r0 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r0 = r0.zzbDp;	 Catch:{ all -> 0x0077 }
            r0 = r0.zzlu;	 Catch:{ all -> 0x0077 }
            r6.zzlu = r0;	 Catch:{ all -> 0x0077 }
        L_0x003a:
            r0 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r2 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r2 = r2.zzuI;	 Catch:{ all -> 0x0077 }
            r2 = r2.currentTimeMillis();	 Catch:{ all -> 0x0077 }
            r4 = 0;
            r0.zza(r6, r2, r4);	 Catch:{ all -> 0x0077 }
            r0 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r2 = r0.zzbCX;	 Catch:{ all -> 0x0077 }
            r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0077 }
            r4 = 58;
            r0.<init>(r4);	 Catch:{ all -> 0x0077 }
            r4 = "setting refresh time to current time: ";
            r0 = r0.append(r4);	 Catch:{ all -> 0x0077 }
            r0 = r0.append(r2);	 Catch:{ all -> 0x0077 }
            r0 = r0.toString();	 Catch:{ all -> 0x0077 }
            com.google.android.gms.tagmanager.zzbo.m11v(r0);	 Catch:{ all -> 0x0077 }
            r0 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r0 = r0.zzOI();	 Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x0075;
        L_0x0070:
            r0 = r5.zzbDt;	 Catch:{ all -> 0x0077 }
            r0.zza(r6);	 Catch:{ all -> 0x0077 }
        L_0x0075:
            monitor-exit(r1);	 Catch:{ all -> 0x0077 }
            goto L_0x002f;
        L_0x0077:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0077 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzp.zzc.zzb(com.google.android.gms.internal.zzai$zzj):void");
        }
    }

    private class zzd implements com.google.android.gms.tagmanager.zzo.zza {
        final /* synthetic */ zzp zzbDt;

        private zzd(zzp com_google_android_gms_tagmanager_zzp) {
            this.zzbDt = com_google_android_gms_tagmanager_zzp;
        }

        public String zzOC() {
            return this.zzbDt.zzOC();
        }

        public void zzOE() {
            if (this.zzbDt.zzbDi.zzpv()) {
                this.zzbDt.zzav(0);
            }
        }

        public void zzgZ(String str) {
            this.zzbDt.zzgZ(str);
        }
    }

    interface zze extends Releasable {
        void zza(zzbn<zzj> com_google_android_gms_tagmanager_zzbn_com_google_android_gms_internal_zzai_zzj);

        void zzf(long j, String str);

        void zzhc(String str);
    }

    interface zzf extends Releasable {
        void zzOK();

        void zza(zzbn<com.google.android.gms.internal.zzbgg.zza> com_google_android_gms_tagmanager_zzbn_com_google_android_gms_internal_zzbgg_zza);

        void zzb(com.google.android.gms.internal.zzbgg.zza com_google_android_gms_internal_zzbgg_zza);

        com.google.android.gms.internal.zzbgi.zzc zzmO(int i);
    }

    zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzf com_google_android_gms_tagmanager_zzp_zzf, zze com_google_android_gms_tagmanager_zzp_zze, zzbgh com_google_android_gms_internal_zzbgh, com.google.android.gms.common.util.zze com_google_android_gms_common_util_zze, zzcl com_google_android_gms_tagmanager_zzcl, zzq com_google_android_gms_tagmanager_zzq) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.zzbDe = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzrx = looper;
        this.zzbCS = str;
        this.zzbDj = i;
        this.zzbDl = com_google_android_gms_tagmanager_zzp_zzf;
        this.zzbDr = com_google_android_gms_tagmanager_zzp_zze;
        this.zzbDm = com_google_android_gms_internal_zzbgh;
        this.zzbDh = new zzd();
        this.zzbDp = new zzj();
        this.zzuI = com_google_android_gms_common_util_zze;
        this.zzbDi = com_google_android_gms_tagmanager_zzcl;
        this.zzbDk = com_google_android_gms_tagmanager_zzq;
        if (zzOI()) {
            zzgZ(zzcj.zzPz().zzPB());
        }
    }

    public zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzt com_google_android_gms_tagmanager_zzt) {
        zzcv com_google_android_gms_tagmanager_zzcv = new zzcv(context, str);
        zzcu com_google_android_gms_tagmanager_zzcu = new zzcu(context, str, com_google_android_gms_tagmanager_zzt);
        Context context2 = context;
        TagManager tagManager2 = tagManager;
        Looper looper2 = looper;
        String str2 = str;
        int i2 = i;
        zzcv com_google_android_gms_tagmanager_zzcv2 = com_google_android_gms_tagmanager_zzcv;
        zzcu com_google_android_gms_tagmanager_zzcu2 = com_google_android_gms_tagmanager_zzcu;
        this(context2, tagManager2, looper2, str2, i2, com_google_android_gms_tagmanager_zzcv2, com_google_android_gms_tagmanager_zzcu2, new zzbgh(context), zzh.zzyv(), new zzbm(1, 5, 900000, 5000, "refreshing", zzh.zzyv()), new zzq(context, str));
        this.zzbDm.zzij(com_google_android_gms_tagmanager_zzt.zzOQ());
    }

    private boolean zzOI() {
        zzcj zzPz = zzcj.zzPz();
        return (zzPz.zzPA() == zza.CONTAINER || zzPz.zzPA() == zza.CONTAINER_DEBUG) && this.zzbCS.equals(zzPz.getContainerId());
    }

    private synchronized void zza(zzj com_google_android_gms_internal_zzai_zzj) {
        if (this.zzbDl != null) {
            com.google.android.gms.internal.zzbgg.zza com_google_android_gms_internal_zzbgg_zza = new com.google.android.gms.internal.zzbgg.zza();
            com_google_android_gms_internal_zzbgg_zza.zzbLh = this.zzbCX;
            com_google_android_gms_internal_zzbgg_zza.zzlu = new com.google.android.gms.internal.zzai.zzf();
            com_google_android_gms_internal_zzbgg_zza.zzbLi = com_google_android_gms_internal_zzai_zzj;
            this.zzbDl.zzb(com_google_android_gms_internal_zzbgg_zza);
        }
    }

    private synchronized void zza(zzj com_google_android_gms_internal_zzai_zzj, long j, boolean z) {
        if (z) {
            boolean z2 = this.zzbDo;
        }
        if (!(isReady() && this.zzbDn == null)) {
            this.zzbDp = com_google_android_gms_internal_zzai_zzj;
            this.zzbCX = j;
            long zzOL = this.zzbDk.zzOL();
            zzav(Math.max(0, Math.min(zzOL, (this.zzbCX + zzOL) - this.zzuI.currentTimeMillis())));
            Container container = new Container(this.mContext, this.zzbDe.getDataLayer(), this.zzbCS, j, com_google_android_gms_internal_zzai_zzj);
            if (this.zzbDn == null) {
                this.zzbDn = new zzo(this.zzbDe, this.zzrx, container, this.zzbDh);
            } else {
                this.zzbDn.zza(container);
            }
            if (!isReady() && this.zzbDs.zzb(container)) {
                zzb(this.zzbDn);
            }
        }
    }

    private void zzaL(final boolean z) {
        this.zzbDl.zza(new zzb());
        this.zzbDr.zza(new zzc());
        com.google.android.gms.internal.zzbgi.zzc zzmO = this.zzbDl.zzmO(this.zzbDj);
        if (zzmO != null) {
            this.zzbDn = new zzo(this.zzbDe, this.zzrx, new Container(this.mContext, this.zzbDe.getDataLayer(), this.zzbCS, 0, zzmO), this.zzbDh);
        }
        this.zzbDs = new zza(this) {
            final /* synthetic */ zzp zzbDt;
            private Long zzbDu;

            private long zzOJ() {
                if (this.zzbDu == null) {
                    this.zzbDu = Long.valueOf(this.zzbDt.zzbDk.zzOL());
                }
                return this.zzbDu.longValue();
            }

            public boolean zzb(Container container) {
                return z ? container.getLastRefreshTime() + zzOJ() >= this.zzbDt.zzuI.currentTimeMillis() : !container.isDefault();
            }
        };
        if (zzOI()) {
            this.zzbDr.zzf(0, "");
        } else {
            this.zzbDl.zzOK();
        }
    }

    private synchronized void zzav(long j) {
        if (this.zzbDr == null) {
            zzbo.zzbe("Refresh requested, but no network load scheduler.");
        } else {
            this.zzbDr.zzf(j, this.zzbDp.zzlv);
        }
    }

    synchronized String zzOC() {
        return this.zzbDq;
    }

    public void zzOF() {
        com.google.android.gms.internal.zzbgi.zzc zzmO = this.zzbDl.zzmO(this.zzbDj);
        if (zzmO != null) {
            zzb(new zzo(this.zzbDe, this.zzrx, new Container(this.mContext, this.zzbDe.getDataLayer(), this.zzbCS, 0, zzmO), new C07922(this)));
        } else {
            String str = "Default was requested, but no default container was found";
            zzbo.m10e(str);
            zzb(zzbK(new Status(10, str, null)));
        }
        this.zzbDr = null;
        this.zzbDl = null;
    }

    public void zzOG() {
        zzaL(false);
    }

    public void zzOH() {
        zzaL(true);
    }

    protected ContainerHolder zzbK(Status status) {
        if (this.zzbDn != null) {
            return this.zzbDn;
        }
        if (status == Status.zzayk) {
            zzbo.m10e("timer expired: setting result to failure");
        }
        return new zzo(status);
    }

    protected /* synthetic */ Result zzc(Status status) {
        return zzbK(status);
    }

    synchronized void zzgZ(String str) {
        this.zzbDq = str;
        if (this.zzbDr != null) {
            this.zzbDr.zzhc(str);
        }
    }
}
