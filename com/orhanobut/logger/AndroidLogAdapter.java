package com.orhanobut.logger;

import android.util.Log;

class AndroidLogAdapter implements LogAdapter {
    AndroidLogAdapter() {
    }

    public void mo2333d(String tag, String message) {
        Log.d(tag, message);
    }

    public void mo2334e(String tag, String message) {
        Log.e(tag, message);
    }

    public void mo2337w(String tag, String message) {
        Log.w(tag, message);
    }

    public void mo2335i(String tag, String message) {
        Log.i(tag, message);
    }

    public void mo2336v(String tag, String message) {
        Log.v(tag, message);
    }

    public void wtf(String tag, String message) {
        Log.wtf(tag, message);
    }
}
