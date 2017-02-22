package com.fz.mybaisi.newspost.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fz.mybaisi.R;
import com.fz.mybaisi.newspost.adapter.holder.MyHolder;
import com.fz.mybaisi.newspost.bean.MusicBean;
import com.fz.mybaisi.newspost.utils.Player;

import java.util.List;

/**
 * @FileName:com.fz.mybaisi.newspost.adapter.MusicAdapter.java
 * @author：方志
 * @date: 2016-12-31 10:06
 * @QQ：459119626
 * @微信：15549433151
 * @function <音乐的适配器>
 */

public class MusicAdapter extends RecyclerView.Adapter<MyHolder> {

    public static final int MUSIC_PLAY = 1;
    private final Context mContext;
    private final List<MusicBean.ListBean> datas;
    private final LayoutInflater inflater;
    public Player player;

    private int index = -1;

    public MusicAdapter(Context mContext, List<MusicBean.ListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
        player = new Player();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(mContext, inflater.inflate(R.layout.item_new_music, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.setmOnPlayerListener(new MyHolder.OnPlayerListener() {
            @Override
            public void onStart(int position) {
                if (index != position) {
                    index = position;
                }
                MusicAdapter.this.notifyDataSetChanged();
                Log.e("TAG", "-----------------notifyDataSetChanged--------------");

            }

            @Override
            public void onStop() {
                index = -1;
            }
        });
        //自定义音乐播放，没有用服务
        holder.setData(datas.get(position), player, index == position, position);

        // 使用节操播放器播放音乐
//        holder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    /**
     * 使用节操播放器播放音乐
     */
//    class MyHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_headpic)
//        ImageView ivHeadpic;
//        @BindView(R.id.tv_name)
//        TextView tvName;
//        @BindView(R.id.tv_time_refresh)
//        TextView tvTimeRefresh;
//        @BindView(R.id.ll_user_info)
//        LinearLayout llUserInfo;
//        @BindView(R.id.iv_right_more)
//        ImageView ivRightMore;
//        @BindView(R.id.tv_content)
//        TextView tvContent;
//        @BindView(R.id.jcv_videoplayer)
//        JCVideoPlayer jcvVideoplayer;
//        @BindView(R.id.tv_play_nums)
//        TextView tvPlayNums;
//        @BindView(R.id.tv_music_duration)
//        TextView tvMusicDuration;
//        @BindView(R.id.rl_holder)
//        RelativeLayout rlHolder;
//        @BindView(R.id.iv_bottom_ding)
//        ImageButton ivBottomDing;
//        @BindView(R.id.tv_shenhe_ding_number)
//        TextView tvShenheDingNumber;
//        @BindView(R.id.rl_bottom_com)
//        LinearLayout rlBottomCom;
//        @BindView(R.id.iv_bottom_cai)
//        ImageButton ivBottomCai;
//        @BindView(R.id.tv_shenhe_cai_number)
//        TextView tvShenheCaiNumber;
//        @BindView(R.id.ll_cai)
//        LinearLayout llCai;
//        @BindView(R.id.iv_bottom_share)
//        ImageButton ivBottomShare;
//        @BindView(R.id.tv_posts_number)
//        TextView tvPostsNumber;
//        @BindView(R.id.ll_share)
//        LinearLayout llShare;
//        @BindView(R.id.iv_bottom_comment)
//        ImageButton ivBottomComment;
//        @BindView(R.id.tv_comment_number)
//        TextView tvCommentNumber;
//        @BindView(R.id.ll_comment)
//        LinearLayout llComment;
//        @BindView(R.id.ll_ding)
//        LinearLayout llDing;
//        @BindView(R.id.ll_comments)
//        LinearLayout llComments;
//
//        PlayTimeUtils utils;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            utils = new PlayTimeUtils();
//        }
//
//        public void setData(MusicBean.ListBean listBean) {
//            //头部
//            if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
//                Glide.with(mContext).load(listBean.getU().getHeader().get(0)).into(ivHeadpic);
//            }
//            if (listBean.getU() != null && listBean.getU().getName() != null) {
//                tvName.setText(listBean.getText() + "");
//            }
//            tvTimeRefresh.setText(listBean.getPasstime());
//
//            //设置音乐
//            tvContent.setText(listBean.getText() + "_" + listBean.getType());
//            //播放音乐
//            String music = listBean.getAudio().getAudio().get(0);
//            String image = listBean.getAudio().getThumbnail().get(0);
//            jcvVideoplayer.setUp(music, image,"");
//            jcvVideoplayer.releaseAllVideos();
//
//            tvPlayNums.setText(listBean.getAudio().getPlaycount() + "次播放");
//            tvMusicDuration.setText(utils.stringForTime(listBean.getAudio().getDuration() * 1000) + "");
//
//            //设置底部点赞，踩,转发
//            tvShenheDingNumber.setText(listBean.getUp() + "");
//            tvShenheCaiNumber.setText(listBean.getDown() + "");
//            tvPostsNumber.setText(listBean.getForward() + "");
//            tvCommentNumber.setText(listBean.getComment() + "");
//        }
//    }
}
