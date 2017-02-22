package com.fz.mybaisi.newspost.pager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.fz.mybaisi.R;
import com.fz.mybaisi.base.DetailBasePager;
import com.fz.mybaisi.interfaces.OnGetNetListener;
import com.fz.mybaisi.newspost.adapter.MusicAdapter;
import com.fz.mybaisi.newspost.bean.MusicBean;
import com.fz.mybaisi.utils.GetNet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.mybaisi.newspost.pager.MusicPager.java
 * @author：方志
 * @date: 2016-12-31 09:58
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class MusicPager extends DetailBasePager {

    private final String url;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MusicAdapter adapter;
    private List<MusicBean.ListBean> datas;

    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;

    public MusicPager(Context mContext, String url) {
        super(mContext);
        this.url = url;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.essence_pager_item, null);
        ButterKnife.bind(this, view);

        //下拉刷新
        refresh.setMyStyle(true);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener(){

            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefresh();

                    }
                }, 2000);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();

    }

    //联网请求数据
    private void getDataFromNet() {
        GetNet.getNetData(url, null, new OnGetNetListener() {
            @Override
            public void onSuccess(String response) {
                analysisData(response);
            }

            @Override
            public void onError(Exception e) {

                Log.e("TAG", "RecommendPager联网失败" + e.getMessage());
            }
        });

//        new Thread(){
//            public void run(){
//                final List<MusicBean.ListBean> datas = GetNet.getData(url);
//               UIUtils.runOnUiThread(new Runnable() {
//                   @Override
//                   public void run() {
//                       if (datas != null && datas.size() > 0) {
//                           //设置适配器
//                           adapter = new MusicAdapter(mContext, datas);
//                           recyclerView.setAdapter(adapter);
//                           //设置布局管理器
//                           recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
//                       }
//                   }
//               });
//            }
//        }.start();
    }

    /**
     * 解析数据
     *
     * @param response
     */
    private void analysisData(String response) {

        MusicBean musicBean = JSON.parseObject(response, MusicBean.class);
//        String content = recommendBean.getList().get(0).getTop_comments().get(0).getContent();
//        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        datas = musicBean.getList();
        if (datas != null && datas.size() > 0) {
            //设置适配器
            adapter = new MusicAdapter(mContext, datas);
            recyclerView.setAdapter(adapter);
            //设置布局管理器
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            recyclerView.setLayoutManager(manager);

//            //设置监听
//            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    if (position > 3) {
//                        adapter.MyHolder.ivConnect.setVisibility(View.GONE);
//                    } else {
////                        ibTop.setVisibility(View.GONE);//隐藏
//                    }
//                    return 1;
//                }
//            });

        }



    }
}
