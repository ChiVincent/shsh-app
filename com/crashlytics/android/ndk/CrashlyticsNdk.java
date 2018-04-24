package com.crashlytics.android.ndk;

import com.crashlytics.android.core.CrashlyticsCore;
import com.crashlytics.android.core.CrashlyticsKitBinder;
import com.crashlytics.android.core.internal.CrashEventDataProvider;
import com.crashlytics.android.core.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;

public class CrashlyticsNdk extends Kit<Void> implements CrashEventDataProvider {
    static final String TAG = "CrashlyticsNdk";
    private NdkKitController kitController;

    public String getVersion() {
        return "1.1.6.167";
    }

    public String getIdentifier() {
        return "com.crashlytics.sdk.android.crashlytics-ndk";
    }

    public static CrashlyticsNdk getInstance() {
        return (CrashlyticsNdk) Fabric.getKit(CrashlyticsNdk.class);
    }

    public SessionEventData getCrashEventData() {
        return this.kitController.getPreviousCrashData();
    }

    protected boolean onPreExecute() {
        CrashlyticsCore crashlyticsCore = (CrashlyticsCore) Fabric.getKit(CrashlyticsCore.class);
        if (crashlyticsCore != null) {
            return onPreExecute(NdkKitControllerImpl.create(this), crashlyticsCore, new CrashlyticsKitBinder());
        }
        throw new UnmetDependencyException("CrashlyticsNdk requires Crashlytics");
    }

    boolean onPreExecute(NdkKitController kitController, CrashlyticsCore crashlyticsCore, CrashlyticsKitBinder kitBinder) {
        this.kitController = kitController;
        boolean initSuccessful = kitController.initialize(getContext());
        if (initSuccessful) {
            kitBinder.bindCrashEventDataProvider(crashlyticsCore, this);
            Fabric.getLogger().mo2376d(TAG, "Crashlytics NDK initialization successful");
        }
        return initSuccessful;
    }

    protected Void doInBackground() {
        this.kitController.loadPreviousCrashData();
        this.kitController.clearCachedData();
        return null;
    }
}
