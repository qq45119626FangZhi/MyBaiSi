package com.fz.mybaisi.essence.adapter;


import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.fz.mybaisi.R;
import com.fz.mybaisi.activity.PersonalDetailActivity;
import com.fz.mybaisi.base.DetailBasePager;
import com.fz.mybaisi.essence.bean.EssenceBean;
import com.fz.mybaisi.essence.bean.TempBean;
import com.fz.mybaisi.essence.pager.CommentPager;
import com.fz.mybaisi.essence.pager.PostPager;
import com.fz.mybaisi.essence.pager.SharePager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fz.mybaisi.application.MyApplication.context;

/**
 * @FileName:com.fz.mybaisi.essence.adapter.PersonAdapter.java
 * @author：方志
 * @date: 2017-01-09 19:39
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class PersonAdapter extends RecyclerView.Adapter {

    private final PersonalDetailActivity mContext;
    private final List<EssenceBean.ListBean> datas;
    private final TempBean tempBean;
    private final LayoutInflater inflater;

    public PersonAdapter(PersonalDetailActivity personalDetailActivity, List<EssenceBean.ListBean> datas, TempBean tempBean) {
        this.mContext = personalDetailActivity;
        this.datas = datas;
        this.tempBean = tempBean;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new UserInfoHolder(inflater.inflate(R.layout.item_person_tetail, parent, false));
        } else {
            return new TabHolder(mContext, inflater.inflate(R.layout.item_radio_viewpager, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            ((UserInfoHolder) holder).setData(tempBean);
        } else {
            ((TabHolder) holder).setData();
        }

    }

    @Override
    public int getItemCount() {
        return 2 + datas.size();
    }

    class UserInfoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_person_header)
        ImageView ivPersonHeader;
        @BindView(R.id.tv_like_numner)
        TextView tvLikeNumner;
        @BindView(R.id.tv_follow_numner)
        TextView tvFollowNumner;
        @BindView(R.id.tv_level)
        TextView tvLevel;

        public UserInfoHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(TempBean tempBean) {
            Glide.with(mContext).load(tempBean.getHeaderUrl())
                    .asBitmap().centerCrop()
                    .into(new BitmapImageViewTarget(ivPersonHeader) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            ivPersonHeader.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }

    class TabHolder extends RecyclerView.ViewHolder {
        private final PersonalDetailActivity mContext;
        @BindView(R.id.tab_layout)
        TabLayout tabLayout;
        @BindView(R.id.viewPager)
        ViewPager viewPager;
        private MyViewPagerAdaper adapter;

        public TabHolder(PersonalDetailActivity mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = mContext;

            initPager();
        }


        public void setData() {
            adapter = new MyViewPagerAdaper(pagers);
            viewPager.setAdapter(adapter);
            //关联ViewPager
            tabLayout.setupWithViewPager(viewPager);
        }

        private List<DetailBasePager> pagers;

        private void initPager() {
            pagers = new ArrayList<>();
            pagers.add(new PostPager(mContext, datas));//帖子
            pagers.add(new SharePager(mContext));//分享
            pagers.add(new CommentPager(mContext));//评论
        }


    }

    class MyViewPagerAdaper extends PagerAdapter {

        private final List<DetailBasePager> pagers;
        private String[] titles = {"帖子", "分享", "评论"};

        public MyViewPagerAdaper(List<DetailBasePager> pagers) {
            this.pagers = pagers;
        }

        /**
         * 显示标题
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
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
