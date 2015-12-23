package com.example.colin.retrofit_test;


import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by colin on 15-12-18.
 */
public class ApiManager {
    //活动的接口
    public interface GitHubService {
        @GET("/users/{username}")
        User getUser(@Path("username") String username);

        @GET("/user/{username}")
        Observable<User> getUserData(@Path("username") String username);
    }

    //初始化的基础的部分
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private static final GitHubService gitHubService = retrofit.create(GitHubService.class);


    public static Observable<User> getUserData(final String user) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                //让订阅者回调
                subscriber.onNext(gitHubService.getUser(user));
            }
        }).subscribeOn(Schedulers.io());
    }

}
