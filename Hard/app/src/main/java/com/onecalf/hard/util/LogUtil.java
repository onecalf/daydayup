package com.onecalf.hard.util;

import android.util.Log;

import com.onecalf.hard.BuildConfig;

public class LogUtil {
    private static boolean debug = BuildConfig.DEBUG;
    private static String defaultTag = "log";

    public static void e(String msg) {
        if (debug) {
            Log.e(defaultTag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, Throwable e) {
        if (debug && e != null) {
            Log.e(tag, e.getMessage());
        }
    }

    public static void d(String msg) {
        if (debug) {
            Log.d(defaultTag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (debug) {
            Log.d(tag, msg);
        }
    }

    public static void i(String msg) {
        if (debug) {
            Log.i(defaultTag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (debug) {
            Log.i(tag, msg);
        }
    }

    public static void w(String msg) {
        if (debug) {
            Log.w(defaultTag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (debug) {
            Log.w(tag, msg);
        }
    }
}
