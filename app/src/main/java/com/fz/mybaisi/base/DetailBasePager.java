package com.fz.mybaisi.base;

import android.content.Context;
import android.view.View;

/**
 * @FileName:com.fz.beijingnews.base.MenuDetailBasePager.java
 * @author：方志
 * @date: 2016-12-13 19:50
 * @QQ：459119626
 * @微信：15549433151
 * @function <精华详情页面>
 */

public abstract class DetailBasePager {


    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 各个孩子页面的实例
     */
    public View rootView;

    public DetailBasePager(Context context) {
        this.mContext = context;
        rootView = initView();//视图
    }

    /**
     * 孩子的视图
     *
     * @return
     */
    public abstract View initView() ;


    /**
     * 1.当需要联网的时候
     * 2.当需要绑定数据到ui的时候
     */
    public abstract void initData();
}
