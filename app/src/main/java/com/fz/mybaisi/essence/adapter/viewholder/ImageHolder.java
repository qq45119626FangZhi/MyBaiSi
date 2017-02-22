package com.fz.mybaisi.essence.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fz.mybaisi.R;
import com.fz.mybaisi.activity.ShowImageAndGifActivity;
import com.fz.mybaisi.essence.bean.EssenceBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.mybaisi.essence.adapter.viewholder.ImageHolder.java
 * @author：方志
 * @date: 2017-01-04 16:17
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class ImageHolder extends BaseViewHolder {
    @BindView(R.id.tv_content)
    TextView tvContext;
    @BindView(R.id.iv_image_icon)
    ImageView ivImageIcon;

    public ImageHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(final Context mContext, final EssenceBean.ListBean listBean) {
        super.setData(mContext, listBean);
        //设置文本-所有的都有
        tvContext.setText(listBean.getText() + "_" + listBean.getType());
        //图片特有的
        ivImageIcon.setImageResource(R.drawable.bg_item);
        if (listBean.getImage() != null && listBean.getImage() != null && listBean.getImage().getSmall() != null) {
            Glide.with(mContext).load(listBean.getImage()
                    .getDownload_url().get(0)).placeholder(R.drawable.bg_item)
                    .error(R.drawable.bg_item).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivImageIcon);
        }

        //图片的点击事件
        ivImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBean != null) {
                    //传递图片列表
                    Intent intent = new Intent(mContext, ShowImageAndGifActivity.class);
                    if ("image".equals(listBean.getType())) {
                        String url = listBean.getImage().getBig().get(0);
                        intent.putExtra("url", url);
                        mContext.startActivity(intent);
                    }
                }
            }
        });
    }

}
