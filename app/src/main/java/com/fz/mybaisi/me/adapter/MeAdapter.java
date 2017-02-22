package com.fz.mybaisi.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fz.mybaisi.R;
import com.fz.mybaisi.activity.LoginActivity;
import com.fz.mybaisi.activity.MainActivity;
import com.fz.mybaisi.me.bean.SquareBean;
import com.fz.mybaisi.me.bean.SubscribeBean;
import com.fz.mybaisi.utils.PreferenceUtils;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.mybaisi.me.adapter.SquareAdapter.java
 * @author：方志
 * @date: 2017-01-03 14:24
 * @QQ：459119626
 * @微信：15549433151
 * @function <我的页面的适配器>
 */

public class MeAdapter extends RecyclerView.Adapter {

    private static final int LOGIN_REGIST = 0;//登录注册
    private static final int SQUARE = 1;//中间网格
    private static final int SUBSCRIBE = 2;//推荐订阅

    private final Context mContext;
    private final List<SquareBean.SquareListBean> datas;
    private final LayoutInflater inflater;
    private final List<SubscribeBean.RecTagsBean> subscribeDatas;
    private MainActivity mainActivity;

    public MeAdapter(Context mContext, List<SquareBean.SquareListBean> datas, List<SubscribeBean.RecTagsBean> subscribeDatas) {
        this.mContext = mContext;
        this.datas = datas;
        this.subscribeDatas = subscribeDatas;
        inflater = LayoutInflater.from(mContext);

        mainActivity = (MainActivity) mContext;
    }

    /**
     * 返回数据类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int CURRENT = -1;
        if (position == LOGIN_REGIST) {//登录注册
            CURRENT = LOGIN_REGIST;
        } else if (position == SQUARE) {//中间网格
            CURRENT = SQUARE;
        } else if (position == SUBSCRIBE) {//推荐订阅
            CURRENT = SUBSCRIBE;
        }

        return CURRENT;
    }

    /**
     * 不同类型创建不同的ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOGIN_REGIST) {//登录注册
            return new LoginRegistHolser(inflater.inflate(R.layout.item_login_regist, null));
        } else if (viewType == SQUARE) {//中间网格
            return new SquareHolder(mContext, inflater.inflate(R.layout.item_square, null));

        } else if (viewType == SUBSCRIBE) {//推荐订阅
            return new SubscrribeHolder(mContext,inflater.inflate(R.layout.item_sunscribe, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == LOGIN_REGIST) {//登录注册
            ((LoginRegistHolser) holder).setData();
        } else if (getItemViewType(position) == SQUARE) {//中间网格
            ((SquareHolder) holder).setData(datas);
        } else if (getItemViewType(position) == SUBSCRIBE) {//推荐订阅
            ((SubscrribeHolder) holder).setData(subscribeDatas);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    //订阅
    class SubscrribeHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        private SubscribeAdapter adapter;

        public SubscrribeHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = mContext;
        }


        public void setData(List<SubscribeBean.RecTagsBean> recTagsBean) {
            adapter = new SubscribeAdapter(mContext,recTagsBean);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }
    }


    //网格数据
    class SquareHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.viewPager)
        ViewPager viewPager;
        @BindView(R.id.indicator)
        CirclePageIndicator indicator;
        private SquareAdapter adapter;


        public SquareHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = mContext;

        }


        public void setData(List<SquareBean.SquareListBean> datas) {
            adapter = new SquareAdapter(mContext, datas);
            viewPager.setAdapter(adapter);
            indicator.setViewPager(viewPager);

        }
    }

    //登录注册
    class LoginRegistHolser extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_header)
        ImageView ivHeader;
        @BindView(R.id.ll_login_regist)
        LinearLayout llLoginRegist;

        public LoginRegistHolser(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData() {

            //是否进入主页面，读取缓存的向导页面记录
            boolean isLogin = PreferenceUtils.getBoolean(mContext, LoginActivity.QQ_LOGIN_KEY);
            if(isLogin) {
                //显示登录后的
                llLoginRegist.setVisibility(View.GONE);
            }else {

                //显示登录注册
                llLoginRegist.setVisibility(View.VISIBLE);

                llLoginRegist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext,LoginActivity.class));
                        mainActivity.finish();
                    }
                });
            }
        }

    }


}
