package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class zzu extends zzam {
    private static final String ID = zzag.FUNCTION_CALL.toString();
    private static final String zzbCM = zzah.ADDITIONAL_PARAMS.toString();
    private static final String zzbDx = zzah.FUNCTION_CALL_NAME.toString();
    private final zza zzbDy;

    public interface zza {
        Object zze(String str, Map<String, Object> map);
    }

    public zzu(zza com_google_android_gms_tagmanager_zzu_zza) {
        super(ID, zzbDx);
        this.zzbDy = com_google_android_gms_tagmanager_zzu_zza;
    }

    public boolean zzOw() {
        return false;
    }

    public com.google.android.gms.internal.zzaj.zza zzY(Map<String, com.google.android.gms.internal.zzaj.zza> map) {
        String zze = zzdm.zze((com.google.android.gms.internal.zzaj.zza) map.get(zzbDx));
        Map hashMap = new HashMap();
        com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza = (com.google.android.gms.internal.zzaj.zza) map.get(zzbCM);
        if (com_google_android_gms_internal_zzaj_zza != null) {
            Object zzj = zzdm.zzj(com_google_android_gms_internal_zzaj_zza);
            if (zzj instanceof Map) {
                for (Entry entry : ((Map) zzj).entrySet()) {
                    hashMap.put(entry.getKey().toString(), entry.getValue());
                }
            } else {
                zzbo.zzbe("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzdm.zzQm();
            }
        }
        try {
            return zzdm.zzR(this.zzbDy.zze(zze, hashMap));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbo.zzbe(new StringBuilder((String.valueOf(zze).length() + 34) + String.valueOf(valueOf).length()).append("Custom macro/tag ").append(zze).append(" threw exception ").append(valueOf).toString());
            return zzdm.zzQm();
        }
    }
}
