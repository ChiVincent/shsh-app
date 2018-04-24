package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

public class zza {
    private static Object zzbCG = new Object();
    private static zza zzbCH;
    private volatile boolean mClosed;
    private final Context mContext;
    private final Thread zzWx;
    private volatile Info zzacA;
    private volatile long zzbCA;
    private volatile long zzbCB;
    private volatile long zzbCC;
    private volatile long zzbCD;
    private final Object zzbCE;
    private zza zzbCF;
    private final zze zzuI;

    class C04232 implements Runnable {
        final /* synthetic */ zza zzbCI;

        C04232(zza com_google_android_gms_tagmanager_zza) {
            this.zzbCI = com_google_android_gms_tagmanager_zza;
        }

        public void run() {
            this.zzbCI.zzOu();
        }
    }

    public interface zza {
        Info zzOv();
    }

    class C07811 implements zza {
        final /* synthetic */ zza zzbCI;

        C07811(zza com_google_android_gms_tagmanager_zza) {
            this.zzbCI = com_google_android_gms_tagmanager_zza;
        }

        public Info zzOv() {
            Info info = null;
            try {
                info = AdvertisingIdClient.getAdvertisingIdInfo(this.zzbCI.mContext);
            } catch (Throwable e) {
                zzbo.zzc("IllegalStateException getting Advertising Id Info", e);
            } catch (Throwable e2) {
                zzbo.zzc("GooglePlayServicesRepairableException getting Advertising Id Info", e2);
            } catch (Throwable e22) {
                zzbo.zzc("IOException getting Ad Id Info", e22);
            } catch (Throwable e222) {
                zzbo.zzc("GooglePlayServicesNotAvailableException getting Advertising Id Info", e222);
            } catch (Throwable e2222) {
                zzbo.zzc("Unknown exception. Could not get the Advertising Id Info.", e2222);
            }
            return info;
        }
    }

    private zza(Context context) {
        this(context, null, zzh.zzyv());
    }

    public zza(Context context, zza com_google_android_gms_tagmanager_zza_zza, zze com_google_android_gms_common_util_zze) {
        this.zzbCA = 900000;
        this.zzbCB = 30000;
        this.mClosed = false;
        this.zzbCE = new Object();
        this.zzbCF = new C07811(this);
        this.zzuI = com_google_android_gms_common_util_zze;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        if (com_google_android_gms_tagmanager_zza_zza != null) {
            this.zzbCF = com_google_android_gms_tagmanager_zza_zza;
        }
        this.zzbCC = this.zzuI.currentTimeMillis();
        this.zzWx = new Thread(new C04232(this));
    }

    private void zzOr() {
        synchronized (this) {
            try {
                zzOs();
                wait(500);
            } catch (InterruptedException e) {
            }
        }
    }

    private void zzOs() {
        if (this.zzuI.currentTimeMillis() - this.zzbCC > this.zzbCB) {
            synchronized (this.zzbCE) {
                this.zzbCE.notify();
            }
            this.zzbCC = this.zzuI.currentTimeMillis();
        }
    }

    private void zzOt() {
        if (this.zzuI.currentTimeMillis() - this.zzbCD > 3600000) {
            this.zzacA = null;
        }
    }

    private void zzOu() {
        Process.setThreadPriority(10);
        while (true) {
            boolean z = this.mClosed;
            Info zzOv = this.zzbCF.zzOv();
            if (zzOv != null) {
                this.zzacA = zzOv;
                this.zzbCD = this.zzuI.currentTimeMillis();
                zzbo.zzbd("Obtained fresh AdvertisingId info from GmsCore.");
            }
            synchronized (this) {
                notifyAll();
            }
            try {
                synchronized (this.zzbCE) {
                    this.zzbCE.wait(this.zzbCA);
                }
            } catch (InterruptedException e) {
                zzbo.zzbd("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    public static zza zzbA(Context context) {
        if (zzbCH == null) {
            synchronized (zzbCG) {
                if (zzbCH == null) {
                    zzbCH = new zza(context);
                    zzbCH.start();
                }
            }
        }
        return zzbCH;
    }

    public boolean isLimitAdTrackingEnabled() {
        if (this.zzacA == null) {
            zzOr();
        } else {
            zzOs();
        }
        zzOt();
        return this.zzacA == null ? true : this.zzacA.isLimitAdTrackingEnabled();
    }

    public void start() {
        this.zzWx.start();
    }

    public String zzOq() {
        if (this.zzacA == null) {
            zzOr();
        } else {
            zzOs();
        }
        zzOt();
        return this.zzacA == null ? null : this.zzacA.getId();
    }
}
