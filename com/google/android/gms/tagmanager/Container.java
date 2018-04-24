package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzai.zzf;
import com.google.android.gms.internal.zzai.zzi;
import com.google.android.gms.internal.zzai.zzj;
import com.google.android.gms.internal.zzbgi;
import com.google.android.gms.internal.zzbgi.zzc;
import com.google.android.gms.internal.zzbgi.zzg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    private final Context mContext;
    private final String zzbCS;
    private final DataLayer zzbCT;
    private zzcx zzbCU;
    private Map<String, FunctionCallMacroCallback> zzbCV = new HashMap();
    private Map<String, FunctionCallTagCallback> zzbCW = new HashMap();
    private volatile long zzbCX;
    private volatile String zzbCY = "";

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    private class zza implements com.google.android.gms.tagmanager.zzu.zza {
        final /* synthetic */ Container zzbCZ;

        private zza(Container container) {
            this.zzbCZ = container;
        }

        public Object zze(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzgV = this.zzbCZ.zzgV(str);
            return zzgV == null ? null : zzgV.getValue(str, map);
        }
    }

    private class zzb implements com.google.android.gms.tagmanager.zzu.zza {
        final /* synthetic */ Container zzbCZ;

        private zzb(Container container) {
            this.zzbCZ = container;
        }

        public Object zze(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzgW = this.zzbCZ.zzgW(str);
            if (zzgW != null) {
                zzgW.execute(str, map);
            }
            return zzdm.zzQl();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzj com_google_android_gms_internal_zzai_zzj) {
        this.mContext = context;
        this.zzbCT = dataLayer;
        this.zzbCS = str;
        this.zzbCX = j;
        zza(com_google_android_gms_internal_zzai_zzj.zzlu);
        if (com_google_android_gms_internal_zzai_zzj.zzlt != null) {
            zza(com_google_android_gms_internal_zzai_zzj.zzlt);
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzc com_google_android_gms_internal_zzbgi_zzc) {
        this.mContext = context;
        this.zzbCT = dataLayer;
        this.zzbCS = str;
        this.zzbCX = j;
        zza(com_google_android_gms_internal_zzbgi_zzc);
    }

    private synchronized zzcx zzOB() {
        return this.zzbCU;
    }

    private void zza(zzf com_google_android_gms_internal_zzai_zzf) {
        if (com_google_android_gms_internal_zzai_zzf == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzbgi.zzb(com_google_android_gms_internal_zzai_zzf));
        } catch (zzg e) {
            String valueOf = String.valueOf(com_google_android_gms_internal_zzai_zzf);
            String valueOf2 = String.valueOf(e.toString());
            zzbo.m10e(new StringBuilder((String.valueOf(valueOf).length() + 46) + String.valueOf(valueOf2).length()).append("Not loading resource: ").append(valueOf).append(" because it is invalid: ").append(valueOf2).toString());
        }
    }

    private void zza(zzc com_google_android_gms_internal_zzbgi_zzc) {
        this.zzbCY = com_google_android_gms_internal_zzbgi_zzc.getVersion();
        zzc com_google_android_gms_internal_zzbgi_zzc2 = com_google_android_gms_internal_zzbgi_zzc;
        zza(new zzcx(this.mContext, com_google_android_gms_internal_zzbgi_zzc2, this.zzbCT, new zza(), new zzb(), zzgY(this.zzbCY)));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzbCT.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzbCS));
        }
    }

    private synchronized void zza(zzcx com_google_android_gms_tagmanager_zzcx) {
        this.zzbCU = com_google_android_gms_tagmanager_zzcx;
    }

    private void zza(zzi[] com_google_android_gms_internal_zzai_zziArr) {
        List arrayList = new ArrayList();
        for (Object add : com_google_android_gms_internal_zzai_zziArr) {
            arrayList.add(add);
        }
        zzOB().zzN(arrayList);
    }

    public boolean getBoolean(String str) {
        zzcx zzOB = zzOB();
        if (zzOB == null) {
            zzbo.m10e("getBoolean called for closed container.");
            return zzdm.zzQj().booleanValue();
        }
        try {
            return zzdm.zzi((com.google.android.gms.internal.zzaj.zza) zzOB.zzht(str).getObject()).booleanValue();
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbo.m10e(new StringBuilder(String.valueOf(valueOf).length() + 66).append("Calling getBoolean() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdm.zzQj().booleanValue();
        }
    }

    public String getContainerId() {
        return this.zzbCS;
    }

    public double getDouble(String str) {
        zzcx zzOB = zzOB();
        if (zzOB == null) {
            zzbo.m10e("getDouble called for closed container.");
            return zzdm.zzQi().doubleValue();
        }
        try {
            return zzdm.zzh((com.google.android.gms.internal.zzaj.zza) zzOB.zzht(str).getObject()).doubleValue();
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbo.m10e(new StringBuilder(String.valueOf(valueOf).length() + 65).append("Calling getDouble() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdm.zzQi().doubleValue();
        }
    }

    public long getLastRefreshTime() {
        return this.zzbCX;
    }

    public long getLong(String str) {
        zzcx zzOB = zzOB();
        if (zzOB == null) {
            zzbo.m10e("getLong called for closed container.");
            return zzdm.zzQh().longValue();
        }
        try {
            return zzdm.zzg((com.google.android.gms.internal.zzaj.zza) zzOB.zzht(str).getObject()).longValue();
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbo.m10e(new StringBuilder(String.valueOf(valueOf).length() + 63).append("Calling getLong() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdm.zzQh().longValue();
        }
    }

    public String getString(String str) {
        zzcx zzOB = zzOB();
        if (zzOB == null) {
            zzbo.m10e("getString called for closed container.");
            return zzdm.zzQl();
        }
        try {
            return zzdm.zze((com.google.android.gms.internal.zzaj.zza) zzOB.zzht(str).getObject());
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbo.m10e(new StringBuilder(String.valueOf(valueOf).length() + 65).append("Calling getString() threw an exception: ").append(valueOf).append(" Returning default value.").toString());
            return zzdm.zzQl();
        }
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.zzbCV) {
            this.zzbCV.put(str, functionCallMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.zzbCW) {
            this.zzbCW.put(str, functionCallTagCallback);
        }
    }

    void release() {
        this.zzbCU = null;
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.zzbCV) {
            this.zzbCV.remove(str);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.zzbCW) {
            this.zzbCW.remove(str);
        }
    }

    public String zzOA() {
        return this.zzbCY;
    }

    FunctionCallMacroCallback zzgV(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzbCV) {
            functionCallMacroCallback = (FunctionCallMacroCallback) this.zzbCV.get(str);
        }
        return functionCallMacroCallback;
    }

    public FunctionCallTagCallback zzgW(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzbCW) {
            functionCallTagCallback = (FunctionCallTagCallback) this.zzbCW.get(str);
        }
        return functionCallTagCallback;
    }

    public void zzgX(String str) {
        zzOB().zzgX(str);
    }

    zzaj zzgY(String str) {
        zzcj.zzPz().zzPA().equals(zza.CONTAINER_DEBUG);
        return new zzbw();
    }
}
