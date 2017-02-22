package com.fz.mybaisi.essence.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cjj.MaterialRefreshLayout;
import com.fz.mybaisi.R;
import com.fz.mybaisi.base.DetailBasePager;
import com.fz.mybaisi.essence.adapter.EssenceAdapter;
import com.fz.mybaisi.essence.bean.EssenceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.mybaisi.essence.pager.PostPager.java
 * @author：方志
 * @date: 2017-01-09 22:15
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class PostPager extends DetailBasePager {

    private final List<EssenceBean.ListBean> datas;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;

    public PostPager(Context context, List<EssenceBean.ListBean> datas) {
        super(context);
        this.datas = datas;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.essence_pager_item, null);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        if(datas != null && datas.size() > 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(new EssenceAdapter(mContext,datas));
        }
    }
}
