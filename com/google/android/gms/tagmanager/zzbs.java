package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzbs extends zzam {
    private static final String ID = zzag.MOBILE_ADWORDS_UNIQUE_ID.toString();
    private final Context mContext;

    public zzbs(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        String zzbC = zzbC(this.mContext);
        return zzbC == null ? zzdm.zzQm() : zzdm.zzR(zzbC);
    }

    protected String zzbC(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }
}
