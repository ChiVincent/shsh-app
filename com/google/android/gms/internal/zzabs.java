package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzabs<T> {
    private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    private static zza zzaCd = null;
    private static int zzaCe = 0;
    private static final Object zztU = new Object();
    protected final String zzAH;
    protected final T zzAI;
    private T zzaCf = null;

    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zzb(String str, Float f);

        Integer zzb(String str, Integer num);
    }

    class C07591 extends zzabs<Boolean> {
        C07591(String str, Boolean bool) {
            super(str, bool);
        }

        protected /* synthetic */ Object zzdd(String str) {
            return zzde(str);
        }

        protected Boolean zzde(String str) {
            zza com_google_android_gms_internal_zzabs_zza = null;
            return com_google_android_gms_internal_zzabs_zza.zza(this.zzAH, (Boolean) this.zzAI);
        }
    }

    class C07602 extends zzabs<Long> {
        C07602(String str, Long l) {
            super(str, l);
        }

        protected /* synthetic */ Object zzdd(String str) {
            return zzdf(str);
        }

        protected Long zzdf(String str) {
            zza com_google_android_gms_internal_zzabs_zza = null;
            return com_google_android_gms_internal_zzabs_zza.getLong(this.zzAH, (Long) this.zzAI);
        }
    }

    class C07613 extends zzabs<Integer> {
        C07613(String str, Integer num) {
            super(str, num);
        }

        protected /* synthetic */ Object zzdd(String str) {
            return zzdg(str);
        }

        protected Integer zzdg(String str) {
            zza com_google_android_gms_internal_zzabs_zza = null;
            return com_google_android_gms_internal_zzabs_zza.zzb(this.zzAH, (Integer) this.zzAI);
        }
    }

    class C07624 extends zzabs<Float> {
        C07624(String str, Float f) {
            super(str, f);
        }

        protected /* synthetic */ Object zzdd(String str) {
            return zzdh(str);
        }

        protected Float zzdh(String str) {
            zza com_google_android_gms_internal_zzabs_zza = null;
            return com_google_android_gms_internal_zzabs_zza.zzb(this.zzAH, (Float) this.zzAI);
        }
    }

    class C07635 extends zzabs<String> {
        C07635(String str, String str2) {
            super(str, str2);
        }

        protected /* synthetic */ Object zzdd(String str) {
            return zzdi(str);
        }

        protected String zzdi(String str) {
            zza com_google_android_gms_internal_zzabs_zza = null;
            return com_google_android_gms_internal_zzabs_zza.getString(this.zzAH, (String) this.zzAI);
        }
    }

    protected zzabs(String str, T t) {
        this.zzAH = str;
        this.zzAI = t;
    }

    public static zzabs<String> zzA(String str, String str2) {
        return new C07635(str, str2);
    }

    public static zzabs<Float> zza(String str, Float f) {
        return new C07624(str, f);
    }

    public static zzabs<Integer> zza(String str, Integer num) {
        return new C07613(str, num);
    }

    public static zzabs<Long> zza(String str, Long l) {
        return new C07602(str, l);
    }

    public static zzabs<Boolean> zzj(String str, boolean z) {
        return new C07591(str, Boolean.valueOf(z));
    }

    public final T get() {
        T zzdd;
        long clearCallingIdentity;
        try {
            zzdd = zzdd(this.zzAH);
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            zzdd = zzdd(this.zzAH);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
        return zzdd;
    }

    protected abstract T zzdd(String str);
}
