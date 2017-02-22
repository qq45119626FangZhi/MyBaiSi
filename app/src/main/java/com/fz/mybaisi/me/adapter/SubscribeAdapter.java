package com.fz.mybaisi.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fz.mybaisi.R;
import com.fz.mybaisi.me.bean.SubscribeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.mybaisi.me.adapter.SubscribeAdapter.java
 * @author：方志
 * @date: 2017-01-03 19:27
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.MyHolder> {

    private final Context mContext;
    private final List<SubscribeBean.RecTagsBean> datas;
    private final LayoutInflater inflater;

    public SubscribeAdapter(Context mContext, List<SubscribeBean.RecTagsBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(inflater.inflate(R.layout.item_subscribe_detail, null));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null?0:datas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_header)
        ImageView ivHeader;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_subscribe_number)
        TextView tvSubscribeNumber;
        @BindView(R.id.tv_total_number)
        TextView tvTotalNumber;
        @BindView(R.id.ibn_follow)
        Button ibnFollow;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(SubscribeBean.RecTagsBean recTagsBean) {
            Glide.with(mContext).load(recTagsBean.getImage_list()).into(ivHeader);//图像
            tvName.setText(recTagsBean.getTheme_name());//主题名
            tvSubscribeNumber.setText(recTagsBean.getSub_number() + "");//订阅数量
            tvTotalNumber.setText(recTagsBean.getPost_num() + "");//总贴数

            ibnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "关注 " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
