package com.wilshion.headlinenews.mvp.view;

import com.wilshion.headlinenews.mvp.contract.BaseContract;

/**
 * Created by Wilshion on 2017/8/8 14:35.
 * [description : ]
 * [version : 1.0]
 */
public interface NavigationBarView extends BaseContract.View{
    void setNavBarLeftImg(int resIcon);
    
    void setNavBarLeftImg(String imgUrl);
    
    void setNavBarTitle(String title);
    
    void setNavBarRightText(String text);
    
    void setNavBarRightImg(int resIcon);

    void setNavBarRightImg(String imgUrl);

    void onNavigationBarLeftClicked();

    void onNavigationBarTitleClicked();

    void onNavigationBarRightClicked();
}
