package com.example.colin.retrofit_test2;

import com.example.colin.constant.Urls;

/**
 * Created by colin on 15-12-25.
 */
public class UserApi {
    public static void getUserModel() {
        UserBiz userBiz=new UserBiz();
        new BaseNetwork.Builder().setUrl(Urls.User.GET).setAccessToken(true).setBiz(userBiz).build().commit();
        userBiz.getUser();
    }
}
