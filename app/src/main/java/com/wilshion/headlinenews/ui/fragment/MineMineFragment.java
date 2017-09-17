package com.wilshion.headlinenews.ui.fragment;

import android.view.View;
import android.widget.Button;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.ui.base.BaseFragment;
import com.wilshion.headlinenews.util.CompoundDrawableUtil;


/**
 * Created by Wilshion on 2017/8/12 21:31.
 * [description : 我的]
 * [version : 1.0]
 */
public class MineMineFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {

        /** 1、头像区域 */

        /** 2、动态粉丝区域*/

        /** 3、收藏、历史、夜间 区域*/
        Button collectBtn = (Button) findViewById(R.id.id_my_collect_btn);
        Button historyBtn = (Button) findViewById(R.id.id_my_history_btn);
        Button nightModeBtn = (Button) findViewById(R.id.id_night_mode_btn);

        CompoundDrawableUtil.changeCompoundDrawableSize(collectBtn, 1, 20);
        CompoundDrawableUtil.changeCompoundDrawableSize(historyBtn, 1, 20);
        CompoundDrawableUtil.changeCompoundDrawableSize(nightModeBtn, 1, 20);

        collectBtn.setOnClickListener(this);
        historyBtn.setOnClickListener(this);
        nightModeBtn.setOnClickListener(this);

        /** 4、cells */

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.id_personal_info_ll:
                /** 个人头像、昵称*/
            case R.id.id_dynamic_count_ll:
                /** 动态*/
                break;
            case R.id.id_fans_ll:
                /** 粉丝*/
                break;
            case R.id.id_visitors_ll:
                /** 7天访客*/
                break;
            case R.id.id_my_collect_btn:
                /** 收藏*/
                break;
            case R.id.id_my_history_btn:
                /** 历史*/
                break;
            case R.id.id_night_mode_btn:
                /** 夜间*/
                break;
            case R.id.id_my_attention_ll:
                /** 我的关注*/
                break;
            case R.id.id_msg_notification_ll:
                /** 消息通知*/
                break;
            case R.id.id_headline_market_ll:
                /** 头条商城*/
                break;
            case R.id.id_jd_supply_ll:
                /** 京东特工*/
                break;
            case R.id.id_breaking_news_ll:
                /** 我要爆料*/
                break;
            case R.id.id_user_feedback_ll:
                /** 用户反馈*/
                break;
            case R.id.id_system_settings_ll:
                /** 系统设置*/
                break;

        }
    }
}
