package com.example.colin.retrofit_test2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin on 15-12-23.
 */
public class ResponseModel<T> implements Serializable{
    private int code=100;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
