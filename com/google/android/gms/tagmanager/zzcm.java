package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcm extends zzam {
    private static final String ID = zzag.REGEX_GROUP.toString();
    private static final String zzbFl = zzah.ARG0.toString();
    private static final String zzbFm = zzah.ARG1.toString();
    private static final String zzbFn = zzah.IGNORE_CASE.toString();
    private static final String zzbFo = zzah.GROUP.toString();

    public zzcm() {
        super(ID, zzbFl, zzbFm);
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        zza com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbFl);
        zza com_google_android_gms_internal_zzaj_zza2 = (zza) map.get(zzbFm);
        if (com_google_android_gms_internal_zzaj_zza == null || com_google_android_gms_internal_zzaj_zza == zzdm.zzQm() || com_google_android_gms_internal_zzaj_zza2 == null || com_google_android_gms_internal_zzaj_zza2 == zzdm.zzQm()) {
            return zzdm.zzQm();
        }
        int i = 64;
        if (zzdm.zzi((zza) map.get(zzbFn)).booleanValue()) {
            i = 66;
        }
        zza com_google_android_gms_internal_zzaj_zza3 = (zza) map.get(zzbFo);
        int intValue;
        if (com_google_android_gms_internal_zzaj_zza3 != null) {
            Long zzg = zzdm.zzg(com_google_android_gms_internal_zzaj_zza3);
            if (zzg == zzdm.zzQh()) {
                return zzdm.zzQm();
            }
            intValue = zzg.intValue();
            if (intValue < 0) {
                return zzdm.zzQm();
            }
        }
        intValue = 1;
        try {
            CharSequence zze = zzdm.zze(com_google_android_gms_internal_zzaj_zza);
            Object obj = null;
            Matcher matcher = Pattern.compile(zzdm.zze(com_google_android_gms_internal_zzaj_zza2), i).matcher(zze);
            if (matcher.find() && matcher.groupCount() >= intValue) {
                obj = matcher.group(intValue);
            }
            return obj == null ? zzdm.zzQm() : zzdm.zzR(obj);
        } catch (PatternSyntaxException e) {
            return zzdm.zzQm();
        }
    }
}
