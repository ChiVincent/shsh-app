package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

class zzdd {

    class C04311 implements Runnable {
        final /* synthetic */ Editor zzbGo;

        C04311(Editor editor) {
            this.zzbGo = editor;
        }

        public void run() {
            this.zzbGo.commit();
        }
    }

    static void zza(Editor editor) {
        if (VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            new Thread(new C04311(editor)).start();
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    static void zzc(Context context, String str, String str2, String str3) {
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        zza(edit);
    }
}
