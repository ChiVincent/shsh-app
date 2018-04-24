package io.fabric.sdk.android;

public interface Logger {
    void mo2376d(String str, String str2);

    void mo2377d(String str, String str2, Throwable th);

    void mo2378e(String str, String str2);

    void mo2379e(String str, String str2, Throwable th);

    int getLogLevel();

    void mo2381i(String str, String str2);

    void mo2382i(String str, String str2, Throwable th);

    boolean isLoggable(String str, int i);

    void log(int i, String str, String str2);

    void log(int i, String str, String str2, boolean z);

    void setLogLevel(int i);

    void mo2387v(String str, String str2);

    void mo2388v(String str, String str2, Throwable th);

    void mo2389w(String str, String str2);

    void mo2390w(String str, String str2, Throwable th);
}
