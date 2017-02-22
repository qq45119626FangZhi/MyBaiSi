package com.fz.mybaisi.essence.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fz.mybaisi.R;
import com.fz.mybaisi.activity.TextDetailActivity;
import com.fz.mybaisi.essence.bean.CommentsBean;
import com.fz.mybaisi.essence.bean.TempBean;
import com.fz.mybaisi.utils.DensityUtil;
import com.fz.mybaisi.utils.PlayTimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @FileName:com.fz.mybaisi.essence.adapter.HotCommentAdapter.java
 * @author：方志
 * @date: 2016-12-30 16:32
 * @QQ：459119626
 * @微信：15549433151
 * @function <详情页面评论的适配器>
 */

public class CommentAdapter extends RecyclerView.Adapter {

    private final TextDetailActivity mContent;
    private final LayoutInflater inflater;
    private final TempBean tempBean;
    private final List<CommentsBean.NormalBean.ListBeanX> datas;

    private static final int TYPE_VIDEO = 0;//视频
    private static final int TYPE_IMAGE = 1;//图片
    private static final int TYPE_GIF = 2; //GIF图片
    private static final int TYPE_TEXT = 3; //文字

    private String type;

    public CommentAdapter(TextDetailActivity mContent, List<CommentsBean.NormalBean.ListBeanX> datas, TempBean tempBean) {
        this.mContent = mContent;
        this.datas = datas;
        this.tempBean = tempBean;
        inflater = LayoutInflater.from(mContent);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

//    /**
//     *
//     * @param position
//     * @return
//     */
//     @Override
//     public int getItemViewType(int position) {
//        if (position == TYPE_VIDEO) {
//            return TYPE_VIDEO;
//        }else if(position == TYPE_IMAGE) {
//            return TYPE_IMAGE;
//        }else if(position == TYPE_GIF) {
//            return TYPE_GIF;
//        }else if(position == TYPE_TEXT) {
//            return TYPE_TEXT;
//        }else if(position == 4) {
//            return 4;
//        }
//        return -1;
//    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        type = tempBean.getType();
        if(viewType == 0) {
            if ("video".equals(type)) {
                return new VideoHolder(inflater.inflate(R.layout.all_video_item, parent, false));
            } else if ("image".equals(type)) {
                return new ImageHolder(inflater.inflate(R.layout.all_image_item, parent, false));
            } else if ("gif".equals(type)) {
                return new GIFHolder(inflater.inflate(R.layout.all_gif_item, parent, false));
            } else if ("text".equals(type)) {
                return new HeaderHolder(inflater.inflate(R.layout.comment_header, parent, false));
            }
        }else if (viewType == 1) {
            return new CommentViewHolder(mContent, inflater.inflate(R.layout.item_hot_comment, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 0) {

            if ("video".equals(type)) {
                ((VideoHolder) holder).setData(mContent, tempBean);
            } else if ("image".equals(type)) {
                ((ImageHolder) holder).setData(mContent, tempBean);
            } else if ("gif".equals(type)) {
                ((GIFHolder) holder).setData(mContent, tempBean);
            } else if ("text".equals(type)) {
                ((HeaderHolder) holder).setData(tempBean);
            }
            ((BaseHolder)holder).setData(tempBean);
        }
        else if (getItemViewType(position) == 1) {
            int tempPosition = position - 1;
            ((CommentViewHolder) holder).setData(datas.get(tempPosition));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    //头部基类
    class BaseHolder extends RecyclerView.ViewHolder{
        ImageView ivHeadpic;
        TextView tvName;
        TextView tvTimeRefresh;
        TextView tvContext;
        TextView tvShenheDingNumber;
        TextView tvShenheCaiNumber;
        TextView tvPostsNumber;
        TextView tvCommentNumber;

        public BaseHolder(View itemView) {
            super(itemView);
            ivHeadpic = (ImageView) itemView.findViewById(R.id.iv_headpic);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvTimeRefresh = (TextView) itemView.findViewById(R.id.tv_time_refresh);
            tvContext = (TextView) itemView.findViewById(R.id.tv_content);
            tvShenheDingNumber = (TextView) itemView.findViewById(R.id.tv_shenhe_ding_number);
            tvShenheCaiNumber = (TextView) itemView.findViewById(R.id.tv_shenhe_cai_number);
            tvPostsNumber = (TextView) itemView.findViewById(R.id.tv_posts_number);
            tvCommentNumber = (TextView) itemView.findViewById(R.id.tv_comment_number);

        }

        public void setData(TempBean tempBean) {
            Glide.with(mContent).load(tempBean.getHeaderUrl()).into(ivHeadpic);//头像
            tvName.setText(tempBean.getUserName());//用户名
            tvTimeRefresh.setText(tempBean.getTime());//时间
//            tvContext.setText(tempBean.getContent());//文字内容
            tvShenheDingNumber.setText(tempBean.getDingNumber());//顶
            tvShenheCaiNumber.setText(tempBean.getCaiNumber());//踩
            tvPostsNumber.setText(tempBean.getShareNumber());//分享
            tvCommentNumber.setText(tempBean.getCommentNumber());//评论
        }
    }


    class GIFHolder extends BaseHolder {

        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_image_gif)
        ImageView ivImageGif;

        public GIFHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(TextDetailActivity mContent, TempBean tempBean) {
            //设置文本-所有的都有
            tvContent.setText(tempBean.getContent());//文字内容
            //下面是gif
            if (!TextUtils.isEmpty(tempBean.getGifUrl())) {
                Glide.with(mContent).load(tempBean.getGifUrl())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivImageGif);
            }

        }
    }


    //图片
    class ImageHolder extends BaseHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_image_icon)
        ImageView ivImageIcon;

        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        public void setData(TextDetailActivity mContent, TempBean tempBean) {
            //设置文本-所有的都有
            tvContent.setText(tempBean.getContent());//文字内容
            //图片特有的
            ivImageIcon.setImageResource(R.drawable.bg_item);
            if (!TextUtils.isEmpty(tempBean.getImageUrl())) {
                Glide.with(mContent).load(tempBean.getImageUrl()).into(ivImageIcon);
            }

        }
    }

    //文字头部
    class HeaderHolder extends BaseHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(TempBean tempBean) {
            tvContent.setText(tempBean.getContent());//文字内容
        }
    }
    //视频头部
    class VideoHolder extends BaseHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.jcv_videoplayer)
        JCVideoPlayerStandard jcvVideoplayer;

        private PlayTimeUtils utils;

        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            utils = new PlayTimeUtils();
        }

        public void setData(TextDetailActivity mContent, TempBean tempBean) {

            tvContent.setText(tempBean.getContent());//文字内容
            //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
            boolean setUp = jcvVideoplayer.setUp(tempBean.getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
            //加载图片
            if (setUp) {
                Glide.with(mContent).load(tempBean.getThumbnailUrl()).into(jcvVideoplayer.thumbImageView);
            }

        }
    }


    //评论
    class CommentViewHolder extends RecyclerView.ViewHolder {
        private final TextDetailActivity mContext;
        @BindView(R.id.iv_headpic)
        ImageView ivHeadpic;
        @BindView(R.id.iv_sex)
        ImageView ivSex;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_bottom_ding)
        ImageButton ivBottomDing;
        @BindView(R.id.tv_ding_number)
        TextView tvDingNumber;
        @BindView(R.id.iv_bottom_cai)
        ImageButton ivBottomCai;
        @BindView(R.id.tv_cai_number)
        TextView tvCaiNumber;
        @BindView(R.id.tv_user_number)
        TextView tvUserNumber;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_reply)
        LinearLayout llReply;

        CommentViewHolder(TextDetailActivity mContent, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.mContext = mContent;
        }

        public void setData(CommentsBean.NormalBean.ListBeanX listBean) {

            //设置用户数据
            CommentsBean.NormalBean.ListBeanX.UserBeanX user = listBean.getUser();
            //头像
            Glide.with(mContext).load(user.getProfile_image()).into(ivHeadpic);
            //性别
            String sex = user.getSex();
            if ("f".equals(sex)) {
                ivSex.setBackgroundResource(R.drawable.sex_women);
            } else if ("m".equals(sex)) {
                ivSex.setBackgroundResource(R.drawable.sex_men);
            }
            //用户名
            tvName.setText(user.getUsername());
            //关注用户的总数
            tvUserNumber.setText(user.getTotal_cmt_like_count());

            //顶的数量
            tvDingNumber.setText(listBean.getLike_count() + "");
            //评论内容
            tvContent.setText(listBean.getContent());

            List<CommentsBean.NormalBean.ListBeanX.PrecmtsBean> precmts = listBean.getPrecmts();

            llReply.removeAllViews();
            if (precmts != null && precmts.size() > 0) {
                for (int i = 0; i < precmts.size(); i++) {
                    //得到每条评论
                    CommentsBean.NormalBean.ListBeanX.PrecmtsBean precmtsBean = precmts.get(i);
                    /**
                     * 线性布局
                     */
                    LinearLayout myLinear = new LinearLayout(mContext);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
                    myLinear.setOrientation(LinearLayout.VERTICAL);//垂直

                    //水平子线性布局
                    LinearLayout myLinear1 = new LinearLayout(mContext);
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(-1, -2);
                    myLinear1.setOrientation(LinearLayout.HORIZONTAL);//垂直
                    myLinear.addView(myLinear1, params1);

                    //编号
                    TextView number = new TextView(mContext);
                    int num = i + 1;
                    number.setText(num + "");
                    number.setTextColor(Color.argb(100, 0, 0, 0));
                    number.setTextSize(DensityUtil.dp2px(mContext, 6));
                    number.setBackgroundResource(R.drawable.reply_comment_number);
                    number.setGravity(Gravity.CENTER);
                    int width = DensityUtil.dp2px(mContext, 3);
                    number.setPadding(width, width, width, width);
                    myLinear1.addView(number);

                    //用户名
                    TextView name = new TextView(mContext);
                    if ("".equals(precmtsBean.getUser().getUsername())) {
                        return;
                    }
                    name.setText(precmtsBean.getUser().getUsername() + "");
                    name.setTextColor(Color.argb(100, 0, 0, 255));
                    name.setTextSize(DensityUtil.dp2px(mContext, 6));
                    name.setPadding(DensityUtil.dp2px(mContext, 10), 0, 0, 0);
                    myLinear1.addView(name);

                    //text
                    TextView text = new TextView(mContext);
                    text.setText(" " + precmtsBean.getContent());
                    text.setTextSize(DensityUtil.dp2px(mContext, 6.5f));
                    number.setTextColor(Color.BLACK);
//
//                    textParams.setMargins(DensityUtil.dip2px(mContext,width + width),0,0,0);
                    text.setPadding(DensityUtil.dp2px(mContext, 10), 0, 0, 0);
                    myLinear.addView(text);

                    //分割线
                    TextView line = new TextView(mContext);
                    line.setBackgroundResource(R.color.line);
                    LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(-1, DensityUtil.dp2px(mContext, 1));
                    int width1 = DensityUtil.dp2px(mContext, 3);
                    lineParams.setMargins(0, width1, width1, 0);
                    myLinear.addView(line, lineParams);


                    llReply.addView(myLinear, params);
                }
            }

        }
    }
}
