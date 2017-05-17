package com.example.copengxiaolue.personaldemo.net;

import com.example.copengxiaolue.personaldemo.net.api.GankApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by copengxiaolue on 2017/05/17.
 */

public class NetWork {
    private static final String BASE_URL = "http://gank.io/api/";
    private static GankApi sGankApi;

    public static GankApi getGankApi() {
        if (sGankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();
            sGankApi = retrofit.create(GankApi.class);
        }

        return sGankApi;
    }
}
