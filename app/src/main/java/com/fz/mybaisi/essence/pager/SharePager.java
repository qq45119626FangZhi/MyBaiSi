package com.fz.mybaisi.essence.pager;

import android.content.Context;
import android.view.View;

import com.fz.mybaisi.R;
import com.fz.mybaisi.base.DetailBasePager;


/**
 * @FileName:com.fz.mybaisi.essence.pager.SharePager.java
 * @author：方志
 * @date: 2017-01-09 22:16
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class SharePager extends DetailBasePager {
    public SharePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.test_text_item,null);
    }

    @Override
    public void initData() {

    }
}
