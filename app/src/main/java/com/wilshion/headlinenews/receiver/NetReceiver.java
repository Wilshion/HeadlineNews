package com.wilshion.headlinenews.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wilshion.utillib.util.NetworkUtils;
import com.wilshion.utillib.util.NetworkUtils.NetworkType;
import java.util.ArrayList;

/**
 * Created by Wilshion on 2017/8/6 19:14.
 * [description : 监听网络 的广播]
 * [version : 1.0]
 */
public class NetReceiver extends BroadcastReceiver {
    public static final String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static ArrayList<OnNetWorkChangeListener> mListeners;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NET_CHANGE_ACTION)) {
            NetworkType netType = NetworkUtils.getNetworkType();
            if (mListeners.size() > 0)
            /** 挨个通知*/
                for (OnNetWorkChangeListener handler : mListeners) {
                    handler.onNetWorkChanged(netType);
                }
        }
    }

    public void addListener(OnNetWorkChangeListener listener) {
        if (mListeners == null)
            mListeners = new ArrayList<>();
        mListeners.add(listener);
    }

    public interface OnNetWorkChangeListener {
        void onNetWorkChanged(NetworkType netType);
    }
}
