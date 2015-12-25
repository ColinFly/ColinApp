package com.example.colin.retrofit_test2;


import com.example.colin.Utils.LogUtil;
import com.example.colin.event.LoginEvent;
import com.example.colin.model.AuthModel;


import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.Retrofit;


/**
 * Created by colin on 15-12-25.
 */
public class LoginBiz extends BaseBiz {
    public LoginBiz login() {
        mHetApi.login(mParams).clone().enqueue(new Callback<Response<AuthModel>>() {
            @Override
            public void onResponse(retrofit.Response<Response<AuthModel>> response, Retrofit retrofit) {
                EventBus.getDefault().post(new LoginEvent("getAuthModelSuccess", response.body().getData()));
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.e(t.getMessage());
            }
        });
        return this;
    }


}
