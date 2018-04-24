package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class zzar extends zzam {
    private static final String ID = zzag.HASH.toString();
    private static final String zzbEd = zzah.ARG0.toString();
    private static final String zzbEf = zzah.INPUT_FORMAT.toString();
    private static final String zzbEj = zzah.ALGORITHM.toString();

    public zzar() {
        super(ID, zzbEd);
    }

    private byte[] zzf(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }

    public boolean zzOw() {
        return true;
    }

    public zza zzY(Map<String, zza> map) {
        zza com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbEd);
        if (com_google_android_gms_internal_zzaj_zza == null || com_google_android_gms_internal_zzaj_zza == zzdm.zzQm()) {
            return zzdm.zzQm();
        }
        byte[] bytes;
        String zze = zzdm.zze(com_google_android_gms_internal_zzaj_zza);
        com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbEj);
        String zze2 = com_google_android_gms_internal_zzaj_zza == null ? CommonUtils.MD5_INSTANCE : zzdm.zze(com_google_android_gms_internal_zzaj_zza);
        com_google_android_gms_internal_zzaj_zza = (zza) map.get(zzbEf);
        Object zze3 = com_google_android_gms_internal_zzaj_zza == null ? "text" : zzdm.zze(com_google_android_gms_internal_zzaj_zza);
        if ("text".equals(zze3)) {
            bytes = zze.getBytes();
        } else if ("base16".equals(zze3)) {
            bytes = zzk.zzgU(zze);
        } else {
            zze2 = "Hash: unknown input format: ";
            String valueOf = String.valueOf(zze3);
            zzbo.m10e(valueOf.length() != 0 ? zze2.concat(valueOf) : new String(zze2));
            return zzdm.zzQm();
        }
        try {
            return zzdm.zzR(zzk.zzq(zzf(zze2, bytes)));
        } catch (NoSuchAlgorithmException e) {
            zze = "Hash: unknown algorithm: ";
            valueOf = String.valueOf(zze2);
            zzbo.m10e(valueOf.length() != 0 ? zze.concat(valueOf) : new String(zze));
            return zzdm.zzQm();
        }
    }
}
