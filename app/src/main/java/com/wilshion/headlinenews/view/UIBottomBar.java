package com.wilshion.headlinenews.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2017/8/8 18:53.
 * [description : ]
 * [version : 1.0]
 */
public class UIBottomBar extends LinearLayout implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private List<UIBottomBarItem> mUIBottomBarItems;
    private ViewPager mViewPager;
    private int mSelectedItem;
    private OnBottomBarItemClickListener mOnBottomBarItemClickListener;

    public UIBottomBar(Context context) {
        this(context, null);
    }

    public UIBottomBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public UIBottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (mUIBottomBarItems == null) {
            mUIBottomBarItems = new ArrayList<>();
        }
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void addBottomBarItem(UIBottomBarItem bottomBarItem) {
        addView(bottomBarItem);
        layouSubViews();
    }

    public void addBottomBarItems(List<UIBottomBarItem> bottomBarItems) {
        for (UIBottomBarItem item : bottomBarItems) {
            addBottomBarItem(item);
        }
    }

    public List<UIBottomBarItem> getUIBottomBarItems() {
        return mUIBottomBarItems;
    }

    public void setUpViewPager(ViewPager viewPager) {
        if (viewPager == null)
            throw new IllegalArgumentException("setUpViewPager（） 不可传入空");
        this.mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * 设置自身以及子控件的权重，并且设置子控件id，设置监听事件,此方法会被多次调用
     */
    private void layouSubViews() {
        int count = getChildCount();
//        LogUtils.d("当前子控件数量是 " + count);
        /** 设置总权重 */
        setWeightSum(count);
        /**遍历 子控件*/
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            if (childView instanceof UIBottomBarItem) {
                if (mUIBottomBarItems.contains(childView))
                    continue;
                mUIBottomBarItems.add((UIBottomBarItem) childView);
                childView.setTag(i);
                childView.setOnClickListener(this);
                LayoutParams lp = (LayoutParams) childView.getLayoutParams();
                lp.weight = 1;
            } else throw new IllegalArgumentException("子控件类型必须是 UIBottomBarItem");
        }
        selecteItem(mSelectedItem);
    }

    private void selecteItem(int selectedItem) {
        if (mUIBottomBarItems.size() <= selectedItem) {
//            throw new IllegalArgumentException("请检查你传的参数 selecteItem 超过范围啦");
            return;
        }
        UIBottomBarItem item = mUIBottomBarItems.get(selectedItem);
        if (!item.isSelected()) {
            UIBottomBarItem lastSelectedItem = mUIBottomBarItems.get(mSelectedItem);
            lastSelectedItem.setSelected(false);
            item.setSelected(true);
            mSelectedItem = selectedItem;

            if (mOnBottomBarItemClickListener != null)
                mOnBottomBarItemClickListener.onItemClick(this, item, selectedItem);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        LogUtils.d("--------------onLayout start--------------------");
        layouSubViews();
    }

    @Override
    public void onClick(View v) {
        int id = (int) v.getTag();
        selecteItem(id);
    }


    public void setOnBottomBarItemClickListener(OnBottomBarItemClickListener onBottomBarItemClickListener) {
        mOnBottomBarItemClickListener = onBottomBarItemClickListener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selecteItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnBottomBarItemClickListener {
        void onItemClick(UIBottomBar bottomBar, UIBottomBarItem bottomBarItem, int position);
    }
}
