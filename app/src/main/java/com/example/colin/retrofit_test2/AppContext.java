package com.example.colin.retrofit_test2;

import android.content.Context;

/**
 * Created by colin on 15-12-22.
 */
public class AppContext {
    private static Context mAppContext;
    private static AppContext mInstance;
    private volatile AuthModel mAuthModel;
//    private volatile UserModel userModel;
//    private volatile DeviceModel deviceModel;
    private static String mAppId;
    private static String mAppSecret;
//    private ITokenExpired mLogout;
//    private ServiceManager mServiceManager;

    public static AppContext getInstance() {
        if (mInstance == null) {
            synchronized (AppContext.class) {
                if (mInstance == null) {
                    mInstance = new AppContext();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context, String appId, String appSecret) {
        mAppContext = context.getApplicationContext();
        mAppId = appId;
        mAppSecret = appSecret;
    }

    public AuthModel getAuthModel() {
        return mAuthModel;
    }

    public void setAuthModel(AuthModel mAuthModel) {
        this.mAuthModel = mAuthModel;
    }

    public static String getAppId() {
        return mAppId;
    }

    public static void setAppId(String mAppId) {
        AppContext.mAppId = mAppId;
    }

    public static String getAppSecret() {
        return mAppSecret;
    }

    public static void setAppSecret(String mAppSecret) {
        AppContext.mAppSecret = mAppSecret;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
