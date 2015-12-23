package com.example.colin.Utils;

import android.util.Log;

/**
 * Created by colin on 15-12-21.
 */
public class LogUtil {
    public LogUtil() {
    }
    public static boolean isDebug = true;
    private static final String TAG = "c-life";
    public static void i(String msg)
    {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg)
    {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }
    public static void v(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }


    public static void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.v(tag, msg);
    }

}
