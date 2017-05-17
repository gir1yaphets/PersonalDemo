package com.example.copengxiaolue.personaldemo.net.api;

import com.example.copengxiaolue.personaldemo.model.GankResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by copengxiaolue on 2017/05/17.
 */

public interface GankApi {

    @GET("data/{category}/{count}/{page}")
    Observable<GankResult> getGankResult(@Path("category") String category, @Path("count") int count, @Path("page") int page);
}
