package com.crashlytics.android.ndk;

import android.content.Context;
import com.crashlytics.android.core.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class NdkKitControllerImpl implements NdkKitController {
    private final JsonCrashDataParser crashDataParser;
    private final CrashFileManager crashFileManager;
    private SessionEventData lastCrashEventData;
    private final NativeApi nativeApi;

    public static NdkKitController create(CrashlyticsNdk kit) {
        return new NdkKitControllerImpl(new JniNativeApi(), new TimeBasedCrashFileManager(new FileStoreImpl(kit)), new JsonCrashDataParser());
    }

    NdkKitControllerImpl(NativeApi nativeApi, CrashFileManager crashFileManager, JsonCrashDataParser crashDataParser) {
        this.nativeApi = nativeApi;
        this.crashFileManager = crashFileManager;
        this.crashDataParser = crashDataParser;
    }

    public boolean initialize(Context context) {
        boolean initSuccess = false;
        try {
            initSuccess = this.nativeApi.initialize(this.crashFileManager.getNewCrashFile().getCanonicalPath(), context.getAssets());
        } catch (IOException e) {
            Fabric.getLogger().mo2379e("CrashlyticsNdk", "Error initializing CrashlyticsNdk", e);
        }
        return initSuccess;
    }

    public void loadPreviousCrashData() {
        File lastCrashFile = this.crashFileManager.getLastWrittenCrashFile();
        if (lastCrashFile != null && lastCrashFile.isFile()) {
            Fabric.getLogger().mo2376d("CrashlyticsNdk", "Found NDK crash file...");
            String rawCrashData = readJsonCrashFile(lastCrashFile);
            if (rawCrashData != null) {
                try {
                    this.lastCrashEventData = this.crashDataParser.parseCrashEventData(rawCrashData);
                } catch (Exception e) {
                    Fabric.getLogger().mo2378e("CrashlyticsNdk", "Crashlytics failed to parse prior crash data.");
                }
            }
        }
    }

    public SessionEventData getPreviousCrashData() {
        return this.lastCrashEventData;
    }

    public void clearCachedData() {
        this.crashFileManager.clearCrashFiles();
    }

    private String readJsonCrashFile(File crashFile) {
        Exception e;
        Throwable th;
        String crashData = null;
        Fabric.getLogger().mo2376d("CrashlyticsNdk", "Reading NDK crash data...");
        FileInputStream fis = null;
        try {
            FileInputStream fis2 = new FileInputStream(crashFile);
            try {
                crashData = CommonUtils.streamToString(fis2);
                CommonUtils.closeOrLog(fis2, "Error closing crash data file.");
                fis = fis2;
            } catch (Exception e2) {
                e = e2;
                fis = fis2;
                try {
                    Fabric.getLogger().mo2379e("CrashlyticsNdk", "Failed to read NDK crash data.", e);
                    CommonUtils.closeOrLog(fis, "Error closing crash data file.");
                    return crashData;
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.closeOrLog(fis, "Error closing crash data file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fis = fis2;
                CommonUtils.closeOrLog(fis, "Error closing crash data file.");
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Fabric.getLogger().mo2379e("CrashlyticsNdk", "Failed to read NDK crash data.", e);
            CommonUtils.closeOrLog(fis, "Error closing crash data file.");
            return crashData;
        }
        return crashData;
    }
}
