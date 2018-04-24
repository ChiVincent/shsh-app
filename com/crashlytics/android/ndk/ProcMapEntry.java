package com.crashlytics.android.ndk;

class ProcMapEntry {
    public final long address;
    public final String path;
    public final String perms;
    public final long size;

    public ProcMapEntry(long address, long size, String perms, String path) {
        this.address = address;
        this.size = size;
        this.perms = perms;
        this.path = path;
    }
}
