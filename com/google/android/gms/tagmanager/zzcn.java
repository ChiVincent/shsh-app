package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcn extends zzdh {
    private static final String ID = zzag.REGEX.toString();
    private static final String zzbFn = zzah.IGNORE_CASE.toString();

    public zzcn() {
        super(ID);
    }

    protected boolean zza(String str, String str2, Map<String, zza> map) {
        try {
            return Pattern.compile(str2, zzdm.zzi((zza) map.get(zzbFn)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}
