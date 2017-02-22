package com.fz.mybaisi.essence.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fz.mybaisi.R;
import com.fz.mybaisi.base.BaseFragment;
import com.fz.mybaisi.base.DetailBasePager;
import com.fz.mybaisi.essence.bean.EssenceTitleBean;
import com.fz.mybaisi.essence.pager.EssencePager;
import com.fz.mybaisi.interfaces.OnGetNetListener;
import com.fz.mybaisi.utils.MyConstants;
import com.fz.mybaisi.utils.GetNet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @FileName:com.fz.mybaisi.essence.EssenceFragment.java
 * @author：方志
 * @date: 2016-12-29 11:49
 * @QQ：459119626
 * @微信：15549433151
 * @function <精华页面的fragment>
 */

public class EssenceFragment extends BaseFragment {
    public static final String FIGURE = "figure";
    @BindView(R.id.new_ib_trophy)
    ImageButton essenceIbTrophy;
    @BindView(R.id.new_ib_setting)
    ImageButton essenceIbPass;
    @BindView(R.id.new_tabLayout)
    TabLayout essenceTabLayout;
    @BindView(R.id.new_viewPager)
    ViewPager essenceViewPager;


    //标题栏集合数据
    private List<EssenceTitleBean.MenusBean.SubmenusBean> datas;
    //页面的集合
    private List<DetailBasePager> pagers;
    private EssenceViewpager adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_essence_item, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    //联网请求数据
    private void getDataFromNet() {
        GetNet.getNetData(MyConstants.ESSENCE_TITLE, null, new OnGetNetListener() {
            @Override
            public void onSuccess(String response) {
                analysisData(response);
            }

            @Override
            public void onError(Exception e) {

                Log.e("TAG", "RecommendPager联网失败" + e.getMessage());
            }
        });

    }

    /**
     * 解析数据，装配数据
     *
     * @param response
     */
    private void analysisData(String response) {

        //解析数据
        EssenceTitleBean titleBean = JSON.parseObject(response, EssenceTitleBean.class);
//        String name = titleBean.getMenus().get(0).getSubmenus().get(0).getName();
        datas = titleBean.getMenus().get(0).getSubmenus();

        if (datas != null && datas.size() > 0) {
            //页面集合
            getListPagers();
            //设置viewpager的适配器
            adapter = new EssenceViewpager();
            essenceViewPager.setAdapter(adapter);

            //设置ViewPager与tabLayout绑定
            essenceTabLayout.setupWithViewPager(essenceViewPager);

            //设置模式，必须设置
            essenceTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }else{
            Toast.makeText(mContext, "没有数据", Toast.LENGTH_SHORT).show();
        }

    }

    private String[] urls = {MyConstants.ESSENCE_RECOMMEND, MyConstants.ESSENCE_VIDEO,
            MyConstants.ESSENCE_PHOTO, MyConstants.ESSENCE_SATIN,
            MyConstants.ESSENCE_GIRL, MyConstants.ESSENCE_HOT_NET,
            MyConstants.ESSENCE_LIST, MyConstants.ESSENCE_SOCIETY,
            MyConstants.ESSENCE_GIRL, MyConstants.ESSENCE_COLD_KNOWLEDGE,
            MyConstants.ESSENCE_GAME,};


    //页面集合
    private void getListPagers() {
        pagers = new ArrayList<>();
        for(int i = 0; i < urls.length; i++) {
            pagers.add(new EssencePager(mContext,urls[i]));
        }
    }

    @OnClick({R.id.new_ib_trophy, R.id.new_ib_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_ib_trophy:
                Toast.makeText(mContext, "进入奖杯页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.new_ib_setting:
                Toast.makeText(mContext, "进入穿越页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class EssenceViewpager extends PagerAdapter {

        /**
         * 显示标题
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return datas.get(position).getName();
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
}
