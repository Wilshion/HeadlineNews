package com.wilshion.headlinenews.model.http.response;

import com.wilshion.headlinenews.model.bean.NewsData;
import com.wilshion.headlinenews.model.bean.TipBean;

import java.util.List;

/**
 * Created by Wilshion on 2017/8/7 11:48.
 * [description : ]
 * [version : 1.0]
 */
public class NewsResponse {


    /**
     * message : success
     * total_number : 20
     * has_more : true
     * login_status : 0
     * show_et_status : 0
     * post_content_hint : 分享今日新鲜事
     * has_more_to_refresh : true
     * action_to_last_stick : 0
     * feed_flag : 0
     */

    private String message;
    private int total_number;
    private boolean has_more;
    private int login_status;
    private int show_et_status;
    private String post_content_hint;
    private boolean has_more_to_refresh;
    private int action_to_last_stick;
    private int feed_flag;
    private List<NewsData> data;
    private TipBean tips;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getLogin_status() {
        return login_status;
    }

    public void setLogin_status(int login_status) {
        this.login_status = login_status;
    }

    public int getShow_et_status() {
        return show_et_status;
    }

    public void setShow_et_status(int show_et_status) {
        this.show_et_status = show_et_status;
    }

    public String getPost_content_hint() {
        return post_content_hint;
    }

    public void setPost_content_hint(String post_content_hint) {
        this.post_content_hint = post_content_hint;
    }

    public boolean isHas_more_to_refresh() {
        return has_more_to_refresh;
    }

    public void setHas_more_to_refresh(boolean has_more_to_refresh) {
        this.has_more_to_refresh = has_more_to_refresh;
    }

    public int getAction_to_last_stick() {
        return action_to_last_stick;
    }

    public void setAction_to_last_stick(int action_to_last_stick) {
        this.action_to_last_stick = action_to_last_stick;
    }

    public int getFeed_flag() {
        return feed_flag;
    }

    public void setFeed_flag(int feed_flag) {
        this.feed_flag = feed_flag;
    }

    public List<NewsData> getData() {
        return data;
    }

    public void setData(List<NewsData> data) {
        this.data = data;
    }

    public TipBean getTips() {
        return tips;
    }

    public void setTips(TipBean tips) {
        this.tips = tips;
    }
}
