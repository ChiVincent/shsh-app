package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.zzai.zzj;
import com.google.android.gms.internal.zzbgi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzbgn;
import com.google.android.gms.tagmanager.zzbn.zza;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

class zzct implements Runnable {
    private final Context mContext;
    private final String zzbCS;
    private volatile String zzbDq;
    private final zzbgm zzbFp;
    private final String zzbFq;
    private zzbn<zzj> zzbFr;
    private volatile zzt zzbFs;
    private volatile String zzbFt;

    zzct(Context context, String str, zzbgm com_google_android_gms_internal_zzbgm, zzt com_google_android_gms_tagmanager_zzt) {
        this.mContext = context;
        this.zzbFp = com_google_android_gms_internal_zzbgm;
        this.zzbCS = str;
        this.zzbFs = com_google_android_gms_tagmanager_zzt;
        String valueOf = String.valueOf("/r?id=");
        String valueOf2 = String.valueOf(str);
        this.zzbFq = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        this.zzbDq = this.zzbFq;
        this.zzbFt = null;
    }

    public zzct(Context context, String str, zzt com_google_android_gms_tagmanager_zzt) {
        this(context, str, new zzbgm(), com_google_android_gms_tagmanager_zzt);
    }

    private boolean zzPC() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbo.m11v("...no network connectivity");
        return false;
    }

    private void zzPD() {
        String valueOf;
        if (zzPC()) {
            String str;
            String str2;
            zzbo.m11v("Start loading resource from network ...");
            String zzPE = zzPE();
            zzbgl zzSd = this.zzbFp.zzSd();
            InputStream inputStream = null;
            try {
                inputStream = zzSd.zzia(zzPE);
            } catch (FileNotFoundException e) {
                str = this.zzbCS;
                zzbo.zzbe(new StringBuilder((String.valueOf(zzPE).length() + 79) + String.valueOf(str).length()).append("No data is retrieved from the given url: ").append(zzPE).append(". Make sure container_id: ").append(str).append(" is correct.").toString());
                this.zzbFr.zza(zza.SERVER_ERROR);
                zzSd.close();
                return;
            } catch (zzbgn e2) {
                str2 = "Error when loading resource for url: ";
                valueOf = String.valueOf(zzPE);
                zzbo.zzbe(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                this.zzbFr.zza(zza.SERVER_UNAVAILABLE_ERROR);
            } catch (Throwable e3) {
                valueOf = String.valueOf(e3.getMessage());
                zzbo.zzc(new StringBuilder((String.valueOf(zzPE).length() + 40) + String.valueOf(valueOf).length()).append("Error when loading resources from url: ").append(zzPE).append(" ").append(valueOf).toString(), e3);
                this.zzbFr.zza(zza.IO_ERROR);
                zzSd.close();
                return;
            } catch (Throwable th) {
                zzSd.close();
            }
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzbgi.zzc(inputStream, byteArrayOutputStream);
                zzj zzg = zzj.zzg(byteArrayOutputStream.toByteArray());
                str = String.valueOf(zzg);
                zzbo.m11v(new StringBuilder(String.valueOf(str).length() + 43).append("Successfully loaded supplemented resource: ").append(str).toString());
                if (zzg.zzlu == null && zzg.zzlt.length == 0) {
                    str2 = "No change for container: ";
                    str = String.valueOf(this.zzbCS);
                    zzbo.m11v(str.length() != 0 ? str2.concat(str) : new String(str2));
                }
                this.zzbFr.onSuccess(zzg);
                zzSd.close();
                zzbo.m11v("Load resource from network finished.");
                return;
            } catch (Throwable e32) {
                valueOf = String.valueOf(e32.getMessage());
                zzbo.zzc(new StringBuilder((String.valueOf(zzPE).length() + 51) + String.valueOf(valueOf).length()).append("Error when parsing downloaded resources from url: ").append(zzPE).append(" ").append(valueOf).toString(), e32);
                this.zzbFr.zza(zza.SERVER_ERROR);
                zzSd.close();
                return;
            }
        }
        this.zzbFr.zza(zza.NOT_AVAILABLE);
    }

    public void run() {
        if (this.zzbFr == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        zzPD();
    }

    String zzPE() {
        String valueOf = String.valueOf(this.zzbFs.zzOQ());
        String str = this.zzbDq;
        String valueOf2 = String.valueOf("&v=a65833898");
        valueOf = new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(valueOf2).toString();
        if (!(this.zzbFt == null || this.zzbFt.trim().equals(""))) {
            valueOf = String.valueOf(valueOf);
            str = String.valueOf("&pv=");
            valueOf2 = this.zzbFt;
            valueOf = new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(valueOf2).toString();
        }
        if (!zzcj.zzPz().zzPA().equals(zza.CONTAINER_DEBUG)) {
            return valueOf;
        }
        str = String.valueOf(valueOf);
        valueOf = String.valueOf("&gtm_debug=x");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    void zza(zzbn<zzj> com_google_android_gms_tagmanager_zzbn_com_google_android_gms_internal_zzai_zzj) {
        this.zzbFr = com_google_android_gms_tagmanager_zzbn_com_google_android_gms_internal_zzai_zzj;
    }

    void zzhc(String str) {
        if (str == null) {
            this.zzbDq = this.zzbFq;
            return;
        }
        String str2 = "Setting CTFE URL path: ";
        String valueOf = String.valueOf(str);
        zzbo.zzbc(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzbDq = str;
    }

    void zzhr(String str) {
        String str2 = "Setting previous container version: ";
        String valueOf = String.valueOf(str);
        zzbo.zzbc(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        this.zzbFt = str;
    }
}
