package com.example.colin.retrofit_test2;

import com.example.colin.Utils.SecurityUtils;
import com.example.colin.constant.Urls;

import java.util.TreeMap;

/**
 * Created by colin on 15-12-23.
 * 关注提交的参数和用哪一个接口
 */
public class LoginApi {
    //获取AuthModel
    public static void getAuthModel(String account,String pwd) {
        //只负责构建
        TreeMap<String,String> params=new TreeMap<>();
        params.put("account", account);
        params.put("password", SecurityUtils.encrypt4login(pwd, AppContext.getAppSecret()));

        LoginBiz loginBiz=new LoginBiz();
        new BaseNetwork.Builder().setSign(true).setUrl(Urls.Login.LOGIN).setParams(params).setBiz(loginBiz)
                .build().commit();
        loginBiz.login();
    }


}
