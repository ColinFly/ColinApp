package com.example.colin.retrofit_test2;

import android.util.Log;

import com.example.colin.Constant.Urls;
import com.example.colin.Utils.LogUtil;
import com.example.colin.Utils.MD5;
import com.example.colin.Utils.SecurityUtils;
import com.example.colin.model.UserModel;

import java.util.List;
import java.util.TreeMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by colin on 15-12-18.
 * 负责建立请求的类
 */
public class BaseApi {
    private static BaseApi ourInstance = new BaseApi();

    public static BaseApi getInstance() {
        return ourInstance;
    }

    private BaseApi() {
    }

    /**
     * Call是Retrofit中重要的一个概念，代表被封装成单个请求/响应的交互行为
     * 通过调用Retrofit2的execute（同步）或者enqueue（异步）方法，发送请求到网络服务器，并返回一个响应（Response）。
     * 独立的请求和响应模块
     * 从响应处理分离出请求创建
     * 每个实例只能使用一次。
     * Call可以被克隆。
     * 支持同步和异步方法。
     * 能够被取消。
     * Call<T></>
     */

    public void getContributors() {
        GitHubApi gitHubApi = ServiceFactory.createRetrofitService(GitHubApi.class, GitHubApi.gitHubBaseUrl);
        Call<List<Contributor>> call = gitHubApi.contributors("square", "retrofit");
        Call<List<Contributor>> callback1 = call.clone();
        //异步请求的方式，与之相对的是同步:execute
        callback1.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response, Retrofit retrofit) {
                Log.d("Response", "response:" + response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    //RxJava的请求方式
    public void getContributors2() {
        GitHubApi gitHubApi = ServiceFactory.createRetrofitService1(GitHubApi.class, GitHubApi.gitHubBaseUrl);
        gitHubApi.contributors2("square", "retrofit")
                .subscribeOn(Schedulers.newThread())//指定观察者代码运行的线程
                .observeOn(AndroidSchedulers.mainThread())//指定订阅者运行的线程
                .subscribe(new Action1<List<Contributor>>() {
                    @Override
                    public void call(List<Contributor> contributors) {
                        System.out.println(contributors);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    //getUserInfo
    public void getUserInfo() {
        HetApi hetApi = ServiceFactory.createRetrofitService2(HetApi.class);
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appId", AppContext.getAppId());
        params.put("accessToken", AppContext.getInstance().getAuthModel().getAccessToken());
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        Call<ResponseModel<UserModel>> call = hetApi.getUser(params).clone();
       call.enqueue(new Callback<ResponseModel<UserModel>>() {
           @Override
           public void onResponse(Response<ResponseModel<UserModel>> response, Retrofit retrofit) {
               LogUtil.i(response.body().toString());
           }

           @Override
           public void onFailure(Throwable t) {
            LogUtil.e(t.getMessage());
           }
       });

    }
    //登录
    public void login(String account, String pwd) {
        HetApi hetApi = ServiceFactory.createRetrofitService2(HetApi.class);
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appId", AppContext.getAppId());
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("account", account);
        params.put("password", SecurityUtils.encrypt4login(pwd, AppContext.getAppSecret()));
        params.put("sign", params2sign(params));
        hetApi.login(params).subscribeOn(Schedulers.newThread())//指定观察者代码运行的线程
                .observeOn(AndroidSchedulers.mainThread())//指定订阅者运行的线程
                        //对返回的结果过滤
                .filter(new Func1<ResponseModel<AuthModel>, Boolean>() {
                    @Override
                    public Boolean call(ResponseModel<AuthModel> responseModel) {
                        int code = responseModel.getCode();
                        return code == 0;
                    }
                })
                .subscribe(new Action1<ResponseModel<AuthModel>>() {
                    @Override
                    public void call(ResponseModel<AuthModel> responseModel) {
                        LogUtil.i(responseModel.toString());
                        LogUtil.i(responseModel.getCode() + "");
                        LogUtil.i(responseModel.getData().toString());
                        AppContext.getInstance().setAuthModel(responseModel.getData());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.e(throwable.getMessage());
                    }
                });
    }

    private String params2sign(TreeMap<String, String> params) {
        StringBuilder sb = new StringBuilder("POST");
        sb.append(Urls.SERVER_HOST);
        sb.append(Urls.Login.LOGIN);
        for (String s : params.keySet()) {
            sb.append(s).append("=").append(params.get(s)).append("&");
        }
        sb.append(AppContext.getAppSecret());
        LogUtil.i("sign param", sb.toString());
        return MD5.getMD5(sb.toString());
    }



}
