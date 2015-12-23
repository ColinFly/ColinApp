package com.example.colin.retrofit_test2;

import com.example.colin.Constant.Urls;
import com.example.colin.model.UserModel;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by colin on 15-12-18.
 */
public interface HetApi {

    //用户登录,请求成功则返回AuthModel
    @FormUrlEncoded
    @POST(Urls.Login.LOGIN)
    Observable<ResponseModel<AuthModel>> login(@FieldMap TreeMap<String,String > params);

    @GET(Urls.User.GET)
    Call<ResponseModel<UserModel>> getUser(@QueryMap TreeMap<String, String> params);

}
