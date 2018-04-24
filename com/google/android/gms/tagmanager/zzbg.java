package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class zzbg extends zzam {
    private static final String ID = zzag.JOINER.toString();
    private static final String zzbEd = zzah.ARG0.toString();
    private static final String zzbEv = zzah.ITEM_SEPARATOR.toString();
    private static final String zzbEw = zzah.KEY_VALUE_SEPARATOR.toString();
    private static final String zzbEx = zzah.ESCAPE.toString();

    private enum zza {
        NONE,
        URL,
        BACKSLASH
    }

    public zzbg() {
        super(ID, zzbEd);
    }

    private String zza(String str, zza com_google_android_gms_tagmanager_zzbg_zza, Set<Character> set) {
        switch (com_google_android_gms_tagmanager_zzbg_zza) {
            case URL:
                try {
                    return zzdq.zzhG(str);
                } catch (Throwable e) {
                    zzbo.zzb("Joiner: unsupported encoding", e);
                    return str;
                }
            case BACKSLASH:
                String replace = str.replace("\\", "\\\\");
                String str2 = replace;
                for (Character ch : set) {
                    CharSequence ch2 = ch.toString();
                    String str3 = "\\";
                    replace = String.valueOf(ch2);
                    str2 = str2.replace(ch2, replace.length() != 0 ? str3.concat(replace) : new String(str3));
                }
                return str2;
            default:
                return str;
        }
    }

    private void zza(StringBuilder stringBuilder, String str, zza com_google_android_gms_tagmanager_zzbg_zza, Set<Character> set) {
        stringBuilder.append(zza(str, com_google_android_gms_tagmanager_zzbg_zza, set));
    }

    private void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    public boolean zzOw() {
        return true;
    }

    public com.google.android.gms.internal.zzaj.zza zzY(Map<String, com.google.android.gms.internal.zzaj.zza> map) {
        com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza = (com.google.android.gms.internal.zzaj.zza) map.get(zzbEd);
        if (com_google_android_gms_internal_zzaj_zza == null) {
            return zzdm.zzQm();
        }
        zza com_google_android_gms_tagmanager_zzbg_zza;
        com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza2 = (com.google.android.gms.internal.zzaj.zza) map.get(zzbEv);
        String zze = com_google_android_gms_internal_zzaj_zza2 != null ? zzdm.zze(com_google_android_gms_internal_zzaj_zza2) : "";
        com_google_android_gms_internal_zzaj_zza2 = (com.google.android.gms.internal.zzaj.zza) map.get(zzbEw);
        String zze2 = com_google_android_gms_internal_zzaj_zza2 != null ? zzdm.zze(com_google_android_gms_internal_zzaj_zza2) : "=";
        zza com_google_android_gms_tagmanager_zzbg_zza2 = zza.NONE;
        com_google_android_gms_internal_zzaj_zza2 = (com.google.android.gms.internal.zzaj.zza) map.get(zzbEx);
        Set set;
        if (com_google_android_gms_internal_zzaj_zza2 != null) {
            String zze3 = zzdm.zze(com_google_android_gms_internal_zzaj_zza2);
            if ("url".equals(zze3)) {
                com_google_android_gms_tagmanager_zzbg_zza = zza.URL;
                set = null;
            } else if ("backslash".equals(zze3)) {
                com_google_android_gms_tagmanager_zzbg_zza = zza.BACKSLASH;
                set = new HashSet();
                zza(set, zze);
                zza(set, zze2);
                set.remove(Character.valueOf('\\'));
            } else {
                zze = "Joiner: unsupported escape type: ";
                String valueOf = String.valueOf(zze3);
                zzbo.m10e(valueOf.length() != 0 ? zze.concat(valueOf) : new String(zze));
                return zzdm.zzQm();
            }
        }
        set = null;
        com_google_android_gms_tagmanager_zzbg_zza = com_google_android_gms_tagmanager_zzbg_zza2;
        StringBuilder stringBuilder = new StringBuilder();
        switch (com_google_android_gms_internal_zzaj_zza.type) {
            case 2:
                Object obj = 1;
                com.google.android.gms.internal.zzaj.zza[] com_google_android_gms_internal_zzaj_zzaArr = com_google_android_gms_internal_zzaj_zza.zzlx;
                int length = com_google_android_gms_internal_zzaj_zzaArr.length;
                int i = 0;
                while (i < length) {
                    com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza3 = com_google_android_gms_internal_zzaj_zzaArr[i];
                    if (obj == null) {
                        stringBuilder.append(zze);
                    }
                    zza(stringBuilder, zzdm.zze(com_google_android_gms_internal_zzaj_zza3), com_google_android_gms_tagmanager_zzbg_zza, set);
                    i++;
                    obj = null;
                }
                break;
            case 3:
                for (int i2 = 0; i2 < com_google_android_gms_internal_zzaj_zza.zzly.length; i2++) {
                    if (i2 > 0) {
                        stringBuilder.append(zze);
                    }
                    String zze4 = zzdm.zze(com_google_android_gms_internal_zzaj_zza.zzly[i2]);
                    String zze5 = zzdm.zze(com_google_android_gms_internal_zzaj_zza.zzlz[i2]);
                    zza(stringBuilder, zze4, com_google_android_gms_tagmanager_zzbg_zza, set);
                    stringBuilder.append(zze2);
                    zza(stringBuilder, zze5, com_google_android_gms_tagmanager_zzbg_zza, set);
                }
                break;
            default:
                zza(stringBuilder, zzdm.zze(com_google_android_gms_internal_zzaj_zza), com_google_android_gms_tagmanager_zzbg_zza, set);
                break;
        }
        return zzdm.zzR(stringBuilder.toString());
    }
}
