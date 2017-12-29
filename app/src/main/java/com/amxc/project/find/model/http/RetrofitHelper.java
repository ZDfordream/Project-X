package com.amxc.project.find.model.http;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class RetrofitHelper {

    private Context mCntext;

    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context mContext) {
        mCntext = mContext;
        init();
    }

    private void init() {
        resetApp();
    }



    /**
     * 以后可以采用如下的方式优化，直接降client和baseurl当做参数传进去
     protected Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
     return builder
     .baseUrl(url)
     .client(client)
     .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
     .addConverterFactory(GsonConverterFactory.create())
     .build();
     }
     */
    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.tianapi.com/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitService getServer() {
        return mRetrofit.create(RetrofitService.class);
    }
}
