package com.wilshion.headlinenews.model.sp;

import com.wilshion.utillib.util.SPUtils;

/**
 * Created by Wilshion on 2017/8/13 14:36.
 * [description : 用户保存的 频道]
 * [version : 1.0]
 */
public class ChannelHelper {
    private static final String SP_NAME = "channel";

    private static final String SELECTED_CHANNEL = "selected_channel";
    private static final String UNSELECTED_CHANNEL = "unselected_channel";
    private static final String LAST_UPDATE_TIME = "last_update_time";

    public static String getSelectedChannels() {
        return getSpUtil().getString(SELECTED_CHANNEL);
    }

    public static String getUnSelectedChannels() {
        return getSpUtil().getString(UNSELECTED_CHANNEL);
    }

    public static long getLastUpdateTime(String channelCode){
       return getSpUtil().getLong(channelCode);
    }
    
    public static void setSelectedChannel(String string){
        getSpUtil().put(SELECTED_CHANNEL,string);
    }
    
    public static void setUnselectedChannel(String string){
        getSpUtil().put(SELECTED_CHANNEL,string);
    }

    public static void setLastUpdateTime(String channelCode,long updateTime){
        getSpUtil().put(channelCode,updateTime);
    }
    
    private static SPUtils getSpUtil(){
        return SPUtils.getInstance(SP_NAME);
    }
}
