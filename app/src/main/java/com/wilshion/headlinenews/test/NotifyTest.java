package com.wilshion.headlinenews.test;

import android.os.Handler;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.ui.MainActivity;
import com.wilshion.utillib.util.notify.NotifyUtil;


/**
 * Created by Wilshion on 2017/8/6 17:19.
 * [description : 测试]
 * [version : 1.0]
 */
public class NotifyTest {


    public static void showSimpleNotify() {
        NotifyUtil.buildSimple(100, R.mipmap.icon, "标题标题标题图表题滴滴滴", "哈哈哈哈哈哈哈呼呼呼呼呼呼", null)
                .setHeadup()
                .addBtn(R.mipmap.ic_launcher, "left", NotifyUtil.buildIntent(MainActivity.class))
                .addBtn(R.mipmap.ic_launcher, "rightdd", NotifyUtil.buildIntent(MainActivity.class))
                .show();
    }

    public static void shoBigPicNotify() {
        NotifyUtil.buildBigPic(101, R.mipmap.icon, "title", "content", "summmaujds")
                .setPicRes(R.mipmap.ic_launcher)
                .show();
    }

  public   static int progresses = 0;

    public static void showProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progresses >= 100) {
                    return;
                }
                progresses = progresses + 10;

                NotifyUtil.buildProgress(102, R.mipmap.ic_launcher, "正在下载", progresses, 100, "下载进度:%dkb/%dkb").show();//"下载进度:%d%%"
                showProgress();
            }
        }, 500);
    }

    public static void showbuilMailBox() {
        NotifyUtil.buildMailBox(104, R.mipmap.icon, "title")
                .addMsg("11111111111")
                .addMsg("33333333333333")
                .addMsg("6666666666666666666")
                .show();
    }

    public static void showMediaNotify() {
        NotifyUtil.buildMedia(105, R.mipmap.icon, "title", "content")
                .addBtn(R.mipmap.ic_launcher, "left", NotifyUtil.buildIntent(MainActivity.class))
                .addBtn(R.mipmap.ic_launcher, "center", NotifyUtil.buildIntent(MainActivity.class))
                .addBtn(R.mipmap.ic_launcher, "right", NotifyUtil.buildIntent(MainActivity.class))
                .show();
    }
}
