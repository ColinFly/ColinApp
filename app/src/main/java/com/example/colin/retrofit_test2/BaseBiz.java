package com.example.colin.retrofit_test2;

import java.util.TreeMap;

/**
 * Created by colin on 15-12-25.
 */
public abstract class BaseBiz {
    protected HetApi mHetApi;
    protected TreeMap<String, String> mParams;

    public void handle(HetApi hetApi, TreeMap<String, String> params) {
        mHetApi = hetApi;
        mParams = params;
    }
}
