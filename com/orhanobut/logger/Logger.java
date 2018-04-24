package com.orhanobut.logger;

public final class Logger {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    private static final String DEFAULT_TAG = "PRETTYLOGGER";
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static Printer printer = new LoggerPrinter();

    private Logger() {
    }

    public static Settings init() {
        return init(DEFAULT_TAG);
    }

    public static Settings init(String tag) {
        printer = new LoggerPrinter();
        return printer.init(tag);
    }

    public static void resetSettings() {
        printer.resetSettings();
    }

    public static Printer m25t(String tag) {
        return printer.mo2349t(tag, printer.getSettings().getMethodCount());
    }

    public static Printer m24t(int methodCount) {
        return printer.mo2349t(null, methodCount);
    }

    public static Printer m26t(String tag, int methodCount) {
        return printer.mo2349t(tag, methodCount);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

    public static void m20d(String message, Object... args) {
        printer.mo2340d(message, args);
    }

    public static void m19d(Object object) {
        printer.mo2339d(object);
    }

    public static void m21e(String message, Object... args) {
        printer.mo2342e(null, message, args);
    }

    public static void m22e(Throwable throwable, String message, Object... args) {
        printer.mo2342e(throwable, message, args);
    }

    public static void m23i(String message, Object... args) {
        printer.mo2344i(message, args);
    }

    public static void m27v(String message, Object... args) {
        printer.mo2350v(message, args);
    }

    public static void m28w(String message, Object... args) {
        printer.mo2351w(message, args);
    }

    public static void wtf(String message, Object... args) {
        printer.wtf(message, args);
    }

    public static void json(String json) {
        printer.json(json);
    }

    public static void xml(String xml) {
        printer.xml(xml);
    }
}
