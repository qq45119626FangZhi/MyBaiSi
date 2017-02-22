package com.fz.mybaisi.essence.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fz.mybaisi.R;
import com.fz.mybaisi.essence.bean.EssenceBean;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @FileName:com.fz.mybaisi.essence.adapter.viewholder.TextHolder.java
 * @author：方志
 * @date: 2017-01-04 16:18
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class TextHolder extends BaseViewHolder {
    @BindView(R.id.tv_content)
    TextView tvContext;

    public TextHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(Context mContext,EssenceBean.ListBean listBean) {
        super.setData(mContext, listBean);
        tvContext.setText(listBean.getText() + "_" + listBean.getType());
    }

}
