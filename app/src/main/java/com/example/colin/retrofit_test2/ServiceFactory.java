package com.example.colin.retrofit_test2;

import com.example.colin.constant.Urls;
import com.example.colin.Utils.SysInfoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;


import java.io.IOException;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by colin on 15-12-18.
 */
public class ServiceFactory {
    /**
     * @return 解决Https不能访问的问题
     */
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static Gson gson = new GsonBuilder()
//            .registerTypeAdapterFactory(new ResponseTypeAdapterFactory())
//            .registerTypeAdapter(AuthModel.class, new MyDeserializer<>(AuthModel.class, "data"))
            .create();

    //用来自定义json数据的解析格式
    static class MyDeserializer<T> implements JsonDeserializer<T> {
        private Class<T> mClass;
        private String mKey;

        public MyDeserializer(Class<T> targetClass, String key) {
            mClass = targetClass;
            mKey = key;
        }

        @Override
        public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonElement content = json.getAsJsonObject().get(mKey);
            // Deserialize it. You use a new instance of Gson to avoid infinite recursion to this deserializer
            return new Gson().fromJson(content, mClass);
        }
    }

//适应post与get请求
    public static <T> T createRetrofitService(final Class<T> clazz) {
        //创建okhHttp客户端
        OkHttpClient httpClient = getUnsafeOkHttpClient();
        //创建网络插值器加入头部信息
        httpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().
                        addHeader("User-Agent", SysInfoUtil.getUserAgent(AppContext.getContext(), AppContext.getAppId()))
                        .build();
                return chain.proceed(request);
            }
        });

        //Log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以设置日志的输出部分
        httpClient.interceptors().add(interceptor);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.SERVER_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(clazz);
    }
}
