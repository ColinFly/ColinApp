package com.example.colin.retrofit_test2;

/**
 * Created by colin on 15-12-18.
 * 网络请求返回的bean
 */
public class Contributor {
    public final String login;
    public final int contributions;
    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }
    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
