package com.fz.mybaisi.newspost;


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
import com.fz.mybaisi.newspost.pager.MusicPager;
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
 * @function <新帖页面的fragment>
 */

public class NewsPostFragment extends BaseFragment {


    @BindView(R.id.new_ib_trophy)
    ImageButton newIbTrophy;
    @BindView(R.id.new_ib_setting)
    ImageButton newIbPass;
    @BindView(R.id.new_tabLayout)
    TabLayout newTabLayout;
    @BindView(R.id.new_viewPager)
    ViewPager newViewPager;
    //标题栏集合数据
    private List<EssenceTitleBean.MenusBean.SubmenusBean> datas;
    //页面的集合
    private List<DetailBasePager> pagers;
    private NewPagerAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_new_post_item, null);
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
        datas = titleBean.getMenus().get(1).getSubmenus();

        if (datas != null && datas.size() > 0) {
            //页面集合
            getListPagers();
            //设置viewpager的适配器
            adapter = new NewPagerAdapter();
            newViewPager.setAdapter(adapter);

            //设置ViewPager与tabLayout绑定
            newTabLayout.setupWithViewPager(newViewPager);

            //设置模式，必须设置
            newTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            Toast.makeText(mContext, "没有数据", Toast.LENGTH_SHORT).show();
        }

    }

    private String[] urls = {MyConstants.NEW_ALL, MyConstants.NEW_VIDEO,
            MyConstants.NEW_PHOTO, MyConstants.NEW_SATIN,
            MyConstants.NEW_ORIGINAL, MyConstants.NEW_HOT_NET,
            MyConstants.NEW_GIRL, MyConstants.NEW_COLD_KNOWLEDGE,
            MyConstants.NEW_MUSIC, MyConstants.NEW_GAME};


    //页面集合
    private void getListPagers() {
        pagers = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            if (i == datas.size() - 2) {
                pagers.add(new MusicPager(mContext, urls[i]));
            } else {
                pagers.add(new EssencePager(mContext, urls[i]));
            }
        }
    }


    @OnClick({R.id.new_ib_trophy, R.id.new_ib_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_ib_trophy:
                Toast.makeText(mContext, "进入审核页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.new_ib_setting:
                Toast.makeText(mContext, "进入搜索页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class NewPagerAdapter extends PagerAdapter {

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
