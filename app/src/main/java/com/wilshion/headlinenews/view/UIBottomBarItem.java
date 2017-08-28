package com.wilshion.headlinenews.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wilshion.headlinenews.R;
import com.wilshion.utillib.util.EmptyUtils;
import com.wilshion.utillib.util.ResouceUtil;

/**
 * Created by Wilshion on 2017/8/8 21:01.
 * [description : 底部 栏的item]
 * [version : 1.0]
 */
public class UIBottomBarItem extends LinearLayout {
    private final String TAG = getClass().getSimpleName();

    private final int DEFAULT_RES_ID = -1;
    private final int DEFAULT_TITLE_TEXT_SIZE = 10;


    private View mItemView;
    private ImageView mIconView;
    private TextView mTitleTv;
    private UIBadgeView mBadgeTv;


    private int mIconNormal = DEFAULT_RES_ID;
    private int mIconSelected = DEFAULT_RES_ID;
    private Drawable mIconDrawable;
    private String mText;
    /**
     * 字体大小，单位px
     */
    private int mTextSize = DEFAULT_TITLE_TEXT_SIZE;
    private int mTextColorNormal = DEFAULT_RES_ID;
    private int mTextColorSelected = DEFAULT_RES_ID;
    private ColorStateList mTextColorList;
    private int mBadgeNum;

    public UIBottomBarItem(Context context) {
        this(context, null);
    }

    public UIBottomBarItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIBottomBarItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initViews(context);
        initValues();
    }


    /**
     * 获取 xml 中的属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
//            LogUtils.d("不是从xml 中加载的");
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UIBottomBarItem);
        /** 1、图片资源相关 */
        mIconNormal = a.getResourceId(R.styleable.UIBottomBarItem_ws_item_icon_nomal, DEFAULT_RES_ID);
        mIconSelected = a.getResourceId(R.styleable.UIBottomBarItem_ws_item_icon_sel, DEFAULT_RES_ID);
        mIconDrawable = a.getDrawable(R.styleable.UIBottomBarItem_ws_item_icon);

        /** 2、标题相关*/
        mTextColorNormal = a.getColor(R.styleable.UIBottomBarItem_ws_item_text_nomal_color, DEFAULT_RES_ID);
        mTextColorSelected = a.getColor(R.styleable.UIBottomBarItem_ws_item_text_sel_color, DEFAULT_RES_ID);
        mTextColorList = a.getColorStateList(R.styleable.UIBottomBarItem_ws_item_text_color);

        mText = a.getString(R.styleable.UIBottomBarItem_ws_item_text);
        mTextSize = a.getDimensionPixelSize(R.styleable.UIBottomBarItem_ws_item_text_size,
                DEFAULT_TITLE_TEXT_SIZE);

        a.recycle();
    }

    /**
     * 检查xml 属性的合法性
     */
    private void checkValues() {
//        LogUtils.d("checkValues");
        /**1、判断icon 图片值*/
        if (mIconDrawable == null) {
            /** 1.1 用户没有在xml 中指定背景图*/
            if (mIconNormal == DEFAULT_RES_ID || mIconSelected == DEFAULT_RES_ID) {
                /** 若在没有指定背景图的情况下，依然没有 指定 正常、选中的图的话，报错*/
                throw new IllegalArgumentException("请设置 icon 的状态图，或者分开设置选中与未选中的图片");
            }
        }

        if (mTextColorList == null) {
            if (mTextColorNormal == DEFAULT_RES_ID || mTextColorSelected == DEFAULT_RES_ID) {
                throw new IllegalArgumentException("请设置 title 的颜色，或者设置选中和未选中状态的文字颜色");
            }
        }

        /** 2、检查 text */
        if (TextUtils.isEmpty(mText)) {
            throw new IllegalArgumentException("请指定 UIBottomBarItem 的标题文字");
        }
    }

    /**
     * 初始化控件
     *
     * @param context
     */
    private void initViews(Context context) {
        mItemView = View.inflate(context, R.layout.view_bottom_bar_item, this);
        mIconView = (ImageView) mItemView.findViewById(R.id.id_icon_iv);
        mTitleTv = (TextView) mItemView.findViewById(R.id.id_title_tv);
        mBadgeTv = (UIBadgeView) mItemView.findViewById(R.id.id_badge_tv);
    }

    /**
     * 设置 xml 中的属性值 给控件
     */
    private void initValues() {
        /** 1、设置 icon 的相关值*/
        if (mIconDrawable == null) {
            if (mIconNormal != DEFAULT_RES_ID && mIconSelected != DEFAULT_RES_ID)
                mIconDrawable = getStateListDrawableWithNomalImgAndSelectedImg(mIconNormal, mIconSelected);
        }
        if (mIconDrawable != null)
            mIconView.setImageDrawable(mIconDrawable);

        /** 2、设置 标题相关值 */
        if (mTextColorList == null)
            if (mTextColorNormal != DEFAULT_RES_ID && mTextColorSelected != DEFAULT_RES_ID)
                mTextColorList = getColorStateListWithNomalColorAndSelctedColor(mTextColorNormal, mTextColorSelected);
        if (mTextColorList != null)
            mTitleTv.setTextColor(mTextColorList);

        if (!EmptyUtils.isEmpty(mText))
            mTitleTv.setText(mText);
        mTitleTv.setTextSize(mTextSize);
        
        setItemBadgeNum(0);
    }


    public static class Builder {
        public static UIBottomBarItem builder(Context context) {
            UIBottomBarItem item = new UIBottomBarItem(context);
            return item;
        }
    }


    public UIBottomBarItem setItemText(String text) {
        mText = text;
        mTitleTv.setText(text);
        return this;
    }

    /**
     * 设置 tab icon 的图片颜色
     *
     * @param stateListDrawableRes R.drawable.xxx 资源文件，一般包含 选中和非选中两种状态图片
     * @return
     */
    public UIBottomBarItem setItemIcon(int stateListDrawableRes) {
        mIconDrawable = ResouceUtil.getStateListDrawable(stateListDrawableRes);
        mIconView.setImageDrawable(mIconDrawable);
        return this;
    }

    public UIBottomBarItem setItemIcon(int nomalImg, int selectedImg) {
        mIconNormal = nomalImg;
        mIconSelected = selectedImg;
        StateListDrawable stateListDrawable = getStateListDrawableWithNomalImgAndSelectedImg(nomalImg, selectedImg);
        mIconDrawable = stateListDrawable;
        mIconView.setImageDrawable(stateListDrawable);
        return this;
    }


    /**
     * 设置文字大小
     *
     * @param itemTextSize 大小   
     * @return
     */
    public UIBottomBarItem setItemTextSize(int itemTextSize) {
        mTextSize = itemTextSize;
        mTitleTv.setTextSize(mTextSize);
        return this;
    }

    /**
     * 设置 tab title 的文字颜色
     *
     * @param colorRes R.color.xxx 资源文件，一般包含 选中和非选中两种状态颜色
     * @return
     */
    public UIBottomBarItem setItemTextColor(@ColorRes int colorRes) {
        ColorStateList colorStateList = ResouceUtil.getColorStateList(colorRes);
        mTextColorList = colorStateList;
        mTitleTv.setTextColor(colorStateList);
        return this;
    }

    public UIBottomBarItem setItemTextColor(@ColorRes int colorNomal, @ColorRes int colorSelected) {
        mTextColorNormal = colorNomal;
        mTextColorSelected = colorSelected;
        ColorStateList colorStateList = getColorStateListWithNomalColorAndSelctedColor(colorNomal, colorSelected);
        mTextColorList = colorStateList;
        mTitleTv.setTextColor(colorStateList);
        return this;
    }

    /**
     * 设置角标数量
     *
     * @return
     */
    public UIBottomBarItem setItemBadgeNum(int num) {
        mBadgeNum = num;
        mBadgeTv.setCount(num);
        mBadgeTv.setVisibility(num > 0 ? VISIBLE : GONE);
        return this;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        checkValues();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);

        mIconView.setSelected(selected);
        mTitleTv.setSelected(selected);
    }


    private StateListDrawable getStateListDrawableWithNomalImgAndSelectedImg(int nomalImg, int selectedImg) {
        Drawable nomalDrawable = ContextCompat.getDrawable(getContext(), nomalImg);
        Drawable selectedDrawable = ContextCompat.getDrawable(getContext(), selectedImg);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, selectedDrawable);
        stateListDrawable.addState(new int[]{-android.R.attr.state_selected}, nomalDrawable);
        return stateListDrawable;
    }

    private ColorStateList getColorStateListWithNomalColorAndSelctedColor(@ColorRes int colorNomal
            , @ColorRes int colorSelected) {
        int nomalColor = ResouceUtil.getColor(colorNomal);
        int selectedColor = ResouceUtil.getColor(colorSelected);
        ColorStateList colorStateList = new ColorStateList(new int[][]{{android.R.attr.state_selected}, {}},
                new int[]{selectedColor, nomalColor});
        return colorStateList;
    }
}
