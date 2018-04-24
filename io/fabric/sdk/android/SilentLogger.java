package io.fabric.sdk.android;

public class SilentLogger implements Logger {
    private int logLevel = 7;

    public boolean isLoggable(String tag, int level) {
        return false;
    }

    public void mo2377d(String tag, String text, Throwable throwable) {
    }

    public void mo2388v(String tag, String text, Throwable throwable) {
    }

    public void mo2382i(String tag, String text, Throwable throwable) {
    }

    public void mo2390w(String tag, String text, Throwable throwable) {
    }

    public void mo2379e(String tag, String text, Throwable throwable) {
    }

    public void mo2376d(String tag, String text) {
    }

    public void mo2387v(String tag, String text) {
    }

    public void mo2381i(String tag, String text) {
    }

    public void mo2389w(String tag, String text) {
    }

    public void mo2378e(String tag, String text) {
    }

    public void log(int priority, String tag, String msg) {
    }

    public void log(int priority, String tag, String msg, boolean forceLog) {
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(int logLevel) {
    }
}
