package com.wilshion.headlinenews.model.bean;

/**
 * Created by Wilshion on 2017/9/14 17:28.
 * [description : ]
 * [version : 1.0]
 */
public class NewsDetail {


    /**
     * detail_source : 田视界
     * media_user : {"no_display_pgc_icon":false,"avatar_url":"http://p3.pstatp.com/thumb/1a6b000794d3258fbde3","id":"1556729288620034","screen_name":"田视界"}
     * publish_time : 1499002542
     * title : 沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画
     * url : http://toutiao.com/group/6438058115183198465/
     * is_original : true
     * is_pgc_article : true
     * content : "<p> <p><img src="http://p1.pstatp.com/large/2c32000483358e87440c" img_width="2449" img_height="1632" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p>2017沈阳夏季国际车展第4日，漂亮姐妹花清凉呈现人体彩绘引围观，一名中年男子连续两天围观人体彩绘，最终获得机会动用画笔在模特身上绘画，该男子非常高兴。</p> <p><img src="http://p1.pstatp.com/large/2c280002e061eb35bd8d" img_width="2449" img_height="1632" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> <p><img src="http://p1.pstatp.com/large/2c32000483a684f8b750" img_width="2449" img_height="1632" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> <p><img src="http://p3.pstatp.com/large/2c290003147ff8139287" img_width="2449" img_height="1632" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> <p><img src="http://p3.pstatp.com/large/2c310002c48b667e3cec" img_width="2449" img_height="1632" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> <p><img src="http://p3.pstatp.com/large/2c2d00031cfe5fd765c0" img_width="2449" img_height="1632" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> <p><img src="http://p1.pstatp.com/large/2c330003d5d694a22f3c" img_width="2449" img_height="1632" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> <p><img src="http://p3.pstatp.com/large/2c2d00031d49977645fc" img_width="2449" img_height="1632" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> <p><img src="http://p3.pstatp.com/large/2c32000484d2ee87f9d0" img_width="2472" img_height="1617" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> <p><img src="http://p1.pstatp.com/large/2c310002c557197549d3" img_width="2416" img_height="1655" inline="0" alt="沈阳一男子连续两天上车展围观“人体彩绘” 终获机会动手绘画" onerror="javascript:errorimg.call(this);"></p><p></p> </p>"
     * source : 田视界
     * comment_count : 19
     * video_play_count : 0
     */

    public String detail_source;
    public MediaUserBean media_user;
    public int publish_time;
    public String title;
    public String url;
    public boolean is_original;
    public boolean is_pgc_article;
    public String content;
    public String source;
    public int comment_count;
    public int video_play_count;


    public static class MediaUserBean {
        /**
         * no_display_pgc_icon : false
         * avatar_url : http://p3.pstatp.com/thumb/1a6b000794d3258fbde3
         * id : 1556729288620034
         * screen_name : 田视界
         */

        public boolean no_display_pgc_icon;
        public String avatar_url;
        public String id;
        public String screen_name;
    }
}
