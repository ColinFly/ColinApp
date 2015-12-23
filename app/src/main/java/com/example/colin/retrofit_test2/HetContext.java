package com.example.colin.retrofit_test2;

import android.app.Application;

/**
 * Created by colin on 15-12-22.
 */
public class HetContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().init(getApplicationContext(),"10104","bea828064741421e8ded724e90a7501a");
    }
}
