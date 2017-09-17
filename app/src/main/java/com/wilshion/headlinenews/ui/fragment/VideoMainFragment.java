package com.wilshion.headlinenews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.constant.Constant;
import com.wilshion.headlinenews.model.bean.Channel;
import com.wilshion.headlinenews.ui.adapter.ChannelPageAdapter;
import com.wilshion.headlinenews.ui.base.BaseFragment;
import com.wilshion.headlinenews.ui.fragment.home.NewsListFragment;
import com.wilshion.utillib.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import me.weyye.library.colortrackview.ColorTrackTabLayout;

/**
 * Created by Wilshion on 2017/8/12 21:30.
 * [description : 西瓜视频]
 * [version : 1.0]
 */
public class VideoMainFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    private ColorTrackTabLayout mTabLayout;
    private ImageView mSearchIv;
    private ViewPager mViewPager;

    private List<Channel> mChannelList = new ArrayList<>();
    private List<NewsListFragment> mChannelFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initViews() {
//        StatusBarUtil.immersive(getActivity());
        initCtrls();
        initChannelData();
        initFragments();
        initContentPager();
    }

    private void initCtrls() {
        mTabLayout = (ColorTrackTabLayout) findViewById(R.id.id_tab_channel_layout);
        mSearchIv = (ImageView) findViewById(R.id.id_search_iv);
        mViewPager = (ViewPager) findViewById(R.id.id_content_vp);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(this);

        mTabLayout.setTabPaddingLeftAndRight(SizeUtils.dp2px(10),SizeUtils.dp2px(10));
        mTabLayout.setSelectedTabIndicatorHeight(0);

        mSearchIv.setOnClickListener(this);
    }

    private void initChannelData() {
        String[] channels = getResources().getStringArray(R.array.channel_video);
        String[] channelCodes = getResources().getStringArray(R.array.channel_code_video);
        for (int i = 0; i < channelCodes.length; i++) {
            String title = channels[i];
            String code = channelCodes[i];
            mChannelList.add(new Channel(title, code));
        }
    }

    private void initFragments(){
        for (Channel channel:mChannelList) {
            NewsListFragment newsFragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.CHANNEL_CODE, channel.channelCode);
            bundle.putBoolean(NewsListFragment.INTENT_PARAM_VIDEO, true);//是否是视频列表页面,]true
            newsFragment.setArguments(bundle);
            mChannelFragments.add(newsFragment);//添加到集合中
        }
    }

    private void initContentPager() {
        ChannelPageAdapter adapter = new ChannelPageAdapter(getChildFragmentManager(),
                mChannelFragments, mChannelList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mChannelFragments.size());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        
    }
}
