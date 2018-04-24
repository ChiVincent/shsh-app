package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class zzbj extends zzam {
    private static final String ID = zzag.LANGUAGE.toString();

    public zzbj() {
        super(ID, new String[0]);
    }

    public boolean zzOw() {
        return false;
    }

    public /* bridge */ /* synthetic */ String zzPg() {
        return super.zzPg();
    }

    public /* bridge */ /* synthetic */ Set zzPh() {
        return super.zzPh();
    }

    public zza zzY(Map<String, zza> map) {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return zzdm.zzQm();
        }
        String language = locale.getLanguage();
        return language == null ? zzdm.zzQm() : zzdm.zzR(language.toLowerCase());
    }
}
