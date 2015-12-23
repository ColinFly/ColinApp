package com.example.colin.Utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

/**
 * Created by colin on 15-12-21.
 * 获取系统基础信息的类
 * 应用场景：User-Agent
 * eg:APPID;应用版本;平台;OS版本;OS版本名称;厂商;机型;分辨率(宽*高);渠道标识;网络;
 * 10001;2.2.0;Android;4.2.2;N7100XXUEMI6BYTuifei;samsung;GT-I9300;480*800;D0001;WIFI;
 */
public class SysInfoUtil {
    public SysInfoUtil() {
    }

    public static String getUserAgent(Context context, String appId) {
        Resources res = context.getResources();
        Configuration cfg = res.getConfiguration();
        Locale locale = cfg.locale;
        StringBuilder userAgent = new StringBuilder();
        userAgent.append(appId);
        userAgent.append(";");
        userAgent.append(getAppVersionName(context));
        userAgent.append(";");
        userAgent.append(SourceType);
        userAgent.append(";");
        userAgent.append(getOSVersionName());
        userAgent.append(";");
        userAgent.append(getOSVersionDisplayName());
        userAgent.append(";");
        userAgent.append(getBrandName());
        userAgent.append(";");
        userAgent.append(getMachineName());
        userAgent.append(";");
        userAgent.append(getPhoneSize(context));
        userAgent.append(";");
        userAgent.append(getAppSource(context, APP_SOURCE));
        userAgent.append(";");
        userAgent.append(getNetType(context));
        userAgent.append(";");
        userAgent.append(locale.toString());
        userAgent.append(";");
        LogUtil.i("User-Agent:",userAgent.toString());
        return userAgent.toString();
    }

    //应用版本
    public static String getAppVersionName(final Context context) {
        String strAppVersionName = "未知的版本名";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            strAppVersionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return strAppVersionName;
    }
    //平台
    public static final String SourceType = "Android";


    //OS版本
    public static String getOSVersionName() {
        return android.os.Build.VERSION.RELEASE;
    }
    //OS版本名称
    public static String getOSVersionDisplayName() {
        return android.os.Build.DISPLAY;
    }
    //厂商
    public static String getBrandName() {
        return android.os.Build.BRAND;
    }
    //机型
    public static String getMachineName() {
        return android.os.Build.MODEL;
    }
    //分辨率
    public static String getPhoneSize(final Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
        return screenWidth + "*" + screenHeight;
    }
    //渠道标识
    public static final String APP_SOURCE = "AppSource";
    public static String getAppSource(Context context, String metaName) {
        String result = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if(appInfo.metaData != null){
                result = appInfo.metaData.getString(metaName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    //网络
    public static String getNetType(final Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        ;
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        // networkInfo.getDetailedState();//获取详细状态。
        // networkInfo.getExtraInfo();//获取附加信息。
        // networkInfo.getReason();//获取连接失败的原因。
        // networkInfo.getType();//获取网络类型(一般为移动或Wi-Fi)。
        // networkInfo.getTypeName();//获取网络类型名称(一般取值“WIFI”或“MOBILE”)。
        // networkInfo.isAvailable();//判断该网络是否可用。
        // networkInfo.isConnected();//判断是否已经连接。
        // networkInfo.isConnectedOrConnecting();//：判断是否已经连接或正在连接。
        // networkInfo.isFailover();//：判断是否连接失败。
        // networkInfo.isRoaming();//：判断是否漫游
        return networkInfo.getTypeName();
    }
}
