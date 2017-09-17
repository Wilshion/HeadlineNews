package com.wilshion.headlinenews.model.http;

import com.wilshion.headlinenews.BuildConfig;
import com.wilshion.headlinenews.constant.ConstantPath;
import com.wilshion.headlinenews.model.http.api.HomeApi;
import com.wilshion.headlinenews.model.http.api.VideoApi;
import com.wilshion.utillib.util.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wilshion on 2017/8/7 11:41.
 * [description : 网络请求类]
 * [version : 1.0]
 */
public class HNHttpHelper {
    private static HNHttpHelper mInstance;
    private OkHttpClient okHttpClient;

    private HomeApi mHomeApi;
    private VideoApi mVideoApi;

    public static HNHttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (HNHttpHelper.class) {
                if (mInstance == null)
                    mInstance = new HNHttpHelper();
            }
        }
        return mInstance;
    }

    private HNHttpHelper() {
        initOkHttp();
    }

    public VideoApi getVideoApi() {
        if (mVideoApi == null) {
            synchronized (HNHttpHelper.class) {
                if (mVideoApi == null) {
                    mVideoApi = getApiService(VideoApi.BASE_SERVER_URL, VideoApi.class);
                }
            }
        }
        return mVideoApi;
    }

    public HomeApi getHomeApi() {
        if (mHomeApi == null) {
            synchronized (HNHttpHelper.class) {
                if (mHomeApi == null) {
                    mHomeApi = getApiService(HomeApi.BASE_SERVER_URL, HomeApi.class);
                }
            }
        }
        return mHomeApi;
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(ConstantPath.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
//        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
//                        .addHeader("apikey",Constants.KEY_API)
//                        .build();
//                return chain.proceed(request);
//            }
//        }
//        设置统一的请求头部参数
//        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private <T> T getApiService(String baseUrl, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }
}
