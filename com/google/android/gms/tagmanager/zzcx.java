package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai.zzi;
import com.google.android.gms.internal.zzbgi;
import com.google.android.gms.internal.zzbgi.zze;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzcx {
    private static final zzce<com.google.android.gms.internal.zzaj.zza> zzbFB = new zzce(zzdm.zzQm(), true);
    private final DataLayer zzbCT;
    private final com.google.android.gms.internal.zzbgi.zzc zzbFC;
    private final zzaj zzbFD;
    private final Map<String, zzam> zzbFE;
    private final Map<String, zzam> zzbFF;
    private final Map<String, zzam> zzbFG;
    private final zzl<com.google.android.gms.internal.zzbgi.zza, zzce<com.google.android.gms.internal.zzaj.zza>> zzbFH;
    private final zzl<String, zzb> zzbFI;
    private final Set<zze> zzbFJ;
    private final Map<String, zzc> zzbFK;
    private volatile String zzbFL;
    private int zzbFM;

    interface zza {
        void zza(zze com_google_android_gms_internal_zzbgi_zze, Set<com.google.android.gms.internal.zzbgi.zza> set, Set<com.google.android.gms.internal.zzbgi.zza> set2, zzcs com_google_android_gms_tagmanager_zzcs);
    }

    private static class zzb {
        private zzce<com.google.android.gms.internal.zzaj.zza> zzbFR;
        private com.google.android.gms.internal.zzaj.zza zzbFS;

        public zzb(zzce<com.google.android.gms.internal.zzaj.zza> com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza, com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza) {
            this.zzbFR = com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza;
            this.zzbFS = com_google_android_gms_internal_zzaj_zza;
        }

        public int getSize() {
            return (this.zzbFS == null ? 0 : this.zzbFS.zzacY()) + ((com.google.android.gms.internal.zzaj.zza) this.zzbFR.getObject()).zzacY();
        }

        public zzce<com.google.android.gms.internal.zzaj.zza> zzPL() {
            return this.zzbFR;
        }

        public com.google.android.gms.internal.zzaj.zza zzPM() {
            return this.zzbFS;
        }
    }

    private static class zzc {
        private final Set<zze> zzbFJ = new HashSet();
        private final Map<zze, List<com.google.android.gms.internal.zzbgi.zza>> zzbFT = new HashMap();
        private final Map<zze, List<com.google.android.gms.internal.zzbgi.zza>> zzbFU = new HashMap();
        private final Map<zze, List<String>> zzbFV = new HashMap();
        private final Map<zze, List<String>> zzbFW = new HashMap();
        private com.google.android.gms.internal.zzbgi.zza zzbFX;

        public Set<zze> zzPN() {
            return this.zzbFJ;
        }

        public Map<zze, List<com.google.android.gms.internal.zzbgi.zza>> zzPO() {
            return this.zzbFT;
        }

        public Map<zze, List<String>> zzPP() {
            return this.zzbFV;
        }

        public Map<zze, List<String>> zzPQ() {
            return this.zzbFW;
        }

        public Map<zze, List<com.google.android.gms.internal.zzbgi.zza>> zzPR() {
            return this.zzbFU;
        }

        public com.google.android.gms.internal.zzbgi.zza zzPS() {
            return this.zzbFX;
        }

        public void zza(zze com_google_android_gms_internal_zzbgi_zze) {
            this.zzbFJ.add(com_google_android_gms_internal_zzbgi_zze);
        }

        public void zza(zze com_google_android_gms_internal_zzbgi_zze, com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza) {
            List list = (List) this.zzbFT.get(com_google_android_gms_internal_zzbgi_zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbFT.put(com_google_android_gms_internal_zzbgi_zze, list);
            }
            list.add(com_google_android_gms_internal_zzbgi_zza);
        }

        public void zza(zze com_google_android_gms_internal_zzbgi_zze, String str) {
            List list = (List) this.zzbFV.get(com_google_android_gms_internal_zzbgi_zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbFV.put(com_google_android_gms_internal_zzbgi_zze, list);
            }
            list.add(str);
        }

        public void zzb(com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza) {
            this.zzbFX = com_google_android_gms_internal_zzbgi_zza;
        }

        public void zzb(zze com_google_android_gms_internal_zzbgi_zze, com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza) {
            List list = (List) this.zzbFU.get(com_google_android_gms_internal_zzbgi_zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbFU.put(com_google_android_gms_internal_zzbgi_zze, list);
            }
            list.add(com_google_android_gms_internal_zzbgi_zza);
        }

        public void zzb(zze com_google_android_gms_internal_zzbgi_zze, String str) {
            List list = (List) this.zzbFW.get(com_google_android_gms_internal_zzbgi_zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbFW.put(com_google_android_gms_internal_zzbgi_zze, list);
            }
            list.add(str);
        }
    }

    class C07841 implements com.google.android.gms.tagmanager.zzm.zza<com.google.android.gms.internal.zzbgi.zza, zzce<com.google.android.gms.internal.zzaj.zza>> {
        C07841(zzcx com_google_android_gms_tagmanager_zzcx) {
        }

        public /* synthetic */ int sizeOf(Object obj, Object obj2) {
            return zza((com.google.android.gms.internal.zzbgi.zza) obj, (zzce) obj2);
        }

        public int zza(com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza, zzce<com.google.android.gms.internal.zzaj.zza> com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza) {
            return ((com.google.android.gms.internal.zzaj.zza) com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza.getObject()).zzacY();
        }
    }

    class C07852 implements com.google.android.gms.tagmanager.zzm.zza<String, zzb> {
        C07852(zzcx com_google_android_gms_tagmanager_zzcx) {
        }

        public /* synthetic */ int sizeOf(Object obj, Object obj2) {
            return zza((String) obj, (zzb) obj2);
        }

        public int zza(String str, zzb com_google_android_gms_tagmanager_zzcx_zzb) {
            return str.length() + com_google_android_gms_tagmanager_zzcx_zzb.getSize();
        }
    }

    class C07874 implements zza {
        C07874(zzcx com_google_android_gms_tagmanager_zzcx) {
        }

        public void zza(zze com_google_android_gms_internal_zzbgi_zze, Set<com.google.android.gms.internal.zzbgi.zza> set, Set<com.google.android.gms.internal.zzbgi.zza> set2, zzcs com_google_android_gms_tagmanager_zzcs) {
            set.addAll(com_google_android_gms_internal_zzbgi_zze.zzRx());
            set2.addAll(com_google_android_gms_internal_zzbgi_zze.zzRy());
            com_google_android_gms_tagmanager_zzcs.zzPq();
            com_google_android_gms_tagmanager_zzcs.zzPr();
        }
    }

    public zzcx(Context context, com.google.android.gms.internal.zzbgi.zzc com_google_android_gms_internal_zzbgi_zzc, DataLayer dataLayer, com.google.android.gms.tagmanager.zzu.zza com_google_android_gms_tagmanager_zzu_zza, com.google.android.gms.tagmanager.zzu.zza com_google_android_gms_tagmanager_zzu_zza2, zzaj com_google_android_gms_tagmanager_zzaj) {
        if (com_google_android_gms_internal_zzbgi_zzc == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.zzbFC = com_google_android_gms_internal_zzbgi_zzc;
        this.zzbFJ = new HashSet(com_google_android_gms_internal_zzbgi_zzc.zzRr());
        this.zzbCT = dataLayer;
        this.zzbFD = com_google_android_gms_tagmanager_zzaj;
        this.zzbFH = new zzm().zza(1048576, new C07841(this));
        this.zzbFI = new zzm().zza(1048576, new C07852(this));
        this.zzbFE = new HashMap();
        zzb(new zzj(context));
        zzb(new zzu(com_google_android_gms_tagmanager_zzu_zza2));
        zzb(new zzy(dataLayer));
        zzb(new zzdn(context, dataLayer));
        this.zzbFF = new HashMap();
        zzc(new zzs());
        zzc(new zzag());
        zzc(new zzah());
        zzc(new zzao());
        zzc(new zzap());
        zzc(new zzbk());
        zzc(new zzbl());
        zzc(new zzcn());
        zzc(new zzdg());
        this.zzbFG = new HashMap();
        zza(new zzb(context));
        zza(new zzc(context));
        zza(new zze(context));
        zza(new zzf(context));
        zza(new zzg(context));
        zza(new zzh(context));
        zza(new zzi(context));
        zza(new zzn());
        zza(new zzr(this.zzbFC.getVersion()));
        zza(new zzu(com_google_android_gms_tagmanager_zzu_zza));
        zza(new zzw(dataLayer));
        zza(new zzab(context));
        zza(new zzac());
        zza(new zzaf());
        zza(new zzak(this));
        zza(new zzaq());
        zza(new zzar());
        zza(new zzbe(context));
        zza(new zzbg());
        zza(new zzbj());
        zza(new zzbq());
        zza(new zzbs(context));
        zza(new zzcf());
        zza(new zzch());
        zza(new zzck());
        zza(new zzcm());
        zza(new zzco(context));
        zza(new zzcy());
        zza(new zzcz());
        zza(new zzdi());
        zza(new zzdo());
        this.zzbFK = new HashMap();
        for (zze com_google_android_gms_internal_zzbgi_zze : this.zzbFJ) {
            int i;
            for (i = 0; i < com_google_android_gms_internal_zzbgi_zze.zzSa().size(); i++) {
                com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza = (com.google.android.gms.internal.zzbgi.zza) com_google_android_gms_internal_zzbgi_zze.zzSa().get(i);
                zzc zzh = zzh(this.zzbFK, zza(com_google_android_gms_internal_zzbgi_zza));
                zzh.zza(com_google_android_gms_internal_zzbgi_zze);
                zzh.zza(com_google_android_gms_internal_zzbgi_zze, com_google_android_gms_internal_zzbgi_zza);
                zzh.zza(com_google_android_gms_internal_zzbgi_zze, "Unknown");
            }
            for (i = 0; i < com_google_android_gms_internal_zzbgi_zze.zzSb().size(); i++) {
                com_google_android_gms_internal_zzbgi_zza = (com.google.android.gms.internal.zzbgi.zza) com_google_android_gms_internal_zzbgi_zze.zzSb().get(i);
                zzh = zzh(this.zzbFK, zza(com_google_android_gms_internal_zzbgi_zza));
                zzh.zza(com_google_android_gms_internal_zzbgi_zze);
                zzh.zzb(com_google_android_gms_internal_zzbgi_zze, com_google_android_gms_internal_zzbgi_zza);
                zzh.zzb(com_google_android_gms_internal_zzbgi_zze, "Unknown");
            }
        }
        for (Entry entry : this.zzbFC.zzRX().entrySet()) {
            for (com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza2 : (List) entry.getValue()) {
                if (!zzdm.zzi((com.google.android.gms.internal.zzaj.zza) com_google_android_gms_internal_zzbgi_zza2.zzRt().get(zzah.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzh(this.zzbFK, (String) entry.getKey()).zzb(com_google_android_gms_internal_zzbgi_zza2);
                }
            }
        }
    }

    private String zzPK() {
        if (this.zzbFM <= 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Integer.toString(this.zzbFM));
        for (int i = 2; i < this.zzbFM; i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(": ");
        return stringBuilder.toString();
    }

    private zzce<com.google.android.gms.internal.zzaj.zza> zza(com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza, Set<String> set, zzdp com_google_android_gms_tagmanager_zzdp) {
        if (!com_google_android_gms_internal_zzaj_zza.zzlG) {
            return new zzce(com_google_android_gms_internal_zzaj_zza, true);
        }
        com.google.android.gms.internal.zzaj.zza zzm;
        int i;
        zzce zza;
        String str;
        String valueOf;
        switch (com_google_android_gms_internal_zzaj_zza.type) {
            case 2:
                zzm = zzbgi.zzm(com_google_android_gms_internal_zzaj_zza);
                zzm.zzlx = new com.google.android.gms.internal.zzaj.zza[com_google_android_gms_internal_zzaj_zza.zzlx.length];
                for (i = 0; i < com_google_android_gms_internal_zzaj_zza.zzlx.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzaj_zza.zzlx[i], (Set) set, com_google_android_gms_tagmanager_zzdp.zzmR(i));
                    if (zza == zzbFB) {
                        return zzbFB;
                    }
                    zzm.zzlx[i] = (com.google.android.gms.internal.zzaj.zza) zza.getObject();
                }
                return new zzce(zzm, false);
            case 3:
                zzm = zzbgi.zzm(com_google_android_gms_internal_zzaj_zza);
                if (com_google_android_gms_internal_zzaj_zza.zzly.length != com_google_android_gms_internal_zzaj_zza.zzlz.length) {
                    str = "Invalid serving value: ";
                    valueOf = String.valueOf(com_google_android_gms_internal_zzaj_zza.toString());
                    zzbo.m10e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return zzbFB;
                }
                zzm.zzly = new com.google.android.gms.internal.zzaj.zza[com_google_android_gms_internal_zzaj_zza.zzly.length];
                zzm.zzlz = new com.google.android.gms.internal.zzaj.zza[com_google_android_gms_internal_zzaj_zza.zzly.length];
                for (i = 0; i < com_google_android_gms_internal_zzaj_zza.zzly.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzaj_zza.zzly[i], (Set) set, com_google_android_gms_tagmanager_zzdp.zzmS(i));
                    zzce zza2 = zza(com_google_android_gms_internal_zzaj_zza.zzlz[i], (Set) set, com_google_android_gms_tagmanager_zzdp.zzmT(i));
                    if (zza == zzbFB || zza2 == zzbFB) {
                        return zzbFB;
                    }
                    zzm.zzly[i] = (com.google.android.gms.internal.zzaj.zza) zza.getObject();
                    zzm.zzlz[i] = (com.google.android.gms.internal.zzaj.zza) zza2.getObject();
                }
                return new zzce(zzm, false);
            case 4:
                if (set.contains(com_google_android_gms_internal_zzaj_zza.zzlA)) {
                    valueOf = String.valueOf(com_google_android_gms_internal_zzaj_zza.zzlA);
                    str = String.valueOf(set.toString());
                    zzbo.m10e(new StringBuilder((String.valueOf(valueOf).length() + 79) + String.valueOf(str).length()).append("Macro cycle detected.  Current macro reference: ").append(valueOf).append(".  Previous macro references: ").append(str).append(".").toString());
                    return zzbFB;
                }
                set.add(com_google_android_gms_internal_zzaj_zza.zzlA);
                zzce<com.google.android.gms.internal.zzaj.zza> zza3 = zzdq.zza(zza(com_google_android_gms_internal_zzaj_zza.zzlA, (Set) set, com_google_android_gms_tagmanager_zzdp.zzPt()), com_google_android_gms_internal_zzaj_zza.zzlF);
                set.remove(com_google_android_gms_internal_zzaj_zza.zzlA);
                return zza3;
            case 7:
                zzm = zzbgi.zzm(com_google_android_gms_internal_zzaj_zza);
                zzm.zzlE = new com.google.android.gms.internal.zzaj.zza[com_google_android_gms_internal_zzaj_zza.zzlE.length];
                for (i = 0; i < com_google_android_gms_internal_zzaj_zza.zzlE.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzaj_zza.zzlE[i], (Set) set, com_google_android_gms_tagmanager_zzdp.zzmU(i));
                    if (zza == zzbFB) {
                        return zzbFB;
                    }
                    zzm.zzlE[i] = (com.google.android.gms.internal.zzaj.zza) zza.getObject();
                }
                return new zzce(zzm, false);
            default:
                zzbo.m10e("Unknown type: " + com_google_android_gms_internal_zzaj_zza.type);
                return zzbFB;
        }
    }

    private zzce<com.google.android.gms.internal.zzaj.zza> zza(String str, Set<String> set, zzbr com_google_android_gms_tagmanager_zzbr) {
        this.zzbFM++;
        zzb com_google_android_gms_tagmanager_zzcx_zzb = (zzb) this.zzbFI.get(str);
        if (com_google_android_gms_tagmanager_zzcx_zzb != null) {
            zza(com_google_android_gms_tagmanager_zzcx_zzb.zzPM(), (Set) set);
            this.zzbFM--;
            return com_google_android_gms_tagmanager_zzcx_zzb.zzPL();
        }
        zzc com_google_android_gms_tagmanager_zzcx_zzc = (zzc) this.zzbFK.get(str);
        if (com_google_android_gms_tagmanager_zzcx_zzc == null) {
            String valueOf = String.valueOf(zzPK());
            zzbo.m10e(new StringBuilder((String.valueOf(valueOf).length() + 15) + String.valueOf(str).length()).append(valueOf).append("Invalid macro: ").append(str).toString());
            this.zzbFM--;
            return zzbFB;
        }
        com.google.android.gms.internal.zzbgi.zza zzPS;
        zzce zza = zza(str, com_google_android_gms_tagmanager_zzcx_zzc.zzPN(), com_google_android_gms_tagmanager_zzcx_zzc.zzPO(), com_google_android_gms_tagmanager_zzcx_zzc.zzPP(), com_google_android_gms_tagmanager_zzcx_zzc.zzPR(), com_google_android_gms_tagmanager_zzcx_zzc.zzPQ(), set, com_google_android_gms_tagmanager_zzbr.zzOU());
        if (((Set) zza.getObject()).isEmpty()) {
            zzPS = com_google_android_gms_tagmanager_zzcx_zzc.zzPS();
        } else {
            if (((Set) zza.getObject()).size() > 1) {
                valueOf = String.valueOf(zzPK());
                zzbo.zzbe(new StringBuilder((String.valueOf(valueOf).length() + 37) + String.valueOf(str).length()).append(valueOf).append("Multiple macros active for macroName ").append(str).toString());
            }
            zzPS = (com.google.android.gms.internal.zzbgi.zza) ((Set) zza.getObject()).iterator().next();
        }
        if (zzPS == null) {
            this.zzbFM--;
            return zzbFB;
        }
        zzce zza2 = zza(this.zzbFG, zzPS, (Set) set, com_google_android_gms_tagmanager_zzbr.zzPl());
        boolean z = zza.zzPu() && zza2.zzPu();
        zzce<com.google.android.gms.internal.zzaj.zza> com_google_android_gms_tagmanager_zzce = zza2 == zzbFB ? zzbFB : new zzce((com.google.android.gms.internal.zzaj.zza) zza2.getObject(), z);
        com.google.android.gms.internal.zzaj.zza zzPM = zzPS.zzPM();
        if (com_google_android_gms_tagmanager_zzce.zzPu()) {
            this.zzbFI.zzi(str, new zzb(com_google_android_gms_tagmanager_zzce, zzPM));
        }
        zza(zzPM, (Set) set);
        this.zzbFM--;
        return com_google_android_gms_tagmanager_zzce;
    }

    private zzce<com.google.android.gms.internal.zzaj.zza> zza(Map<String, zzam> map, com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza, Set<String> set, zzcp com_google_android_gms_tagmanager_zzcp) {
        boolean z = true;
        com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza = (com.google.android.gms.internal.zzaj.zza) com_google_android_gms_internal_zzbgi_zza.zzRt().get(zzah.FUNCTION.toString());
        if (com_google_android_gms_internal_zzaj_zza == null) {
            zzbo.m10e("No function id in properties");
            return zzbFB;
        }
        String str = com_google_android_gms_internal_zzaj_zza.zzlB;
        zzam com_google_android_gms_tagmanager_zzam = (zzam) map.get(str);
        if (com_google_android_gms_tagmanager_zzam == null) {
            zzbo.m10e(String.valueOf(str).concat(" has no backing implementation."));
            return zzbFB;
        }
        zzce<com.google.android.gms.internal.zzaj.zza> com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza = (zzce) this.zzbFH.get(com_google_android_gms_internal_zzbgi_zza);
        if (com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza != null) {
            return com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza;
        }
        Map hashMap = new HashMap();
        boolean z2 = true;
        for (Entry entry : com_google_android_gms_internal_zzbgi_zza.zzRt().entrySet()) {
            zzce zza = zza((com.google.android.gms.internal.zzaj.zza) entry.getValue(), (Set) set, com_google_android_gms_tagmanager_zzcp.zzhp((String) entry.getKey()).zzd((com.google.android.gms.internal.zzaj.zza) entry.getValue()));
            if (zza == zzbFB) {
                return zzbFB;
            }
            boolean z3;
            if (zza.zzPu()) {
                com_google_android_gms_internal_zzbgi_zza.zza((String) entry.getKey(), (com.google.android.gms.internal.zzaj.zza) zza.getObject());
                z3 = z2;
            } else {
                z3 = false;
            }
            hashMap.put((String) entry.getKey(), (com.google.android.gms.internal.zzaj.zza) zza.getObject());
            z2 = z3;
        }
        if (com_google_android_gms_tagmanager_zzam.zzf(hashMap.keySet())) {
            if (!(z2 && com_google_android_gms_tagmanager_zzam.zzOw())) {
                z = false;
            }
            com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza = new zzce(com_google_android_gms_tagmanager_zzam.zzY(hashMap), z);
            if (!z) {
                return com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza;
            }
            this.zzbFH.zzi(com_google_android_gms_internal_zzbgi_zza, com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza);
            return com_google_android_gms_tagmanager_zzce_com_google_android_gms_internal_zzaj_zza;
        }
        String valueOf = String.valueOf(com_google_android_gms_tagmanager_zzam.zzPh());
        String valueOf2 = String.valueOf(hashMap.keySet());
        zzbo.m10e(new StringBuilder(((String.valueOf(str).length() + 43) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length()).append("Incorrect keys for function ").append(str).append(" required ").append(valueOf).append(" had ").append(valueOf2).toString());
        return zzbFB;
    }

    private zzce<Set<com.google.android.gms.internal.zzbgi.zza>> zza(Set<zze> set, Set<String> set2, zza com_google_android_gms_tagmanager_zzcx_zza, zzcw com_google_android_gms_tagmanager_zzcw) {
        Set hashSet = new HashSet();
        Collection hashSet2 = new HashSet();
        boolean z = true;
        for (zze com_google_android_gms_internal_zzbgi_zze : set) {
            zzcs zzPs = com_google_android_gms_tagmanager_zzcw.zzPs();
            zzce zza = zza(com_google_android_gms_internal_zzbgi_zze, (Set) set2, zzPs);
            if (((Boolean) zza.getObject()).booleanValue()) {
                com_google_android_gms_tagmanager_zzcx_zza.zza(com_google_android_gms_internal_zzbgi_zze, hashSet, hashSet2, zzPs);
            }
            boolean z2 = z && zza.zzPu();
            z = z2;
        }
        hashSet.removeAll(hashSet2);
        return new zzce(hashSet, z);
    }

    private static String zza(com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza) {
        return zzdm.zze((com.google.android.gms.internal.zzaj.zza) com_google_android_gms_internal_zzbgi_zza.zzRt().get(zzah.INSTANCE_NAME.toString()));
    }

    private void zza(com.google.android.gms.internal.zzaj.zza com_google_android_gms_internal_zzaj_zza, Set<String> set) {
        if (com_google_android_gms_internal_zzaj_zza != null) {
            zzce zza = zza(com_google_android_gms_internal_zzaj_zza, (Set) set, new zzcc());
            if (zza != zzbFB) {
                Object zzj = zzdm.zzj((com.google.android.gms.internal.zzaj.zza) zza.getObject());
                if (zzj instanceof Map) {
                    this.zzbCT.push((Map) zzj);
                } else if (zzj instanceof List) {
                    for (Object zzj2 : (List) zzj2) {
                        if (zzj2 instanceof Map) {
                            this.zzbCT.push((Map) zzj2);
                        } else {
                            zzbo.zzbe("pushAfterEvaluate: value not a Map");
                        }
                    }
                } else {
                    zzbo.zzbe("pushAfterEvaluate: value not a Map or List");
                }
            }
        }
    }

    private static void zza(Map<String, zzam> map, zzam com_google_android_gms_tagmanager_zzam) {
        if (map.containsKey(com_google_android_gms_tagmanager_zzam.zzPg())) {
            String str = "Duplicate function type name: ";
            String valueOf = String.valueOf(com_google_android_gms_tagmanager_zzam.zzPg());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        map.put(com_google_android_gms_tagmanager_zzam.zzPg(), com_google_android_gms_tagmanager_zzam);
    }

    private static zzc zzh(Map<String, zzc> map, String str) {
        zzc com_google_android_gms_tagmanager_zzcx_zzc = (zzc) map.get(str);
        if (com_google_android_gms_tagmanager_zzcx_zzc != null) {
            return com_google_android_gms_tagmanager_zzcx_zzc;
        }
        com_google_android_gms_tagmanager_zzcx_zzc = new zzc();
        map.put(str, com_google_android_gms_tagmanager_zzcx_zzc);
        return com_google_android_gms_tagmanager_zzcx_zzc;
    }

    public synchronized void zzN(List<zzi> list) {
        for (zzi com_google_android_gms_internal_zzai_zzi : list) {
            if (com_google_android_gms_internal_zzai_zzi.name == null || !com_google_android_gms_internal_zzai_zzi.name.startsWith("gaExperiment:")) {
                String valueOf = String.valueOf(com_google_android_gms_internal_zzai_zzi);
                zzbo.m11v(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Ignored supplemental: ").append(valueOf).toString());
            } else {
                zzal.zza(this.zzbCT, com_google_android_gms_internal_zzai_zzi);
            }
        }
    }

    synchronized String zzPJ() {
        return this.zzbFL;
    }

    zzce<Boolean> zza(com.google.android.gms.internal.zzbgi.zza com_google_android_gms_internal_zzbgi_zza, Set<String> set, zzcp com_google_android_gms_tagmanager_zzcp) {
        zzce zza = zza(this.zzbFF, com_google_android_gms_internal_zzbgi_zza, (Set) set, com_google_android_gms_tagmanager_zzcp);
        Boolean zzi = zzdm.zzi((com.google.android.gms.internal.zzaj.zza) zza.getObject());
        zzdm.zzR(zzi);
        return new zzce(zzi, zza.zzPu());
    }

    zzce<Boolean> zza(zze com_google_android_gms_internal_zzbgi_zze, Set<String> set, zzcs com_google_android_gms_tagmanager_zzcs) {
        boolean z = true;
        for (com.google.android.gms.internal.zzbgi.zza zza : com_google_android_gms_internal_zzbgi_zze.zzRw()) {
            zzce zza2 = zza(zza, (Set) set, com_google_android_gms_tagmanager_zzcs.zzPm());
            if (((Boolean) zza2.getObject()).booleanValue()) {
                zzdm.zzR(Boolean.valueOf(false));
                return new zzce(Boolean.valueOf(false), zza2.zzPu());
            }
            boolean z2 = z && zza2.zzPu();
            z = z2;
        }
        for (com.google.android.gms.internal.zzbgi.zza zza3 : com_google_android_gms_internal_zzbgi_zze.zzRv()) {
            zza2 = zza(zza3, (Set) set, com_google_android_gms_tagmanager_zzcs.zzPn());
            if (((Boolean) zza2.getObject()).booleanValue()) {
                z = z && zza2.zzPu();
            } else {
                zzdm.zzR(Boolean.valueOf(false));
                return new zzce(Boolean.valueOf(false), zza2.zzPu());
            }
        }
        zzdm.zzR(Boolean.valueOf(true));
        return new zzce(Boolean.valueOf(true), z);
    }

    zzce<Set<com.google.android.gms.internal.zzbgi.zza>> zza(String str, Set<zze> set, Map<zze, List<com.google.android.gms.internal.zzbgi.zza>> map, Map<zze, List<String>> map2, Map<zze, List<com.google.android.gms.internal.zzbgi.zza>> map3, Map<zze, List<String>> map4, Set<String> set2, zzcw com_google_android_gms_tagmanager_zzcw) {
        final Map<zze, List<com.google.android.gms.internal.zzbgi.zza>> map5 = map;
        final Map<zze, List<String>> map6 = map2;
        final Map<zze, List<com.google.android.gms.internal.zzbgi.zza>> map7 = map3;
        final Map<zze, List<String>> map8 = map4;
        return zza((Set) set, (Set) set2, new zza(this) {
            public void zza(zze com_google_android_gms_internal_zzbgi_zze, Set<com.google.android.gms.internal.zzbgi.zza> set, Set<com.google.android.gms.internal.zzbgi.zza> set2, zzcs com_google_android_gms_tagmanager_zzcs) {
                List list = (List) map5.get(com_google_android_gms_internal_zzbgi_zze);
                map6.get(com_google_android_gms_internal_zzbgi_zze);
                if (list != null) {
                    set.addAll(list);
                    com_google_android_gms_tagmanager_zzcs.zzPo();
                }
                list = (List) map7.get(com_google_android_gms_internal_zzbgi_zze);
                map8.get(com_google_android_gms_internal_zzbgi_zze);
                if (list != null) {
                    set2.addAll(list);
                    com_google_android_gms_tagmanager_zzcs.zzPp();
                }
            }
        }, com_google_android_gms_tagmanager_zzcw);
    }

    zzce<Set<com.google.android.gms.internal.zzbgi.zza>> zza(Set<zze> set, zzcw com_google_android_gms_tagmanager_zzcw) {
        return zza((Set) set, new HashSet(), new C07874(this), com_google_android_gms_tagmanager_zzcw);
    }

    void zza(zzam com_google_android_gms_tagmanager_zzam) {
        zza(this.zzbFG, com_google_android_gms_tagmanager_zzam);
    }

    void zzb(zzam com_google_android_gms_tagmanager_zzam) {
        zza(this.zzbFE, com_google_android_gms_tagmanager_zzam);
    }

    void zzc(zzam com_google_android_gms_tagmanager_zzam) {
        zza(this.zzbFF, com_google_android_gms_tagmanager_zzam);
    }

    public synchronized void zzgX(String str) {
        zzhu(str);
        zzv zzPf = this.zzbFD.zzhk(str).zzPf();
        for (com.google.android.gms.internal.zzbgi.zza zza : (Set) zza(this.zzbFJ, zzPf.zzOU()).getObject()) {
            zza(this.zzbFE, zza, new HashSet(), zzPf.zzOT());
        }
        zzhu(null);
    }

    public zzce<com.google.android.gms.internal.zzaj.zza> zzht(String str) {
        this.zzbFM = 0;
        return zza(str, new HashSet(), this.zzbFD.zzhj(str).zzPe());
    }

    synchronized void zzhu(String str) {
        this.zzbFL = str;
    }
}
