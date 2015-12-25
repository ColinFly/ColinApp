package com.example.colin.retrofit_test2;

import com.example.colin.Utils.LogUtil;
import com.example.colin.model.UserModel;

import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by colin on 15-12-25.
 */
public class UserBiz extends BaseBiz {

    public void getUser() {
        mHetApi.getUser(mParams).clone().enqueue(new Callback<Response<UserModel>>() {
            @Override
            public void onResponse(retrofit.Response<Response<UserModel>> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.e(t.getMessage());
            }
        });
    }
}
