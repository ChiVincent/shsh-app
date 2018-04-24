package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzg extends zzam {
    private static final String ID = zzag.APP_NAME.toString();
    private final Context mContext;

    public zzg(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return zzdm.zzR(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        } catch (Throwable e) {
            zzbo.zzb("App name is not found.", e);
            return zzdm.zzQm();
        }
    }
}
