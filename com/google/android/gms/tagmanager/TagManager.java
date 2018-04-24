package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager zzbGy;
    private final Context mContext;
    private final DataLayer zzbCT;
    private final zzt zzbFs;
    private final zza zzbGv;
    private final zzdb zzbGw;
    private final ConcurrentMap<String, zzo> zzbGx;

    class C04213 implements ComponentCallbacks2 {
        final /* synthetic */ TagManager zzbGz;

        C04213(TagManager tagManager) {
            this.zzbGz = tagManager;
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
        }

        public void onTrimMemory(int i) {
            if (i == 20) {
                this.zzbGz.dispatch();
            }
        }
    }

    public interface zza {
        zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzt com_google_android_gms_tagmanager_zzt);
    }

    class C07791 implements zzb {
        final /* synthetic */ TagManager zzbGz;

        C07791(TagManager tagManager) {
            this.zzbGz = tagManager;
        }

        public void zzZ(Map<String, Object> map) {
            Object obj = map.get("event");
            if (obj != null) {
                this.zzbGz.zzhv(obj.toString());
            }
        }
    }

    class C07802 implements zza {
        C07802() {
        }

        public zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzt com_google_android_gms_tagmanager_zzt) {
            return new zzp(context, tagManager, looper, str, i, com_google_android_gms_tagmanager_zzt);
        }
    }

    TagManager(Context context, zza com_google_android_gms_tagmanager_TagManager_zza, DataLayer dataLayer, zzdb com_google_android_gms_tagmanager_zzdb) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.zzbGw = com_google_android_gms_tagmanager_zzdb;
        this.zzbGv = com_google_android_gms_tagmanager_TagManager_zza;
        this.zzbGx = new ConcurrentHashMap();
        this.zzbCT = dataLayer;
        this.zzbCT.zza(new C07791(this));
        this.zzbCT.zza(new zzd(this.mContext));
        this.zzbFs = new zzt();
        zzPZ();
        zzQa();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzbGy == null) {
                if (context == null) {
                    zzbo.m10e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                zzbGy = new TagManager(context, new C07802(), new DataLayer(new zzx(context)), zzdc.zzPT());
            }
            tagManager = zzbGy;
        }
        return tagManager;
    }

    @TargetApi(14)
    private void zzPZ() {
        if (VERSION.SDK_INT >= 14) {
            this.mContext.registerComponentCallbacks(new C04213(this));
        }
    }

    private void zzQa() {
        zza.zzbA(this.mContext);
    }

    private void zzhv(String str) {
        for (zzo zzgX : this.zzbGx.values()) {
            zzgX.zzgX(str);
        }
    }

    public void dispatch() {
        this.zzbGw.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzbCT;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i) {
        PendingResult zza = this.zzbGv.zza(this.mContext, this, null, str, i, this.zzbFs);
        zza.zzOF();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.zzbGv.zza(this.mContext, this, handler.getLooper(), str, i, this.zzbFs);
        zza.zzOF();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i) {
        PendingResult zza = this.zzbGv.zza(this.mContext, this, null, str, i, this.zzbFs);
        zza.zzOH();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.zzbGv.zza(this.mContext, this, handler.getLooper(), str, i, this.zzbFs);
        zza.zzOH();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i) {
        PendingResult zza = this.zzbGv.zza(this.mContext, this, null, str, i, this.zzbFs);
        zza.zzOG();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i, Handler handler) {
        PendingResult zza = this.zzbGv.zza(this.mContext, this, handler.getLooper(), str, i, this.zzbFs);
        zza.zzOG();
        return zza;
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzbo.setLogLevel(z ? 2 : 5);
    }

    public int zza(zzo com_google_android_gms_tagmanager_zzo) {
        this.zzbGx.put(com_google_android_gms_tagmanager_zzo.getContainerId(), com_google_android_gms_tagmanager_zzo);
        return this.zzbGx.size();
    }

    public boolean zzb(zzo com_google_android_gms_tagmanager_zzo) {
        return this.zzbGx.remove(com_google_android_gms_tagmanager_zzo.getContainerId()) != null;
    }

    synchronized boolean zzv(Uri uri) {
        boolean z;
        zzcj zzPz = zzcj.zzPz();
        if (zzPz.zzv(uri)) {
            String containerId = zzPz.getContainerId();
            switch (zzPz.zzPA()) {
                case NONE:
                    zzo com_google_android_gms_tagmanager_zzo = (zzo) this.zzbGx.get(containerId);
                    if (com_google_android_gms_tagmanager_zzo != null) {
                        com_google_android_gms_tagmanager_zzo.zzgZ(null);
                        com_google_android_gms_tagmanager_zzo.refresh();
                        break;
                    }
                    break;
                case CONTAINER:
                case CONTAINER_DEBUG:
                    for (String str : this.zzbGx.keySet()) {
                        zzo com_google_android_gms_tagmanager_zzo2 = (zzo) this.zzbGx.get(str);
                        if (str.equals(containerId)) {
                            com_google_android_gms_tagmanager_zzo2.zzgZ(zzPz.zzPB());
                            com_google_android_gms_tagmanager_zzo2.refresh();
                        } else if (com_google_android_gms_tagmanager_zzo2.zzOC() != null) {
                            com_google_android_gms_tagmanager_zzo2.zzgZ(null);
                            com_google_android_gms_tagmanager_zzo2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
