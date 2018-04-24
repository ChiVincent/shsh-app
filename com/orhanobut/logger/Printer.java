package com.orhanobut.logger;

public interface Printer {
    void mo2339d(Object obj);

    void mo2340d(String str, Object... objArr);

    void mo2341e(String str, Object... objArr);

    void mo2342e(Throwable th, String str, Object... objArr);

    Settings getSettings();

    void mo2344i(String str, Object... objArr);

    Settings init(String str);

    void json(String str);

    void log(int i, String str, String str2, Throwable th);

    void resetSettings();

    Printer mo2349t(String str, int i);

    void mo2350v(String str, Object... objArr);

    void mo2351w(String str, Object... objArr);

    void wtf(String str, Object... objArr);

    void xml(String str);
}
