package com.crashlytics.android.ndk;

import android.content.Context;
import com.crashlytics.android.core.internal.models.SessionEventData;

interface NdkKitController {
    void clearCachedData();

    SessionEventData getPreviousCrashData();

    boolean initialize(Context context);

    void loadPreviousCrashData();
}
