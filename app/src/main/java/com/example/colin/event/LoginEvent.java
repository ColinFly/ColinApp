package com.example.colin.event;

import com.example.colin.model.AuthModel;

/**
 * Created by colin on 15-12-24.
 */
public class LoginEvent {
    private String msg;
    private AuthModel authModel;

    public LoginEvent(String msg) {
        this.msg = msg;
    }


    public LoginEvent(String msg, AuthModel authModel) {
        this.msg = msg;
        this.authModel = authModel;
    }

    public String getMsg() {
        return msg;
    }

    public AuthModel getAuthModel() {
        return authModel;
    }
}
