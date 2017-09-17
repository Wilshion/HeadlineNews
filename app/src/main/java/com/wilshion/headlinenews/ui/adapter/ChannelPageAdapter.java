package com.wilshion.headlinenews.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wilshion.headlinenews.model.bean.Channel;
import com.wilshion.headlinenews.ui.fragment.home.NewsListFragment;
import com.wilshion.utillib.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2017/8/13 15:51.
 * [description : ]
 * [version : 1.0]
 */
public class ChannelPageAdapter extends FragmentStatePagerAdapter {
    private List<NewsListFragment> mFragments;
    private List<Channel> mChannels;

    public ChannelPageAdapter(FragmentManager fm, List<NewsListFragment> fragments, List<Channel> channels) {
        super(fm);
        mFragments = fragments == null ? new ArrayList<>() : fragments;
        mChannels = channels == null ? new ArrayList<>() : channels;
    }

//    @Override
//    public Fragment getItem(int position) {
//        return mFragments.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return mFragments.size();
//    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).title;
    }

    public ChannelPageAdapter(FragmentManager fm, List<Channel> channels) {
        super(fm);
        mChannels = channels;
        mFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
       
        if (mFragments.size() > position){
            LogUtils.e("mFragments.get(position) " + position);
            return mFragments.get(position);
        }
        else {
            LogUtils.e("NewsListFragment.newInstance " + position);
            NewsListFragment newsListFragment = NewsListFragment.newInstance(mChannels.get(position));
            mFragments.add(newsListFragment);
            return newsListFragment;
        }
    }

    @Override
    public int getCount() {
        return mChannels.size();
    }
}
