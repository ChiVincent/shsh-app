package com.crashlytics.android.ndk;

import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.persistence.FileStore;
import java.io.File;

class TimeBasedCrashFileManager implements CrashFileManager {
    private static final String CRASHFILE_EXT = ".ndk.json";
    private static final File[] EMPTY_FILES = new File[0];
    private final FileStore fileStore;
    private final CurrentTimeProvider timeProvider;

    public TimeBasedCrashFileManager(FileStore fileStore) {
        this(fileStore, new SystemCurrentTimeProvider());
    }

    TimeBasedCrashFileManager(FileStore fileStore, CurrentTimeProvider timeProvider) {
        this.fileStore = fileStore;
        this.timeProvider = timeProvider;
    }

    public File getNewCrashFile() {
        return new File(getDataDirectory(), this.timeProvider.getCurrentTimeMillis() + CRASHFILE_EXT);
    }

    public File getLastWrittenCrashFile() {
        return findLatestCrashFileByValue();
    }

    public void clearCrashFiles() {
        for (File f : getFiles()) {
            f.delete();
        }
    }

    private File findLatestCrashFileByValue() {
        File latestFile = null;
        File[] files = getFiles();
        long highest = 0;
        for (File f : files) {
            long value = Long.parseLong(stripExtension(f.getName()));
            if (value > highest) {
                highest = value;
                latestFile = f;
            }
        }
        return latestFile;
    }

    private String stripExtension(String fileName) {
        return fileName.substring(0, fileName.length() - CRASHFILE_EXT.length());
    }

    private File[] getFiles() {
        File[] files = getDataDirectory().listFiles();
        return files == null ? EMPTY_FILES : files;
    }

    private File getDataDirectory() {
        return this.fileStore.getFilesDir();
    }
}
