package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class zzcg implements zzaw {
    private static final String zzadt = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    private final Context mContext;
    private final zzb zzbEQ;
    private volatile zzad zzbER;
    private final zzax zzbES;
    private final String zzbET;
    private long zzbEU;
    private final int zzbEV;
    private zze zzuI;

    class zzb extends SQLiteOpenHelper {
        final /* synthetic */ zzcg zzbEW;
        private boolean zzbEX;
        private long zzbEY = 0;

        zzb(zzcg com_google_android_gms_tagmanager_zzcg, Context context, String str) {
            this.zzbEW = com_google_android_gms_tagmanager_zzcg;
            super(context, str, null, 1);
        }

        private boolean zza(String str, SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            String str2;
            String valueOf;
            Throwable th;
            Cursor cursor2 = null;
            try {
                Cursor query = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
                try {
                    boolean moveToFirst = query.moveToFirst();
                    if (query == null) {
                        return moveToFirst;
                    }
                    query.close();
                    return moveToFirst;
                } catch (SQLiteException e) {
                    cursor = query;
                    try {
                        str2 = "Error querying for table ";
                        valueOf = String.valueOf(str);
                        zzbo.zzbe(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        cursor2 = cursor;
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cursor2 = query;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e2) {
                cursor = null;
                str2 = "Error querying for table ";
                valueOf = String.valueOf(str);
                if (valueOf.length() == 0) {
                }
                zzbo.zzbe(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf));
                if (cursor != null) {
                    cursor.close();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        }

        private void zzc(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
            Set hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (Object add : columnNames) {
                    hashSet.add(add);
                }
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_time") || !hashSet.remove("hit_first_send_time")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } finally {
                rawQuery.close();
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            if (!this.zzbEX || this.zzbEY + 3600000 <= this.zzbEW.zzuI.currentTimeMillis()) {
                SQLiteDatabase sQLiteDatabase = null;
                this.zzbEX = true;
                this.zzbEY = this.zzbEW.zzuI.currentTimeMillis();
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (SQLiteException e) {
                    this.zzbEW.mContext.getDatabasePath(this.zzbEW.zzbET).delete();
                }
                if (sQLiteDatabase == null) {
                    sQLiteDatabase = super.getWritableDatabase();
                }
                this.zzbEX = false;
                return sQLiteDatabase;
            }
            throw new SQLiteException("Database creation failed");
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzan.zzbZ(sQLiteDatabase.getPath());
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (VERSION.SDK_INT < 15) {
                Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (zza("gtm_hits", sQLiteDatabase)) {
                zzc(sQLiteDatabase);
            } else {
                sQLiteDatabase.execSQL(zzcg.zzadt);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    class zza implements com.google.android.gms.tagmanager.zzdf.zza {
        final /* synthetic */ zzcg zzbEW;

        zza(zzcg com_google_android_gms_tagmanager_zzcg) {
            this.zzbEW = com_google_android_gms_tagmanager_zzcg;
        }

        public void zza(zzas com_google_android_gms_tagmanager_zzas) {
            this.zzbEW.zzu(com_google_android_gms_tagmanager_zzas.zzPi());
        }

        public void zzb(zzas com_google_android_gms_tagmanager_zzas) {
            this.zzbEW.zzu(com_google_android_gms_tagmanager_zzas.zzPi());
            zzbo.m11v("Permanent failure dispatching hitId: " + com_google_android_gms_tagmanager_zzas.zzPi());
        }

        public void zzc(zzas com_google_android_gms_tagmanager_zzas) {
            long zzPj = com_google_android_gms_tagmanager_zzas.zzPj();
            if (zzPj == 0) {
                this.zzbEW.zzh(com_google_android_gms_tagmanager_zzas.zzPi(), this.zzbEW.zzuI.currentTimeMillis());
            } else if (zzPj + 14400000 < this.zzbEW.zzuI.currentTimeMillis()) {
                this.zzbEW.zzu(com_google_android_gms_tagmanager_zzas.zzPi());
                zzbo.m11v("Giving up on failed hitId: " + com_google_android_gms_tagmanager_zzas.zzPi());
            }
        }
    }

    zzcg(zzax com_google_android_gms_tagmanager_zzax, Context context) {
        this(com_google_android_gms_tagmanager_zzax, context, "gtm_urls.db", 2000);
    }

    zzcg(zzax com_google_android_gms_tagmanager_zzax, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.zzbET = str;
        this.zzbES = com_google_android_gms_tagmanager_zzax;
        this.zzuI = zzh.zzyv();
        this.zzbEQ = new zzb(this, this.mContext, this.zzbET);
        this.zzbER = new zzdf(this.mContext, new zza(this));
        this.zzbEU = 0;
        this.zzbEV = i;
    }

    private void zzPv() {
        int zzPw = (zzPw() - this.zzbEV) + 1;
        if (zzPw > 0) {
            List zzmV = zzmV(zzPw);
            zzbo.m11v("Store full, deleting " + zzmV.size() + " hits to make room.");
            zzh((String[]) zzmV.toArray(new String[0]));
        }
    }

    private void zzh(long j, long j2) {
        SQLiteDatabase zzhh = zzhh("Error opening database for getNumStoredHits.");
        if (zzhh != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzhh.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException e) {
                zzbo.zzbe("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + j);
                zzu(j);
            }
        }
    }

    private void zzh(long j, String str) {
        SQLiteDatabase zzhh = zzhh("Error opening database for putHit");
        if (zzhh != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", Integer.valueOf(0));
            try {
                zzhh.insert("gtm_hits", null, contentValues);
                this.zzbES.zzaM(false);
            } catch (SQLiteException e) {
                zzbo.zzbe("Error storing hit");
            }
        }
    }

    private SQLiteDatabase zzhh(String str) {
        try {
            return this.zzbEQ.getWritableDatabase();
        } catch (SQLiteException e) {
            zzbo.zzbe(str);
            return null;
        }
    }

    private void zzu(long j) {
        zzh(new String[]{String.valueOf(j)});
    }

    public void dispatch() {
        zzbo.m11v("GTM Dispatch running...");
        if (this.zzbER.zzPa()) {
            List zzmW = zzmW(40);
            if (zzmW.isEmpty()) {
                zzbo.m11v("...nothing to dispatch");
                this.zzbES.zzaM(true);
                return;
            }
            this.zzbER.zzM(zzmW);
            if (zzPx() > 0) {
                zzdc.zzPT().dispatch();
            }
        }
    }

    int zzPw() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase zzhh = zzhh("Error opening database for getNumStoredHits.");
        if (zzhh != null) {
            try {
                cursor = zzhh.rawQuery("SELECT COUNT(*) from gtm_hits", null);
                if (cursor.moveToFirst()) {
                    i = (int) cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteException e) {
                zzbo.zzbe("Error getting numStoredHits");
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return i;
    }

    int zzPx() {
        int count;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        SQLiteDatabase zzhh = zzhh("Error opening database for getNumStoredHits.");
        if (zzhh == null) {
            return 0;
        }
        try {
            Cursor query = zzhh.query("gtm_hits", new String[]{"hit_id", "hit_first_send_time"}, "hit_first_send_time=0", null, null, null, null);
            try {
                count = query.getCount();
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e) {
                cursor = query;
                try {
                    zzbo.zzbe("Error getting num untried hits");
                    if (cursor == null) {
                        count = 0;
                    } else {
                        cursor.close();
                        count = 0;
                    }
                    return count;
                } catch (Throwable th2) {
                    cursor2 = cursor;
                    th = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            cursor = null;
            zzbo.zzbe("Error getting num untried hits");
            if (cursor == null) {
                cursor.close();
                count = 0;
            } else {
                count = 0;
            }
            return count;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        return count;
    }

    public void zzg(long j, String str) {
        zznS();
        zzPv();
        zzh(j, str);
    }

    void zzh(String[] strArr) {
        boolean z = true;
        if (strArr != null && strArr.length != 0) {
            SQLiteDatabase zzhh = zzhh("Error opening database for deleteHits.");
            if (zzhh != null) {
                try {
                    zzhh.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                    zzax com_google_android_gms_tagmanager_zzax = this.zzbES;
                    if (zzPw() != 0) {
                        z = false;
                    }
                    com_google_android_gms_tagmanager_zzax.zzaM(z);
                } catch (SQLiteException e) {
                    zzbo.zzbe("Error deleting hits");
                }
            }
        }
    }

    List<String> zzmV(int i) {
        Cursor query;
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        List<String> arrayList = new ArrayList();
        if (i <= 0) {
            zzbo.zzbe("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase zzhh = zzhh("Error opening database for peekHitIds.");
        if (zzhh == null) {
            return arrayList;
        }
        try {
            query = zzhh.query("gtm_hits", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", new Object[]{"hit_id"}), Integer.toString(i));
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(query.getLong(0)));
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    str = "Error in peekHits fetching hitIds: ";
                    valueOf = String.valueOf(e.getMessage());
                    zzbo.zzbe(valueOf.length() == 0 ? str.concat(valueOf) : new String(str));
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            str = "Error in peekHits fetching hitIds: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            zzbo.zzbe(valueOf.length() == 0 ? str.concat(valueOf) : new String(str));
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.tagmanager.zzas> zzmW(int r17) {
        /*
        r16 = this;
        r11 = new java.util.ArrayList;
        r11.<init>();
        r2 = "Error opening database for peekHits";
        r0 = r16;
        r2 = r0.zzhh(r2);
        if (r2 != 0) goto L_0x0011;
    L_0x000f:
        r2 = r11;
    L_0x0010:
        return r2;
    L_0x0011:
        r12 = 0;
        r3 = "gtm_hits";
        r4 = 3;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r5 = 1;
        r6 = "hit_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r5 = 2;
        r6 = "hit_first_send_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r13 = 0;
        r14 = "hit_id";
        r10[r13] = r14;	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r10 = java.lang.Integer.toString(r17);	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r12 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x0178, all -> 0x0174 }
        r12.<init>();	 Catch:{ SQLiteException -> 0x0178, all -> 0x0174 }
        r3 = r13.moveToFirst();	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        if (r3 == 0) goto L_0x0068;
    L_0x004b:
        r3 = new com.google.android.gms.tagmanager.zzas;	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r4 = 0;
        r4 = r13.getLong(r4);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r6 = 1;
        r6 = r13.getLong(r6);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r8 = 2;
        r8 = r13.getLong(r8);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r3.<init>(r4, r6, r8);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r12.add(r3);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r3 = r13.moveToNext();	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        if (r3 != 0) goto L_0x004b;
    L_0x0068:
        if (r13 == 0) goto L_0x006d;
    L_0x006a:
        r13.close();
    L_0x006d:
        r11 = 0;
        r3 = "gtm_hits";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x0170 }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0170 }
        r5 = 1;
        r6 = "hit_url";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0170 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x0170 }
        r14 = 0;
        r15 = "hit_id";
        r10[r14] = r15;	 Catch:{ SQLiteException -> 0x0170 }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x0170 }
        r10 = java.lang.Integer.toString(r17);	 Catch:{ SQLiteException -> 0x0170 }
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x0170 }
        r2 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        if (r2 == 0) goto L_0x00c2;
    L_0x009d:
        r4 = r11;
    L_0x009e:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteCursor) r0;	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = r0;
        r2 = r2.getWindow();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = r2.getNumRows();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        if (r2 <= 0) goto L_0x00fa;
    L_0x00ac:
        r2 = r12.get(r4);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = (com.google.android.gms.tagmanager.zzas) r2;	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r5 = 1;
        r5 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2.zzhl(r5);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
    L_0x00ba:
        r2 = r4 + 1;
        r4 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        if (r4 != 0) goto L_0x0184;
    L_0x00c2:
        if (r3 == 0) goto L_0x00c7;
    L_0x00c4:
        r3.close();
    L_0x00c7:
        r2 = r12;
        goto L_0x0010;
    L_0x00ca:
        r2 = move-exception;
        r3 = r2;
        r4 = r12;
        r2 = r11;
    L_0x00ce:
        r5 = "Error in peekHits fetching hitIds: ";
        r3 = r3.getMessage();	 Catch:{ all -> 0x00f2 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x00f2 }
        r6 = r3.length();	 Catch:{ all -> 0x00f2 }
        if (r6 == 0) goto L_0x00ec;
    L_0x00de:
        r3 = r5.concat(r3);	 Catch:{ all -> 0x00f2 }
    L_0x00e2:
        com.google.android.gms.tagmanager.zzbo.zzbe(r3);	 Catch:{ all -> 0x00f2 }
        if (r4 == 0) goto L_0x0010;
    L_0x00e7:
        r4.close();
        goto L_0x0010;
    L_0x00ec:
        r3 = new java.lang.String;	 Catch:{ all -> 0x00f2 }
        r3.<init>(r5);	 Catch:{ all -> 0x00f2 }
        goto L_0x00e2;
    L_0x00f2:
        r2 = move-exception;
        r12 = r4;
    L_0x00f4:
        if (r12 == 0) goto L_0x00f9;
    L_0x00f6:
        r12.close();
    L_0x00f9:
        throw r2;
    L_0x00fa:
        r5 = "HitString for hitId %d too large.  Hit will be deleted.";
        r2 = 1;
        r6 = new java.lang.Object[r2];	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r7 = 0;
        r2 = r12.get(r4);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = (com.google.android.gms.tagmanager.zzas) r2;	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r8 = r2.zzPi();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r6[r7] = r2;	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = java.lang.String.format(r5, r6);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        com.google.android.gms.tagmanager.zzbo.zzbe(r2);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        goto L_0x00ba;
    L_0x0118:
        r2 = move-exception;
        r13 = r3;
    L_0x011a:
        r3 = "Error in peekHits fetching hit url: ";
        r2 = r2.getMessage();	 Catch:{ all -> 0x0161 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x0161 }
        r4 = r2.length();	 Catch:{ all -> 0x0161 }
        if (r4 == 0) goto L_0x015b;
    L_0x012a:
        r2 = r3.concat(r2);	 Catch:{ all -> 0x0161 }
    L_0x012e:
        com.google.android.gms.tagmanager.zzbo.zzbe(r2);	 Catch:{ all -> 0x0161 }
        r3 = new java.util.ArrayList;	 Catch:{ all -> 0x0161 }
        r3.<init>();	 Catch:{ all -> 0x0161 }
        r4 = 0;
        r5 = r12.iterator();	 Catch:{ all -> 0x0161 }
    L_0x013b:
        r2 = r5.hasNext();	 Catch:{ all -> 0x0161 }
        if (r2 == 0) goto L_0x0153;
    L_0x0141:
        r2 = r5.next();	 Catch:{ all -> 0x0161 }
        r2 = (com.google.android.gms.tagmanager.zzas) r2;	 Catch:{ all -> 0x0161 }
        r6 = r2.zzPk();	 Catch:{ all -> 0x0161 }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x0161 }
        if (r6 == 0) goto L_0x0169;
    L_0x0151:
        if (r4 == 0) goto L_0x0168;
    L_0x0153:
        if (r13 == 0) goto L_0x0158;
    L_0x0155:
        r13.close();
    L_0x0158:
        r2 = r3;
        goto L_0x0010;
    L_0x015b:
        r2 = new java.lang.String;	 Catch:{ all -> 0x0161 }
        r2.<init>(r3);	 Catch:{ all -> 0x0161 }
        goto L_0x012e;
    L_0x0161:
        r2 = move-exception;
    L_0x0162:
        if (r13 == 0) goto L_0x0167;
    L_0x0164:
        r13.close();
    L_0x0167:
        throw r2;
    L_0x0168:
        r4 = 1;
    L_0x0169:
        r3.add(r2);	 Catch:{ all -> 0x0161 }
        goto L_0x013b;
    L_0x016d:
        r2 = move-exception;
        r13 = r3;
        goto L_0x0162;
    L_0x0170:
        r2 = move-exception;
        goto L_0x011a;
    L_0x0172:
        r2 = move-exception;
        goto L_0x00f4;
    L_0x0174:
        r2 = move-exception;
        r12 = r13;
        goto L_0x00f4;
    L_0x0178:
        r2 = move-exception;
        r3 = r2;
        r4 = r13;
        r2 = r11;
        goto L_0x00ce;
    L_0x017e:
        r2 = move-exception;
        r3 = r2;
        r4 = r13;
        r2 = r12;
        goto L_0x00ce;
    L_0x0184:
        r4 = r2;
        goto L_0x009e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcg.zzmW(int):java.util.List<com.google.android.gms.tagmanager.zzas>");
    }

    int zznS() {
        boolean z = true;
        long currentTimeMillis = this.zzuI.currentTimeMillis();
        if (currentTimeMillis <= this.zzbEU + 86400000) {
            return 0;
        }
        this.zzbEU = currentTimeMillis;
        SQLiteDatabase zzhh = zzhh("Error opening database for deleteStaleHits.");
        if (zzhh == null) {
            return 0;
        }
        int delete = zzhh.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zzuI.currentTimeMillis() - 2592000000L)});
        zzax com_google_android_gms_tagmanager_zzax = this.zzbES;
        if (zzPw() != 0) {
            z = false;
        }
        com_google_android_gms_tagmanager_zzax.zzaM(z);
        return delete;
    }
}
