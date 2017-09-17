package com.wilshion.headlinenews.model.http.api;

import com.wilshion.headlinenews.model.bean.VideoModel;
import com.wilshion.headlinenews.model.http.response.CommentResponse;
import com.wilshion.headlinenews.model.bean.NewsDetail;
import com.wilshion.headlinenews.model.http.response.NewsResponse;
import com.wilshion.headlinenews.model.http.response.ResultResponse;
import com.wilshion.headlinenews.model.http.response.VideoPathResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Wilshion on 2017/8/7 11:38.
 * [description : ]
 * [version : 1.0]
 */
public interface HomeApi {
    /**
     * 接口根地址
     */
    public static final String BASE_SERVER_URL = "http://is.snssdk.com/";

    String GET_ARTICLE_LIST = "api/news/feed/v54/?refer=1&count=20&loc_mode=4&device_id=34960436458";
    String GET_COMMENT_LIST = "article/v2/tab_comments/";


    /**
     * 获取新闻列表
     * @param category          频道
     * @param lastTime          上次更新时间
     * @param currentTime       当前时间
     * @return
     */
    @GET(GET_ARTICLE_LIST)
    Observable<NewsResponse> getNewsList(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);

    /**
     * 获取新闻详情
     */
    @GET
    Observable<ResultResponse<NewsDetail>> getNewsDetail(@Url String url);

    /**
     * 获取评论列表数据
     *
     * @param groupId
     * @param itemId
     * @param offset
     * @param count
     * @return
     */
    @GET(GET_COMMENT_LIST)
    Observable<CommentResponse> getComment(@Query("group_id") String groupId, @Query("item_id") String itemId, 
                                           @Query("offset") String offset, @Query("count") String count);

    /**
     * 获取视频数据json
     *
     * @param url
     * @return
     */
    @GET
    Observable<ResultResponse<VideoModel>> getVideoData(@Url String url);

    @Headers({
            "Content-Type:application/x-www-form-urlencoded; charset=UTF-8",
            "Origin:http://toutiao.iiilab.com",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"

    })
    @POST("http://service.iiilab.com/video/toutiao")
    Observable<VideoPathResponse> getVideoPath(@Query("link") String link, @Query("r") String r, @Query("s") String s);
}
