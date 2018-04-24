package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class zzav extends Thread implements zzau {
    private static zzav zzbEo;
    private volatile boolean mClosed = false;
    private final Context mContext;
    private volatile boolean zzMS = false;
    private final LinkedBlockingQueue<Runnable> zzbEn = new LinkedBlockingQueue();
    private volatile zzaw zzbEp;

    private zzav(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    static zzav zzbI(Context context) {
        if (zzbEo == null) {
            zzbEo = new zzav(context);
        }
        return zzbEo;
    }

    private String zzg(Throwable th) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public void run() {
        while (true) {
            boolean z = this.mClosed;
            try {
                Runnable runnable = (Runnable) this.zzbEn.take();
                if (!this.zzMS) {
                    runnable.run();
                }
            } catch (InterruptedException e) {
                zzbo.zzbd(e.toString());
            } catch (Throwable th) {
                String str = "Error on Google TagManager Thread: ";
                String valueOf = String.valueOf(zzg(th));
                zzbo.m10e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                zzbo.m10e("Google TagManager is shutting down.");
                this.zzMS = true;
            }
        }
    }

    public void zzhm(String str) {
        zzn(str, System.currentTimeMillis());
    }

    void zzn(String str, long j) {
        final zzav com_google_android_gms_tagmanager_zzav = this;
        final long j2 = j;
        final String str2 = str;
        zzp(new Runnable(this) {
            final /* synthetic */ zzav zzbEs;

            public void run() {
                if (this.zzbEs.zzbEp == null) {
                    zzdc zzPT = zzdc.zzPT();
                    zzPT.zza(this.zzbEs.mContext, com_google_android_gms_tagmanager_zzav);
                    this.zzbEs.zzbEp = zzPT.zzPW();
                }
                this.zzbEs.zzbEp.zzg(j2, str2);
            }
        });
    }

    public void zzp(Runnable runnable) {
        this.zzbEn.add(runnable);
    }
}
