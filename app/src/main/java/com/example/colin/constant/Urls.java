package com.example.colin.constant;

/**
 * Created by colin on 15-12-23.
 */
public class Urls {
    public static final String INNER_SERVER_HOST = "http://200.200.200.50";
    public static final String OUTER_SERVER_HOST = "https://api.clife.cn";

    public static String SERVER_HOST = OUTER_SERVER_HOST;

    private static final String ACCOUNT = "/v1/account/";
    private static final String USER = "v1/user/";

    //登录获取token
    public static class Login {
        public final static String LOGIN = ACCOUNT + "login";
    }

    //用户接口获取用户信息
    public static class User {
        public final static String GET = USER + "get";
    }
}
