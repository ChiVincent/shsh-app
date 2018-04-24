package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zzc;
import com.google.android.gms.internal.zzai.zzd;
import com.google.android.gms.internal.zzai.zzi;
import com.google.android.gms.internal.zzaj.zza;
import java.util.Map;

class zzal {
    private static void zza(DataLayer dataLayer, zzd com_google_android_gms_internal_zzai_zzd) {
        for (zza zze : com_google_android_gms_internal_zzai_zzd.zzkD) {
            dataLayer.zzhd(zzdm.zze(zze));
        }
    }

    public static void zza(DataLayer dataLayer, zzi com_google_android_gms_internal_zzai_zzi) {
        if (com_google_android_gms_internal_zzai_zzi.zzls == null) {
            zzbo.zzbe("supplemental missing experimentSupplemental");
            return;
        }
        zza(dataLayer, com_google_android_gms_internal_zzai_zzi.zzls);
        zzb(dataLayer, com_google_android_gms_internal_zzai_zzi.zzls);
        zzc(dataLayer, com_google_android_gms_internal_zzai_zzi.zzls);
    }

    private static void zzb(DataLayer dataLayer, zzd com_google_android_gms_internal_zzai_zzd) {
        for (zza zzc : com_google_android_gms_internal_zzai_zzd.zzkC) {
            Map zzc2 = zzc(zzc);
            if (zzc2 != null) {
                dataLayer.push(zzc2);
            }
        }
    }

    private static Map<String, Object> zzc(zza com_google_android_gms_internal_zzaj_zza) {
        Object zzj = zzdm.zzj(com_google_android_gms_internal_zzaj_zza);
        if (zzj instanceof Map) {
            return (Map) zzj;
        }
        String valueOf = String.valueOf(zzj);
        zzbo.zzbe(new StringBuilder(String.valueOf(valueOf).length() + 36).append("value: ").append(valueOf).append(" is not a map value, ignored.").toString());
        return null;
    }

    private static void zzc(DataLayer dataLayer, zzd com_google_android_gms_internal_zzai_zzd) {
        for (zzc com_google_android_gms_internal_zzai_zzc : com_google_android_gms_internal_zzai_zzd.zzkE) {
            if (com_google_android_gms_internal_zzai_zzc.zzaA == null) {
                zzbo.zzbe("GaExperimentRandom: No key");
            } else {
                Object obj = dataLayer.get(com_google_android_gms_internal_zzai_zzc.zzaA);
                Long valueOf = !(obj instanceof Number) ? null : Long.valueOf(((Number) obj).longValue());
                long j = com_google_android_gms_internal_zzai_zzc.zzky;
                long j2 = com_google_android_gms_internal_zzai_zzc.zzkz;
                if (!com_google_android_gms_internal_zzai_zzc.zzkA || valueOf == null || valueOf.longValue() < j || valueOf.longValue() > j2) {
                    if (j <= j2) {
                        obj = Long.valueOf(Math.round((Math.random() * ((double) (j2 - j))) + ((double) j)));
                    } else {
                        zzbo.zzbe("GaExperimentRandom: random range invalid");
                    }
                }
                dataLayer.zzhd(com_google_android_gms_internal_zzai_zzc.zzaA);
                Map zzo = dataLayer.zzo(com_google_android_gms_internal_zzai_zzc.zzaA, obj);
                if (com_google_android_gms_internal_zzai_zzc.zzkB > 0) {
                    if (zzo.containsKey("gtm")) {
                        Object obj2 = zzo.get("gtm");
                        if (obj2 instanceof Map) {
                            ((Map) obj2).put("lifetime", Long.valueOf(com_google_android_gms_internal_zzai_zzc.zzkB));
                        } else {
                            zzbo.zzbe("GaExperimentRandom: gtm not a map");
                        }
                    } else {
                        zzo.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(com_google_android_gms_internal_zzai_zzc.zzkB)));
                    }
                }
                dataLayer.push(zzo);
            }
        }
    }
}
