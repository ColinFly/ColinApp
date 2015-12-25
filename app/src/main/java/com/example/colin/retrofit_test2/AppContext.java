package com.example.colin.retrofit_test2;

import android.content.Context;

import com.example.colin.Utils.ACache;
import com.example.colin.Utils.LogUtil;
import com.example.colin.event.LoginEvent;
import com.example.colin.model.AuthModel;

import de.greenrobot.event.EventBus;

/**
 * Created by colin on 15-12-22.
 */
public class AppContext {
    //App的全局上下文
    private static Context mAppContext;
    private static AppContext mInstance;
    private volatile AuthModel mAuthModel;
//    private volatile UserModel userModel;
//    private volatile DeviceModel deviceModel;
    private static String mAppId;
    private static String mAppSecret;
    //全局的缓存类
    private static ACache mACache;
//    private ITokenExpired mLogout;
//    private ServiceManager mServiceManager;
    private AppContext() {
    }

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
        EventBus.getDefault().register(this);
        mAppContext = context.getApplicationContext();
        mAppId = appId;
        mAppSecret = appSecret;
        mACache = ACache.get(context);
        if (mACache.getAsObject("AuthModel") != null) {
            mAuthModel = (AuthModel) mACache.getAsObject("AuthModel");
            LogUtil.i("存储的AuthModel", mAuthModel.toString());
        }
    }

    public void onEventMainThread(LoginEvent event) {
        switch (event.getMsg()) {
            case "getAuthModelSuccess":
                //写入缓存
                mACache.put("AuthModel",event.getAuthModel());
                //更新当前的mAuthModel
                mAuthModel = event.getAuthModel();
                break;
        }
    }

    public static ACache getACache() {
        return mACache;
    }


    public static Context getContext() {
        return mAppContext;
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

}
