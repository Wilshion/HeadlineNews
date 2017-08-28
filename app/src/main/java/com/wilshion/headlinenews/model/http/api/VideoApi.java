package com.wilshion.headlinenews.model.http.api;

import com.wilshion.headlinenews.model.http.response.TestResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Wilshion on 2017/8/7 11:40.
 * [description : ]
 * [version : 1.0]
 */
public interface VideoApi {
    public static final String BASE_SERVER_URL = "http://jdcl-app-test.ztehealth.com/health/MyRegistion/";

    @GET("queryChatAccountBySupId")
    Observable<TestResponse> queryChatAccountBySupId(@Query("supId") int supId);

}
