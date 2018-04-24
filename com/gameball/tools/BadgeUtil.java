package com.gameball.tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class BadgeUtil {
    private static final String ACTION_BADGE_COUNT_UPDATE = "android.intent.action.BADGE_COUNT_UPDATE";
    private static final String EXTRA_BADGE_COUNT = "badge_count";
    private static final String EXTRA_BADGE_COUNT_CLASS_NAME = "badge_count_class_name";
    private static final String EXTRA_BADGE_COUNT_PACKAGE_NAME = "badge_count_package_name";
    private static final String TAG = "BadgeUtil";

    public static void setBadgeCount(Context context, int count) {
        Intent badgeIntent = new Intent(ACTION_BADGE_COUNT_UPDATE);
        badgeIntent.putExtra(EXTRA_BADGE_COUNT, count);
        badgeIntent.putExtra(EXTRA_BADGE_COUNT_PACKAGE_NAME, context.getPackageName());
        badgeIntent.putExtra(EXTRA_BADGE_COUNT_CLASS_NAME, getLauncherClassName(context));
        context.sendBroadcast(badgeIntent);
    }

    public static void resetBadgeCount(Context context) {
        setBadgeCount(context, 0);
    }

    private static String getLauncherClassName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setPackage(context.getPackageName());
        intent.addCategory("android.intent.category.LAUNCHER");
        ResolveInfo info = packageManager.resolveActivity(intent, 65536);
        if (info == null) {
            info = packageManager.resolveActivity(intent, 0);
        }
        return info.activityInfo.name;
    }
}
