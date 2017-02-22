package com.fz.mybaisi.me.pager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fz.mybaisi.R;
import com.fz.mybaisi.base.DetailBasePager;
import com.fz.mybaisi.me.bean.SquareBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.mybaisi.me.pager.PagerSecond.java
 * @author：方志
 * @date: 2017-01-03 15:51
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class PagerSecond extends DetailBasePager {

    private final List<SquareBean.SquareListBean> datas;
    @BindView(R.id.gridView)
    GridView gridView;
    private SecondAdapter adapter;

    public PagerSecond(Context context, List<SquareBean.SquareListBean> datas2) {
        super(context);
        this.datas = datas2;
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.pager_me_first, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        adapter = new SecondAdapter();
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class SecondAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            PagerFirst.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_first_pager, null);
                viewHolder = new PagerFirst.ViewHolder(convertView);
                convertView.setTag(viewHolder);

            }else{
                viewHolder = (PagerFirst.ViewHolder) convertView.getTag();
            }

            //装配数据
            Glide.with(mContext).load(datas.get(position).getIcon()).into(viewHolder.ivIcon);

            viewHolder.tvName.setText(datas.get(position).getName());

            return convertView;
        }


    }

    static class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
