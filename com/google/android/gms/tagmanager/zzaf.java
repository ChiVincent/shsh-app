package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzaf extends zzam {
    private static final String ID = zzag.ENCODE.toString();
    private static final String zzbEd = zzah.ARG0.toString();
    private static final String zzbEe = zzah.NO_PADDING.toString();
    private static final String zzbEf = zzah.INPUT_FORMAT.toString();
    private static final String zzbEg = zzah.OUTPUT_FORMAT.toString();

    public zzaf() {
        super(ID, zzbEd);
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        zza com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbEd);
        if (com_google_android_gms_internal_zzaj_zza == null || com_google_android_gms_internal_zzaj_zza == zzdm.zzQm()) {
            return zzdm.zzQm();
        }
        String zze = zzdm.zze(com_google_android_gms_internal_zzaj_zza);
        com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbEf);
        if (com_google_android_gms_internal_zzaj_zza == null) {
            Object obj = "text";
        } else {
            String zze2 = zzdm.zze(com_google_android_gms_internal_zzaj_zza);
        }
        com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbEg);
        if (com_google_android_gms_internal_zzaj_zza == null) {
            Object obj2 = "base16";
        } else {
            String zze3 = zzdm.zze(com_google_android_gms_internal_zzaj_zza);
        }
        int i = 2;
        com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbEe);
        if (com_google_android_gms_internal_zzaj_zza != null && zzdm.zzi(com_google_android_gms_internal_zzaj_zza).booleanValue()) {
            i = 3;
        }
        try {
            byte[] bytes;
            String valueOf;
            Object zzq;
            if ("text".equals(obj)) {
                bytes = zze.getBytes();
            } else if ("base16".equals(obj)) {
                bytes = zzk.zzgU(zze);
            } else if ("base64".equals(obj)) {
                bytes = Base64.decode(zze, i);
            } else if ("base64url".equals(obj)) {
                bytes = Base64.decode(zze, i | 8);
            } else {
                zze3 = "Encode: unknown input format: ";
                valueOf = String.valueOf(obj);
                zzbo.m10e(valueOf.length() != 0 ? zze3.concat(valueOf) : new String(zze3));
                return zzdm.zzQm();
            }
            if ("base16".equals(obj2)) {
                zzq = zzk.zzq(bytes);
            } else if ("base64".equals(obj2)) {
                zzq = Base64.encodeToString(bytes, i);
            } else if ("base64url".equals(obj2)) {
                zzq = Base64.encodeToString(bytes, i | 8);
            } else {
                zze2 = "Encode: unknown output format: ";
                valueOf = String.valueOf(obj2);
                zzbo.m10e(valueOf.length() != 0 ? zze2.concat(valueOf) : new String(zze2));
                return zzdm.zzQm();
            }
            return zzdm.zzR(zzq);
        } catch (IllegalArgumentException e) {
            zzbo.m10e("Encode: invalid input:");
            return zzdm.zzQm();
        }
    }
}
