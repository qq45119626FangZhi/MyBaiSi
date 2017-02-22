package com.fz.mybaisi.me.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.fz.mybaisi.base.DetailBasePager;
import com.fz.mybaisi.me.bean.SquareBean;
import com.fz.mybaisi.me.pager.PagerFirst;
import com.fz.mybaisi.me.pager.PagerSecond;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName:com.fz.mybaisi.essence.adapter.SquareAdapter.java
 * @author：方志
 * @date: 2017-01-03 15:30
 * @QQ：459119626
 * @微信：15549433151
 * @function <网格页面>
 */

public class SquareAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<SquareBean.SquareListBean> datas;
    //总数据
    private List<DetailBasePager> pagers;
    private List<SquareBean.SquareListBean> datas1;
    private List<SquareBean.SquareListBean> datas2;


    public SquareAdapter(Context mContext, List<SquareBean.SquareListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;

        //分解集合数据
        datas1 = new ArrayList<>();
        datas2 = new ArrayList<>();

        for(int i = 0; i < datas.size(); i++) {
           if(i <= 9) {
               datas1.add(datas.get(i));
           }else{
               datas2.add(datas.get(i));
           }
        }

        //初始化页面
        pagers = new ArrayList<>();
        pagers.add(new PagerFirst(mContext,datas1));
        pagers.add(new PagerSecond(mContext,datas2));


    }

    @Override
    public int getCount() {
        return pagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        DetailBasePager Pager = pagers.get(position);
        View rootView = Pager.rootView;
        container.addView(rootView);
        //调用initData加载数据
        Pager.initData();

        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
