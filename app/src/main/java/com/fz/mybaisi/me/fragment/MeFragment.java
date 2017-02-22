package com.fz.mybaisi.me.fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fz.mybaisi.R;
import com.fz.mybaisi.activity.LoginActivity;
import com.fz.mybaisi.base.BaseFragment;
import com.fz.mybaisi.interfaces.OnGetNetListener;
import com.fz.mybaisi.me.adapter.MeAdapter;
import com.fz.mybaisi.me.bean.SquareBean;
import com.fz.mybaisi.me.bean.SubscribeBean;
import com.fz.mybaisi.utils.DataCleanManager;
import com.fz.mybaisi.utils.GetNet;
import com.fz.mybaisi.utils.MyConstants;
import com.fz.mybaisi.utils.PreferenceUtils;

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
 * @function <我的页面的fragment>
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.me_ib_wallet)
    ImageButton meIbWallet;
    @BindView(R.id.me_ib_day_night)
    ImageButton meIbDayNight;
    @BindView(R.id.new_ib_setting)
    ImageButton newIbPass;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_query_cache)
    TextView tvQueryCache;
    @BindView(R.id.tv_clear_cache)
    TextView tvClearCache;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.dl_left)
    DrawerLayout dlLeft;

    private MeAdapter adapter;
    /**
     * 网格数据
     */
    private List<SquareBean.SquareListBean> datas;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_me_item, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

        getDataFromNet();

    }


    //联网请求数据
    private void getDataFromNet() {
        GetNet.getNetData(MyConstants.ME_SQUARE, null, new OnGetNetListener() {
            @Override
            public void onSuccess(String response) {
                analysisData(response);
            }

            @Override
            public void onError(Exception e) {
                Log.e("TAG", "RecommendPager联网失败" + e.getMessage());
            }
        });

        GetNet.getNetData(MyConstants.ME_SUBSCRIBE, null, new OnGetNetListener() {
            @Override
            public void onSuccess(String response) {
                analysisData1(response);
            }

            @Override
            public void onError(Exception e) {
                Log.e("TAG", "RecommendPager联网失败" + e.getMessage());
            }
        });

    }

    private void analysisData1(String response) {
        SubscribeBean subscribeBean = JSON.parseObject(response, SubscribeBean.class);
        List<SubscribeBean.RecTagsBean> subscribeDatas = subscribeBean.getRec_tags();
//        Log.e("TAG", "1111111111111111111" + subscribeDatas.get(0).getTheme_name());

        if (datas != null && datas.size() > 0) {
            //设置viewpager的适配器
            adapter = new MeAdapter(mContext, datas, subscribeDatas);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        } else {
            Toast.makeText(mContext, "没有数据", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 解析数据，装配数据
     *
     * @param response
     */
    private void analysisData(String response) {

        //解析数据
        SquareBean squareBean = JSON.parseObject(response, SquareBean.class);
        datas = squareBean.getSquare_list();
//        Toast.makeText(mContext, this.datas.get(0).getName(), Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.me_ib_wallet, R.id.me_ib_day_night, R.id.new_ib_setting,R.id.tv_query_cache, R.id.tv_clear_cache, R.id.tv_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_ib_wallet:
                Toast.makeText(mContext, "钱包页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_ib_day_night:
                Toast.makeText(mContext, "调节亮度", Toast.LENGTH_SHORT).show();
                break;
            case R.id.new_ib_setting:
                //显示筛选菜单
                dlLeft.openDrawer(Gravity.RIGHT);

               break;

            case R.id.tv_query_cache:
//                Toast.makeText(mContext, "计算缓存", Toast.LENGTH_SHORT).show();
                try {
                    String cacheSize = DataCleanManager.getTotalCacheSize(mContext);
                    Toast.makeText(mContext, cacheSize, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_clear_cache:
                Toast.makeText(mContext, "清理缓存", Toast.LENGTH_SHORT).show();
                DataCleanManager.clearAllCache(mContext);

                break;
            case R.id.tv_logout:
                Toast.makeText(mContext, "退出登录", Toast.LENGTH_SHORT).show();
                PreferenceUtils.putBoolean(mContext, LoginActivity.QQ_LOGIN_KEY, false);
                adapter.notifyDataSetChanged();
                break;
        }
    }



}
