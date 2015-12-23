package com.example.colin.retrofit_test2;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by colin on 15-12-18.
 * 定义post或者get请求
 */
public interface GitHubApi {
    String gitHubBaseUrl = "https://api.github.com";

    @GET("/repos/{owner}/{repo}/contributors")
    // // Call 代表的是CallBack回调机制，将返回一个List<Contributor>对象
    Call<List<Contributor>> contributors( @Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/contributors")
    // Observable 代表的是RxJava的执行，将返回一个Observable对象
    Observable<List<Contributor>> contributors2(@Path("owner") String owner,@Path("repo")String repo);



}
