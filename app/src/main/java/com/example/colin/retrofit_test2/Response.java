package com.example.colin.retrofit_test2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin on 15-12-23.
 */
public class Response<T> implements Serializable{
    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }
    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
