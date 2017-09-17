package com.wilshion.headlinenews.model.bean;

import java.util.List;

/**
 * Created by Wilshion on 2017/9/14 17:09.
 * [description : ]
 * [version : 1.0]
 */
public class Comment {

    /**
     * id : 1578576534296589
     * text : 自己不自食其力，老是想靠别人的技术活着，这种国家也是辣鸡
     * reply_count : 0
     * reply_list : []
     * digg_count : 12
     * bury_count : 0
     * create_time : 1505447897
     * score : 0.38619282841682434
     * user_id : 53040525704
     * user_name : Sye141811875
     * user_profile_image_url : http://p3.pstatp.com/thumb/289e000f1d581a5e1fee
     * user_verified : false
     * is_following : 0
     * is_followed : 0
     * is_blocking : 0
     * is_blocked : 0
     * is_pgc_author : 0
     * author_badge : []
     * verified_reason : 
     * user_bury : 0
     * user_digg : 0
     * user_relation : 0
     * user_auth_info : 
     * media_info : {"name":"","avatar_url":""}
     * platform : feifei
     */

    public long id;
    public String text;
    public int reply_count;
    public int digg_count;
    public int bury_count;
    public int create_time;
    public double score;
    public long user_id;
    public String user_name;
    public String user_profile_image_url;
    public boolean user_verified;
    public int is_following;
    public int is_followed;
    public int is_blocking;
    public int is_blocked;
    public int is_pgc_author;
    public String verified_reason;
    public int user_bury;
    public int user_digg;
    public int user_relation;
    public String user_auth_info;
    public MediaInfoBean media_info;
    public String platform;
    public List<?> reply_list;
    public List<?> author_badge;

  
   
    public static class MediaInfoBean {
        /**
         * name : 
         * avatar_url : 
         */

        public String name;
        public String avatar_url;

       
    }
}
