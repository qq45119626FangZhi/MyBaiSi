package com.fz.mybaisi.essence.pager;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.fz.mybaisi.R;
import com.fz.mybaisi.base.DetailBasePager;
import com.fz.mybaisi.essence.adapter.EssenceAdapter;
import com.fz.mybaisi.essence.bean.EssenceBean;
import com.fz.mybaisi.interfaces.OnGetNetListener;
import com.fz.mybaisi.utils.GetNet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.mybaisi.essence.pager.RecommendPager.java
 * @author：方志
 * @date: 2016-12-29 15:51
 * @QQ：459119626
 * @微信：15549433151
 * @function <推荐页面>
 */

public class EssencePager extends DetailBasePager {

    private final String url;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<EssenceBean.ListBean> datas;
    private EssenceAdapter adapter;

    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;

    public EssencePager(Context mContext, String url) {
        super(mContext);
        this.url = url;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.essence_pager_item, null);
        ButterKnife.bind(this, view);

        //设置下拉刷新，需要在视图李设置
        refresh.setMyStyle(true);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener(){

            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromNet(url);
                        materialRefreshLayout.finishRefresh();
                    }
                },2000);
            }
        });

        return view;
    }

    @Override
    public void initData() {
        getDataFromNet(url);

    }



    //联网请求数据
    private void getDataFromNet(String url) {
        GetNet.getNetData(this.url, null, new OnGetNetListener() {
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
     * 解析数据
     *
     * @param response
     */
    private void analysisData(String response) {
        EssenceBean essenceBean = JSON.parseObject(response, EssenceBean.class);
//        String content = recommendBean.getList().get(0).getTop_comments().get(0).getContent();
//        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        datas = essenceBean.getList();
        if (datas != null && datas.size() > 0) {
            //设置布局管理器
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            //设置适配器
            adapter = new EssenceAdapter(mContext, datas);

            recyclerView.setAdapter(adapter);

        }
    }
}
