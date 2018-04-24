package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import com.google.android.gms.internal.zzai.zzf;
import com.google.android.gms.internal.zzbgg.zza;
import com.google.android.gms.internal.zzbgi;
import com.google.android.gms.internal.zzbgi.zzc;
import com.google.android.gms.internal.zzbgi.zzg;
import com.google.android.gms.internal.zzbus;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class zzcv implements zzf {
    private final Context mContext;
    private final String zzbCS;
    private zzbn<zza> zzbFr;
    private final ExecutorService zzbFy = Executors.newSingleThreadExecutor();

    class C04271 implements Runnable {
        final /* synthetic */ zzcv zzbFz;

        C04271(zzcv com_google_android_gms_tagmanager_zzcv) {
            this.zzbFz = com_google_android_gms_tagmanager_zzcv;
        }

        public void run() {
            this.zzbFz.zzPH();
        }
    }

    zzcv(Context context, String str) {
        this.mContext = context;
        this.zzbCS = str;
    }

    private zzc zzL(byte[] bArr) {
        try {
            zzc zzb = zzbgi.zzb(zzf.zzf(bArr));
            if (zzb == null) {
                return zzb;
            }
            zzbo.m11v("The container was successfully loaded from the resource (using binary file)");
            return zzb;
        } catch (zzbus e) {
            zzbo.m10e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzg e2) {
            zzbo.zzbe("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    private zzc zza(ByteArrayOutputStream byteArrayOutputStream) {
        zzc com_google_android_gms_internal_zzbgi_zzc = null;
        try {
            com_google_android_gms_internal_zzbgi_zzc = zzbh.zzho(byteArrayOutputStream.toString(HttpRequest.CHARSET_UTF8));
        } catch (UnsupportedEncodingException e) {
            zzbo.zzbc("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
        } catch (JSONException e2) {
            zzbo.zzbe("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
        }
        return com_google_android_gms_internal_zzbgi_zzc;
    }

    private void zzd(zza com_google_android_gms_internal_zzbgg_zza) throws IllegalArgumentException {
        if (com_google_android_gms_internal_zzbgg_zza.zzlu == null && com_google_android_gms_internal_zzbgg_zza.zzbLi == null) {
            throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
        }
    }

    public synchronized void release() {
        this.zzbFy.shutdown();
    }

    public void zzOK() {
        this.zzbFy.execute(new C04271(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void zzPH() {
        /*
        r3 = this;
        r0 = r3.zzbFr;
        if (r0 != 0) goto L_0x000c;
    L_0x0004:
        r0 = new java.lang.IllegalStateException;
        r1 = "Callback must be set before execute";
        r0.<init>(r1);
        throw r0;
    L_0x000c:
        r0 = "Attempting to load resource from disk";
        com.google.android.gms.tagmanager.zzbo.m11v(r0);
        r0 = com.google.android.gms.tagmanager.zzcj.zzPz();
        r0 = r0.zzPA();
        r1 = com.google.android.gms.tagmanager.zzcj.zza.CONTAINER;
        if (r0 == r1) goto L_0x0029;
    L_0x001d:
        r0 = com.google.android.gms.tagmanager.zzcj.zzPz();
        r0 = r0.zzPA();
        r1 = com.google.android.gms.tagmanager.zzcj.zza.CONTAINER_DEBUG;
        if (r0 != r1) goto L_0x0041;
    L_0x0029:
        r0 = r3.zzbCS;
        r1 = com.google.android.gms.tagmanager.zzcj.zzPz();
        r1 = r1.getContainerId();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0041;
    L_0x0039:
        r0 = r3.zzbFr;
        r1 = com.google.android.gms.tagmanager.zzbn.zza.NOT_AVAILABLE;
        r0.zza(r1);
    L_0x0040:
        return;
    L_0x0041:
        r1 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x006b }
        r0 = r3.zzPI();	 Catch:{ FileNotFoundException -> 0x006b }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x006b }
        r0 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0080, IllegalArgumentException -> 0x0098 }
        r0.<init>();	 Catch:{ IOException -> 0x0080, IllegalArgumentException -> 0x0098 }
        com.google.android.gms.internal.zzbgi.zzc(r1, r0);	 Catch:{ IOException -> 0x0080, IllegalArgumentException -> 0x0098 }
        r0 = r0.toByteArray();	 Catch:{ IOException -> 0x0080, IllegalArgumentException -> 0x0098 }
        r0 = com.google.android.gms.internal.zzbgg.zza.zzP(r0);	 Catch:{ IOException -> 0x0080, IllegalArgumentException -> 0x0098 }
        r3.zzd(r0);	 Catch:{ IOException -> 0x0080, IllegalArgumentException -> 0x0098 }
        r2 = r3.zzbFr;	 Catch:{ IOException -> 0x0080, IllegalArgumentException -> 0x0098 }
        r2.onSuccess(r0);	 Catch:{ IOException -> 0x0080, IllegalArgumentException -> 0x0098 }
        r1.close();	 Catch:{ IOException -> 0x0079 }
    L_0x0065:
        r0 = "The Disk resource was successfully read.";
        com.google.android.gms.tagmanager.zzbo.m11v(r0);
        goto L_0x0040;
    L_0x006b:
        r0 = move-exception;
        r0 = "Failed to find the resource in the disk";
        com.google.android.gms.tagmanager.zzbo.zzbc(r0);
        r0 = r3.zzbFr;
        r1 = com.google.android.gms.tagmanager.zzbn.zza.NOT_AVAILABLE;
        r0.zza(r1);
        goto L_0x0040;
    L_0x0079:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzbo.zzbe(r0);
        goto L_0x0065;
    L_0x0080:
        r0 = move-exception;
        r0 = r3.zzbFr;	 Catch:{ all -> 0x00b0 }
        r2 = com.google.android.gms.tagmanager.zzbn.zza.IO_ERROR;	 Catch:{ all -> 0x00b0 }
        r0.zza(r2);	 Catch:{ all -> 0x00b0 }
        r0 = "Failed to read the resource from disk";
        com.google.android.gms.tagmanager.zzbo.zzbe(r0);	 Catch:{ all -> 0x00b0 }
        r1.close();	 Catch:{ IOException -> 0x0091 }
        goto L_0x0065;
    L_0x0091:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzbo.zzbe(r0);
        goto L_0x0065;
    L_0x0098:
        r0 = move-exception;
        r0 = r3.zzbFr;	 Catch:{ all -> 0x00b0 }
        r2 = com.google.android.gms.tagmanager.zzbn.zza.IO_ERROR;	 Catch:{ all -> 0x00b0 }
        r0.zza(r2);	 Catch:{ all -> 0x00b0 }
        r0 = "Failed to read the resource from disk. The resource is inconsistent";
        com.google.android.gms.tagmanager.zzbo.zzbe(r0);	 Catch:{ all -> 0x00b0 }
        r1.close();	 Catch:{ IOException -> 0x00a9 }
        goto L_0x0065;
    L_0x00a9:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzbo.zzbe(r0);
        goto L_0x0065;
    L_0x00b0:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x00b5 }
    L_0x00b4:
        throw r0;
    L_0x00b5:
        r1 = move-exception;
        r1 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzbo.zzbe(r1);
        goto L_0x00b4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcv.zzPH():void");
    }

    File zzPI() {
        String valueOf = String.valueOf("resource_");
        String valueOf2 = String.valueOf(this.zzbCS);
        return new File(this.mContext.getDir("google_tagmanager", 0), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public void zza(zzbn<zza> com_google_android_gms_tagmanager_zzbn_com_google_android_gms_internal_zzbgg_zza) {
        this.zzbFr = com_google_android_gms_tagmanager_zzbn_com_google_android_gms_internal_zzbgg_zza;
    }

    public void zzb(final zza com_google_android_gms_internal_zzbgg_zza) {
        this.zzbFy.execute(new Runnable(this) {
            final /* synthetic */ zzcv zzbFz;

            public void run() {
                this.zzbFz.zzc(com_google_android_gms_internal_zzbgg_zza);
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean zzc(com.google.android.gms.internal.zzbgg.zza r5) {
        /*
        r4 = this;
        r0 = 0;
        r1 = r4.zzPI();
        r2 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0016 }
        r2.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0016 }
        r3 = com.google.android.gms.internal.zzbut.zzf(r5);	 Catch:{ IOException -> 0x0024 }
        r2.write(r3);	 Catch:{ IOException -> 0x0024 }
        r2.close();	 Catch:{ IOException -> 0x001d }
    L_0x0014:
        r0 = 1;
    L_0x0015:
        return r0;
    L_0x0016:
        r1 = move-exception;
        r1 = "Error opening resource file for writing";
        com.google.android.gms.tagmanager.zzbo.m10e(r1);
        goto L_0x0015;
    L_0x001d:
        r0 = move-exception;
        r0 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzbo.zzbe(r0);
        goto L_0x0014;
    L_0x0024:
        r3 = move-exception;
        r3 = "Error writing resource to disk. Removing resource from disk.";
        com.google.android.gms.tagmanager.zzbo.zzbe(r3);	 Catch:{ all -> 0x0038 }
        r1.delete();	 Catch:{ all -> 0x0038 }
        r2.close();	 Catch:{ IOException -> 0x0031 }
        goto L_0x0015;
    L_0x0031:
        r1 = move-exception;
        r1 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzbo.zzbe(r1);
        goto L_0x0015;
    L_0x0038:
        r0 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x003d }
    L_0x003c:
        throw r0;
    L_0x003d:
        r1 = move-exception;
        r1 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzbo.zzbe(r1);
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcv.zzc(com.google.android.gms.internal.zzbgg$zza):boolean");
    }

    public zzc zzmO(int i) {
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(i);
            String valueOf = String.valueOf(this.mContext.getResources().getResourceName(i));
            zzbo.m11v(new StringBuilder(String.valueOf(valueOf).length() + 66).append("Attempting to load a container from the resource ID ").append(i).append(" (").append(valueOf).append(")").toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzbgi.zzc(openRawResource, byteArrayOutputStream);
                zzc zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zzL(byteArrayOutputStream.toByteArray());
                }
                zzbo.m11v("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException e) {
                String valueOf2 = String.valueOf(this.mContext.getResources().getResourceName(i));
                zzbo.zzbe(new StringBuilder(String.valueOf(valueOf2).length() + 67).append("Error reading the default container with resource ID ").append(i).append(" (").append(valueOf2).append(")").toString());
                return null;
            }
        } catch (NotFoundException e2) {
            zzbo.zzbe("Failed to load the container. No default container resource found with the resource ID " + i);
            return null;
        }
    }
}
