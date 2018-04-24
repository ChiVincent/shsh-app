package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzj extends zzdk {
    private static final String ID = zzag.ARBITRARY_PIXEL.toString();
    private static final String URL = zzah.URL.toString();
    private static final String zzbCM = zzah.ADDITIONAL_PARAMS.toString();
    private static final String zzbCN = zzah.UNREPEATABLE.toString();
    static final String zzbCO;
    private static final Set<String> zzbCP = new HashSet();
    private final Context mContext;
    private final zza zzbCQ;

    public interface zza {
        zzat zzOx();
    }

    class C07901 implements zza {
        final /* synthetic */ Context zztd;

        C07901(Context context) {
            this.zztd = context;
        }

        public zzat zzOx() {
            return zzaa.zzbB(this.zztd);
        }
    }

    static {
        String str = ID;
        zzbCO = new StringBuilder(String.valueOf(str).length() + 17).append("gtm_").append(str).append("_unrepeatable").toString();
    }

    public zzj(Context context) {
        this(context, new C07901(context));
    }

    zzj(Context context, zza com_google_android_gms_tagmanager_zzj_zza) {
        super(ID, URL);
        this.zzbCQ = com_google_android_gms_tagmanager_zzj_zza;
        this.mContext = context;
    }

    private synchronized boolean zzgR(String str) {
        boolean z = true;
        synchronized (this) {
            if (!zzgT(str)) {
                if (zzgS(str)) {
                    zzbCP.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    public void zzaa(Map<String, com.google.android.gms.internal.zzaj.zza> map) {
        String zze = map.get(zzbCN) != null ? zzdm.zze((com.google.android.gms.internal.zzaj.zza) map.get(zzbCN)) : null;
        if (zze == null || !zzgR(zze)) {
            String valueOf;
            Builder buildUpon = Uri.parse(zzdm.zze((com.google.android.gms.internal.zzaj.zza) map.get(URL))).buildUpon();
            com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza = (com.google.android.gms.internal.zzaj.zza) map.get(zzbCM);
            if (com_google_android_gms_internal_zzaj_zza != null) {
                Object zzj = zzdm.zzj(com_google_android_gms_internal_zzaj_zza);
                if (zzj instanceof List) {
                    for (Object zzj2 : (List) zzj2) {
                        if (zzj2 instanceof Map) {
                            for (Entry entry : ((Map) zzj2).entrySet()) {
                                buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                            }
                        } else {
                            zze = "ArbitraryPixel: additional params contains non-map: not sending partial hit: ";
                            valueOf = String.valueOf(buildUpon.build().toString());
                            zzbo.m10e(valueOf.length() != 0 ? zze.concat(valueOf) : new String(zze));
                            return;
                        }
                    }
                }
                zze = "ArbitraryPixel: additional params not a list: not sending partial hit: ";
                valueOf = String.valueOf(buildUpon.build().toString());
                zzbo.m10e(valueOf.length() != 0 ? zze.concat(valueOf) : new String(zze));
                return;
            }
            valueOf = buildUpon.build().toString();
            this.zzbCQ.zzOx().zzhi(valueOf);
            String str = "ArbitraryPixel: url = ";
            valueOf = String.valueOf(valueOf);
            zzbo.m11v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            if (zze != null) {
                synchronized (zzj.class) {
                    zzbCP.add(zze);
                    zzdd.zzc(this.mContext, zzbCO, zze, "true");
                }
            }
        }
    }

    boolean zzgS(String str) {
        return this.mContext.getSharedPreferences(zzbCO, 0).contains(str);
    }

    boolean zzgT(String str) {
        return zzbCP.contains(str);
    }
}
