package com.android.yangke.java.m.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : yangke on 2021/3/20
 * weChat : ACE_5200125
 * email  : 211yangke@sina.com
 * desc   : Retrofit管理者，对Retrofit的封装，如果有多个Retrofit都应在此进行构建
 */
public class RetrofitManager {
    private static Retrofit retrofit;

    /**
     * @return 获取Retrofit对象
     */
    public static synchronized Retrofit getRetrofit() {
        if (null == retrofit) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder
                    .addInterceptor(interceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS);

            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(UrlManager.BASE_URL)
                    .client(okBuilder.build())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofit = retrofitBuilder.build();
        }
        return retrofit;
    }
}