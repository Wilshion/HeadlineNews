package com.wilshion.headlinenews;

import android.app.Application;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wilshion.headlinenews.receiver.ActivityLifecycleCallBack;
import com.wilshion.utillib.util.Utils;


/**
 * Created by Wilshion on 2017/8/5 21:39.
 * [description : 主程序]
 * [version : 1.0]
 */
public class HeadlineNewsApplication extends Application {
    private static HeadlineNewsApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallBack());
        /** 初始化 工具类 */
        Utils.init(this);
    }

    public static HeadlineNewsApplication getInstance() {
        return mInstance;
    }
    
    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            ClassicsHeader header = new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
//            header.setPrimaryColors(ContextCompat.getColor(context, R.color.darkgray),
//                    ContextCompat.getColor(context, android.R.color.white));
            return header;
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            ClassicsFooter footer = new ClassicsFooter(context);
            footer.setBackgroundResource(android.R.color.white);
            footer.setSpinnerStyle(SpinnerStyle.Translate);
            return footer;
        });
    }
}
