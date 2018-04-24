package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzaj.zza;
import com.google.android.gms.internal.zzbgi;
import com.google.android.gms.internal.zzbgi.zzc;
import com.google.android.gms.internal.zzbgi.zzd;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class zzbh {
    private static zza zzK(Object obj) throws JSONException {
        return zzdm.zzR(zzL(obj));
    }

    static Object zzL(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            Map hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, zzL(jSONObject.get(str)));
            }
            return hashMap;
        }
    }

    public static zzc zzho(String str) throws JSONException {
        zza zzK = zzK(new JSONObject(str));
        zzd zzRW = zzc.zzRW();
        for (int i = 0; i < zzK.zzly.length; i++) {
            zzRW.zzc(zzbgi.zza.zzRU().zzb(zzah.INSTANCE_NAME.toString(), zzK.zzly[i]).zzb(zzah.FUNCTION.toString(), zzdm.zzhz(zzn.zzOy())).zzb(zzn.zzOz(), zzK.zzlz[i]).zzRV());
        }
        return zzRW.zzRY();
    }
}
