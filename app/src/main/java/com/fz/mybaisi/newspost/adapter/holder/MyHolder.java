package com.fz.mybaisi.newspost.adapter.holder;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fz.mybaisi.R;
import com.fz.mybaisi.newspost.bean.MusicBean;
import com.fz.mybaisi.newspost.utils.Player;
import com.fz.mybaisi.utils.PlayTimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.fz.mybaisi.newspost.adapter.holder.MyHolder.java
 * @author：方志
 * @date: 2017-01-05 18:39
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MUSIC_PROGRESS = 1;
    private final Context mContext;
    @BindView(R.id.iv_headpic)
    ImageView ivHeadpic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time_refresh)
    TextView tvTimeRefresh;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.iv_right_more)
    ImageView ivRightMore;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_music_icon)
    ImageView ivMusicIcon;
    @BindView(R.id.tv_play_nums)
    TextView tvPlayNums;
    @BindView(R.id.tv_music_duration)
    TextView tvMusicDuration;

    @BindView(R.id.iv_connect)
    ImageView ivConnect;

    @BindView(R.id.iv_play_pause)
    ImageView ivPlayPause;
    @BindView(R.id.music_seekBar)
    SeekBar musicSeekBar;
    @BindView(R.id.tv_music_progress)
    TextView tvMusicProgress;

    @BindView(R.id.ll_music_start)
    LinearLayout llMusicStart;

    @BindView(R.id.rl_holder)
    RelativeLayout rlHolder;
    @BindView(R.id.iv_bottom_ding)
    ImageButton ivBottomDing;
    @BindView(R.id.tv_shenhe_ding_number)
    TextView tvShenheDingNumber;
    @BindView(R.id.rl_bottom_com)
    LinearLayout rlBottomCom;
    @BindView(R.id.iv_bottom_cai)
    ImageButton ivBottomCai;
    @BindView(R.id.tv_shenhe_cai_number)
    TextView tvShenheCaiNumber;
    @BindView(R.id.ll_cai)
    LinearLayout llCai;
    @BindView(R.id.iv_bottom_share)
    ImageButton ivBottomShare;
    @BindView(R.id.tv_posts_number)
    TextView tvPostsNumber;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.iv_bottom_comment)
    ImageButton ivBottomComment;
    @BindView(R.id.tv_comment_number)
    TextView tvCommentNumber;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.ll_ding)
    LinearLayout llDing;
    @BindView(R.id.ll_comments)
    LinearLayout llComments;

    PlayTimeUtils utils;
    private Player player;
    private MusicBean.ListBean listBean;
    private boolean isplay = false;
    private int position;

    public MyHolder(Context mContext, final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = mContext;
        utils = new PlayTimeUtils();
        //设置默认模式
        setIdelMode();

    }

    //设置数据
    public void setData(MusicBean.ListBean listBean, Player player, boolean isplay, int position) {
        this.player = player;
        this.listBean = listBean;
        this.isplay = isplay;
        this.position = position;
        //头部
        if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
            Glide.with(mContext).load(listBean.getU().getHeader().get(0)).into(ivHeadpic);
        }
        if (listBean.getU() != null && listBean.getU().getName() != null) {
            tvName.setText(listBean.getText() + "");
        }
        tvTimeRefresh.setText(listBean.getPasstime());

        //设置视频
        tvContent.setText(listBean.getText() + "_" + listBean.getType());
        //加载图片显示封面的地址
        Glide.with(mContext).load(listBean.getAudio().getThumbnail().get(1)).into(ivMusicIcon);

        tvPlayNums.setText(listBean.getAudio().getPlaycount() + "次播放");
        tvMusicDuration.setText(utils.stringForTime(listBean.getAudio().getDuration() * 1000) + "");

        //设置底部点赞，踩,转发
        tvShenheDingNumber.setText(listBean.getUp() + "");
        tvShenheCaiNumber.setText(listBean.getDown() + "");
        tvPostsNumber.setText(listBean.getForward() + "");
        tvCommentNumber.setText(listBean.getComment() + "");

        //评论
        comments();
        /**
         * 校验状态
         */
        if (isplay) {
            setPlayMode();

        } else {
            setIdelMode();
        }

        //设置seekbar最大值
        musicSeekBar.setMax(listBean.getAudio().getDuration());
    }


    /**
     * 评论
     */
    private void comments() {
        llComments.removeAllViews();
        //评论
        List<MusicBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
        if (top_comments != null && top_comments.size() > 0) {
            for (int i = 0; i < top_comments.size(); i++) {

                //得到每条具体评论
                MusicBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
                /**
                 * 线性布局
                 */
                final LinearLayout myLinear = new LinearLayout(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
                //用户名和评论
                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //用户
                TextView textView = new TextView(mContext);
                textView.setTextColor(Color.BLUE);
                textView.setText(topCommentsBean.getU().getName());
                myLinear.addView(textView, tvParams);
                //评论
                TextView textView1 = new TextView(mContext);
                textView1.setTextColor(Color.BLACK);
                textView1.setText(" : " + topCommentsBean.getContent());
                myLinear.addView(textView1, tvParams);

                llComments.addView(myLinear, params);

            }
        }
    }


    /**
     * 进度更新
     */
    private int tempProgress;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MUSIC_PROGRESS:
                    tempProgress++;
                    /**
                     * 播放完成的监听
                     */
                    if(tempProgress == listBean.getAudio().getDuration()+1) {
                        tvMusicProgress.setText(utils.stringForTime(0) + "");
                        setIdelMode();
                        if(mOnPlayerListener != null) {
                            mOnPlayerListener.onStop();
                        }
                        return;
                    }
                    tvMusicProgress.setText(utils.stringForTime(tempProgress * 1000) + "");

                    int position = player.mediaPlayer.getCurrentPosition();
                    int duration = player.mediaPlayer.getDuration();
                    if (duration > 0) {
                        // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                        long pos = musicSeekBar.getMax() * position / duration;
                        musicSeekBar.setProgress((int) pos);
                    }

                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(MUSIC_PROGRESS, 1000);

                    break;
            }
        }
    };


    //设置播放模式
    public void setPlayMode() {
        ivConnect.setVisibility(View.GONE);
        llMusicStart.setVisibility(View.VISIBLE);
        ivConnect.setOnClickListener(null);
        ivPlayPause.setOnClickListener(this);
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    //设置默认模式
    public void setIdelMode() {
        ivConnect.setVisibility(View.VISIBLE);
        llMusicStart.setVisibility(View.GONE);
        ivConnect.setOnClickListener(this);
        ivPlayPause.setOnClickListener(null);
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play_pause://播放暂停

                if (player.mediaPlayer.isPlaying()) {
                    //暂停

                    ivPlayPause.setBackgroundResource(R.drawable.news_play_audio);
                    player.pause();

                    handler.removeCallbacksAndMessages(null);

                } else {
                    //播放

                    ivPlayPause.setBackgroundResource(R.drawable.voice_recoder_stop);
                    player.play();

                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(MUSIC_PROGRESS, 1000);
                }
                break;
            case R.id.iv_connect://联网请求播放

                if (!isplay) {
                    setPlayMode();
                    player.mediaPlayer.reset();
                    //重置
                    String audioUrl = listBean.getAudio().getAudio().get(0);
                    //播放音乐
                    player.playUrl(audioUrl);
                    ivPlayPause.setBackgroundResource(R.drawable.voice_recoder_stop);

                    if (mOnPlayerListener != null) {
                        mOnPlayerListener.onStart(position);
                    }
                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(1, 1000);
                }

                break;
        }
    }


    /**
     * 接口回调
     */
    public interface OnPlayerListener {
        void onStart(int position);
        void onStop();
    }

    private OnPlayerListener mOnPlayerListener;

    public void setmOnPlayerListener(OnPlayerListener mOnPlayerListener) {
        this.mOnPlayerListener = mOnPlayerListener;
    }
}




