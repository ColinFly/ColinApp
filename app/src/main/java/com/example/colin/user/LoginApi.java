package com.example.colin.user;

import com.example.colin.retrofit_test2.BaseApi;

/**
 * Created by colin on 15-12-23.
 */
public class LoginApi {
    public static void login(String account,String pwd) {
        BaseApi.getInstance().login(account,pwd);
    }
}
