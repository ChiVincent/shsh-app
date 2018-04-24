package com.jakewharton.processphoenix;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Process;
import java.util.List;

public final class ProcessPhoenix extends Activity {
    private static final String KEY_RESTART_INTENT = "phoenix_restart_intent";

    public static void triggerRebirth(Context context) {
        triggerRebirth(context, getRestartIntent(context));
    }

    public static void triggerRebirth(Context context, Intent nextIntent) {
        Intent intent = new Intent(context, ProcessPhoenix.class);
        intent.addFlags(268435456);
        intent.putExtra(KEY_RESTART_INTENT, nextIntent);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
        Runtime.getRuntime().exit(0);
    }

    private static Intent getRestartIntent(Context context) {
        Intent defaultIntent = new Intent("android.intent.action.MAIN", null);
        defaultIntent.addFlags(268468224);
        defaultIntent.addCategory("android.intent.category.DEFAULT");
        String packageName = context.getPackageName();
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(defaultIntent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo.packageName.equals(packageName)) {
                defaultIntent.setComponent(new ComponentName(packageName, activityInfo.name));
                return defaultIntent;
            }
        }
        throw new IllegalStateException("Unable to determine default activity for " + packageName + ". Does an activity specify the DEFAULT category in its intent filter?");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity((Intent) getIntent().getParcelableExtra(KEY_RESTART_INTENT));
        finish();
        Runtime.getRuntime().exit(0);
    }

    public static boolean isPhoenixProcess(Context context) {
        int currentPid = Process.myPid();
        List<RunningAppProcessInfo> runningProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningProcesses != null) {
            for (RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.pid == currentPid && processInfo.processName.endsWith(":phoenix")) {
                    return true;
                }
            }
        }
        return false;
    }
}
