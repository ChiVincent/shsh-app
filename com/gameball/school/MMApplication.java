package com.gameball.school;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.gameball.firebase.MyFirebaseMessagingService;
import com.gameball.tools.BadgeUtil;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;
import io.fabric.sdk.android.Fabric;

public class MMApplication extends Application {
    private static boolean activityVisible;
    private static Context mContext;
    private static Tracker mTracker;

    class C02711 implements ActivityLifecycleCallbacks {
        C02711() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            activity.setRequestedOrientation(1);
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            MyFirebaseMessagingService.mId = 0;
            BadgeUtil.resetBadgeCount(MMApplication.this.getApplicationContext());
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }
    }

    public static synchronized Tracker getDefaultTracker() {
        Tracker tracker;
        synchronized (MMApplication.class) {
            if (mTracker == null) {
                GoogleAnalytics analytics = GoogleAnalytics.getInstance(getAppContext());
                mTracker.enableAdvertisingIdCollection(true);
            }
            tracker = mTracker;
        }
        return tracker;
    }

    public static void sendGoogleAnalyticsEvent(String category, String action) {
        sendGoogleAnalyticsEvent(category, action, null);
    }

    public static void sendGoogleAnalyticsEvent(String category, String action, String label) {
        EventBuilder eventBuilder = new EventBuilder();
        if (category != null) {
            eventBuilder.setCategory(category);
        }
        if (action != null) {
            eventBuilder.setAction(action);
        }
        if (label != null) {
            eventBuilder.setLabel(label);
        }
        getDefaultTracker().send(eventBuilder.build());
    }

    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        registerActivityLifecycleCallbacks(new C02711());
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }
}
