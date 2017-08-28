package com.wilshion.headlinenews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.constant.Constant;
import com.wilshion.headlinenews.model.bean.Channel;
import com.wilshion.headlinenews.model.sp.ChannelHelper;
import com.wilshion.headlinenews.ui.adapter.ChannelPageAdapter;
import com.wilshion.headlinenews.ui.base.BaseFragment;
import com.wilshion.headlinenews.ui.fragment.home.NewsListFragment;
import com.wilshion.utillib.util.EmptyUtils;
import com.wilshion.utillib.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.weyye.library.colortrackview.ColorTrackTabLayout;

/**
 * Created by Wilshion on 2017/8/12 21:29.
 * [description : 首页]
 * [version : 1.0]
 */
public class HomeMainFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    private TextView mSearchTv;
    private ColorTrackTabLayout mTabLayout;
    private ImageView mAddTabIv;
    private ViewPager mViewPager;
    private List<Channel> mSelectedChannels = new ArrayList<>();
    private List<Channel> mUnSelectedChannels = new ArrayList<>();
    private List<NewsListFragment> mChannelFragments = new ArrayList<>();
    private Gson mGson = new Gson();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_main;
    }

    @Override
    protected void initViews() {
        initCtrls();
        initChannelTabs();
        initChannelFragments();
        initContentPager();
    }

    private void initCtrls() {
        mSearchTv = (TextView) findViewById(R.id.id_search_tv);
        mAddTabIv = (ImageView) findViewById(R.id.id_add_channel_iv);
        mTabLayout = (ColorTrackTabLayout) findViewById(R.id.id_tab_channel_layout);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mViewPager = (ViewPager) findViewById(R.id.id_content_vp);
        mTabLayout.setupWithViewPager(mViewPager);
        
        mSearchTv.setOnClickListener(this);
        mAddTabIv.setOnClickListener(this);
        mTabLayout.addOnTabSelectedListener(this);
//        StatusBarUtil.setPadding(getActivity(),mSearchTv);
        StatusBarUtil.setPaddingSmart(getActivity(),findViewById(R.id.id_statusbar));
        StatusBarUtil.immersive(getActivity());
    }

    private void initChannelTabs() {
        String selectedChannelJson = ChannelHelper.getSelectedChannels();
        String unselectChannel = ChannelHelper.getUnSelectedChannels();
        if (TextUtils.isEmpty(selectedChannelJson) || TextUtils.isEmpty(unselectChannel)) {
            //本地没有title
            String[] channels = getResources().getStringArray(R.array.channel);
            String[] channelCodes = getResources().getStringArray(R.array.channel_code);
            //默认添加了全部频道
            for (int i = 0; i < channelCodes.length; i++) {
                String title = channels[i];
                String code = channelCodes[i];
                if (!EmptyUtils.isEmpty(code))
                    mSelectedChannels.add(new Channel(title, code));
            }
            selectedChannelJson = mGson.toJson(mSelectedChannels);//将集合转换成json字符串
            logD("selectedChannelJson:" + selectedChannelJson);
            ChannelHelper.setSelectedChannel(selectedChannelJson);
        } else {
            //之前添加过
            List<Channel> selectedChannel = mGson.fromJson(selectedChannelJson, new TypeToken<List<Channel>>() {
            }.getType());
            List<Channel> unselectedChannel = mGson.fromJson(unselectChannel, new TypeToken<List<Channel>>() {
            }.getType());
            mSelectedChannels.addAll(selectedChannel);
            mUnSelectedChannels.addAll(unselectedChannel);
        }
    }

    private void initChannelFragments() {
        String[] channelCodes = getResources().getStringArray(R.array.channel_code);
        for (Channel channel : mSelectedChannels) {
            NewsListFragment newsFragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.CHANNEL_CODE, channel.channelCode);
            newsFragment.setArguments(bundle);
            mChannelFragments.add(newsFragment);//添加到集合中
        }
    }

    private void initContentPager() {
        ChannelPageAdapter adapter = new ChannelPageAdapter(getChildFragmentManager(), mChannelFragments, mSelectedChannels);
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
