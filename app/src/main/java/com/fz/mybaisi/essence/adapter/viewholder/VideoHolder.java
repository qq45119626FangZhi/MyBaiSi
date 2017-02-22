package com.fz.mybaisi.essence.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fz.mybaisi.R;
import com.fz.mybaisi.essence.bean.EssenceBean;
import com.fz.mybaisi.utils.PlayTimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @FileName:com.fz.mybaisi.essence.adapter.viewholder.VideoHolder.java
 * @author：方志
 * @date: 2017-01-04 16:16
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class VideoHolder extends BaseViewHolder {

    @BindView(R.id.tv_content)
    TextView tvContext;
    @BindView(R.id.jcv_videoplayer)
    JCVideoPlayerStandard jcvVideoplayer;
    @BindView(R.id.tv_play_nums)
    TextView tvPlayNums;
    @BindView(R.id.tv_video_duration)
    TextView tvVideoDuration;

    private PlayTimeUtils utils;

    public VideoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        utils = new PlayTimeUtils();
    }

    @Override
    public void setData(Context mContext, EssenceBean.ListBean listBean) {
        super.setData(mContext, listBean);

        //设置视频
        tvContext.setText(listBean.getText() + "_" + listBean.getType());
        //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
        boolean setUp = jcvVideoplayer.setUp(listBean.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        //加载图片
        if (setUp) {
            Glide.with(mContext).load(listBean.getVideo().getThumbnail().get(0)).into(jcvVideoplayer.thumbImageView);
        }
        tvPlayNums.setText(listBean.getVideo().getPlaycount() + "次播放");
        tvVideoDuration.setText(utils.stringForTime(listBean.getVideo().getDuration() * 1000) + "");
    }

}
