package com.veryitman.msblog.util;

import android.util.Log;

public final class MZLog {
    public static boolean isLog = true;

    public static void logD(String tag, String msg) {
        if (isLog) {
            Log.d(tag, msg);
        }
    }

    public static void logE(String tag, String msg) {
        if (isLog) {
            Log.e(tag, msg);
        }
    }

    public static void logW(String tag, String msg) {
        if (isLog) {
            Log.w(tag, msg);
        }
    }
}
