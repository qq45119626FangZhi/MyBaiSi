package com.fz.mybaisi.essence.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fz.mybaisi.R;
import com.fz.mybaisi.essence.adapter.viewholder.BaseViewHolder;
import com.fz.mybaisi.essence.adapter.viewholder.GifHolder;
import com.fz.mybaisi.essence.adapter.viewholder.ImageHolder;
import com.fz.mybaisi.essence.adapter.viewholder.TextHolder;
import com.fz.mybaisi.essence.adapter.viewholder.VideoHolder;
import com.fz.mybaisi.essence.bean.EssenceBean;
import com.fz.mybaisi.utils.PlayTimeUtils;

import java.util.List;

/**
 * @FileName:com.fz.mybaisi.essence.adapter.RecommendAdapter.java
 * @author：方志
 * @date: 2016-12-29 16:57
 * @QQ：459119626
 * @微信：15549433151
 * @function <推荐页面的适配器>
 */

public class EssenceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_VIDEO = 0;//视频
    private static final int TYPE_IMAGE = 1;//图片
    private static final int TYPE_TEXT = 2; //文字
    private static final int TYPE_GIF = 3; //GIF图片
    private static final int TYPE_AD = 4;//软件推广

    private final Context mContext;
    private final List<EssenceBean.ListBean> datas;
    private final LayoutInflater inflater;
    private PlayTimeUtils utils;

    public EssenceAdapter(Context mContext, List<EssenceBean.ListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
        utils = new PlayTimeUtils();
    }

    /**
     * 根据位置的到对应得类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //定义item类型
        int itemViewType = -1;
        //根据位置，从列表中得到一个数据对象的类型
        String type = datas.get(position).getType();
        if ("video".equals(type)) {//视频
            itemViewType = TYPE_VIDEO;
        } else if ("image".equals(type)) {//图片
            itemViewType = TYPE_IMAGE;
        } else if ("text".equals(type)) {//文本
            itemViewType = TYPE_TEXT;
        } else if ("gif".equals(type)) {//gif图片
            itemViewType = TYPE_GIF;
        }
//        else {
//            itemViewType = TYPE_AD;//广播
//        }

        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_VIDEO) {
            return new VideoHolder(inflater.inflate(R.layout.all_video_item, null));
        } else if (viewType == TYPE_IMAGE) {
            return new ImageHolder(inflater.inflate(R.layout.all_image_item, null));
        } else if (viewType == TYPE_TEXT) {
            return new TextHolder(inflater.inflate(R.layout.all_text_item, null));
        } else if (viewType == TYPE_GIF) {
            return new GifHolder(inflater.inflate(R.layout.all_gif_item, null));
        }
        return null;
//        else {
//            return new ADHolder(inflater.inflate(R.layout.all_ad_item, null));
//        }
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case  TYPE_VIDEO:
                VideoHolder videoHolder = (VideoHolder)viewHolder;
                videoHolder.setData(mContext, datas.get(position));

                break;
            case  TYPE_IMAGE:
                ImageHolder imageHolder = (ImageHolder)viewHolder;
                imageHolder.setData(mContext, datas.get(position));

                break;
            case  TYPE_TEXT:
                TextHolder textHolder = (TextHolder)viewHolder;
                textHolder.setData(mContext, datas.get(position));

                break;
            case  TYPE_GIF:
                GifHolder gifHolder = (GifHolder)viewHolder;
                gifHolder.setData(mContext, datas.get(position));

                break;
//            case  TYPE_AD:
//                ADHolder adHolder = (ADHolder)viewHolder;
//                adHolder.setData( datas.get(position));
//
//                break;
        }

        BaseViewHolder baseViewHolder = (BaseViewHolder) viewHolder;
        baseViewHolder.setData(mContext,datas.get(position));
    }

    public void refresh(){
        notifyDataSetChanged();
    }


//    //广告
//    class ADHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tv_context)
//        TextView tvContext;
//        @BindView(R.id.iv_image_icon)
//        ImageView ivImageIcon;
//        @BindView(R.id.btn_install)
//        Button btnInstall;
//
//        ADHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//            //中间公共部分 -所有的都有
//        }
//
//        public void setData(EssenceBean.ListBean listBean) {
//            tvContext.setText(listBean.getText());
//            Glide.with(mContext).load(listBean.getImage()).into(ivImageIcon);
//        }
//    }


//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//        switch (getItemViewType(position)) {
//            case TYPE_VIDEO:
//                ((VideoHolder) holder).setData(datas.get(position));
//                break;
//            case TYPE_IMAGE:
//                ((ImageHolder) holder).setData(datas.get(position));
//                break;
//            case TYPE_TEXT:
//                ((TextHolder) holder).setData(datas.get(position));
//                break;
//            case TYPE_GIF:
//                ((GifHolder) holder).setData(datas.get(position));
//                break;
//            case TYPE_AD:
//                ((ADHolder) holder).setData(datas.get(position));
//                break;
//
//        }
//    }

//    //GIF
//    class GifHolder extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.iv_headpic)
//        ImageView ivHeadpic;
//        @BindView(R.id.tv_name)
//        TextView tvName;
//        @BindView(R.id.tv_time_refresh)
//        TextView tvTimeRefresh;
//        @BindView(R.id.ll_user_info)
//        LinearLayout llVideoUserInfo;
//        @BindView(R.id.iv_right_more)
//        ImageView ivRightMore;
//        @BindView(R.id.tv_context)
//        TextView tvContext;
//        @BindView(R.id.iv_image_gif)
//        ImageView ivImageGif;
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
//        @BindView(ll_comment)
//        LinearLayout llComment;
//        @BindView(R.id.ll_ding)
//        LinearLayout llDing;
//
//        public GifHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//
//        public void setData(final EssenceBean.ListBean listBean) {
//            //头部
//            if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
//                Glide.with(mContext).load(listBean.getU().getHeader().get(0)).into(ivHeadpic);
//            }
//            if (listBean.getU() != null && listBean.getU().getName() != null) {
//                tvName.setText(listBean.getU().getName());
//            }
//            tvTimeRefresh.setText(listBean.getPasstime());
//
//            //设置文本-所有的都有
//            tvContext.setText(listBean.getText() + "_" + listBean.getType());
//            //下面是gif
//            if (listBean.getGif() != null && listBean.getGif() != null && listBean.getGif().getImages() != null) {
//                Glide.with(mContext).load(listBean.getGif().getImages()
//                        .get(0)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivImageGif);
//            }
//
//            //底部点赞，踩,转发
//            tvShenheDingNumber.setText(listBean.getUp() + "");
//            tvShenheCaiNumber.setText(listBean.getDown() + "");
//            tvPostsNumber.setText(listBean.getForward() + "");
//            tvCommentNumber.setText(listBean.getComment() + "");
//
//
//            //图片的点击事件
//            ivImageGif.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listBean != null) {
//                        //传递图片列表
//                        Intent intent = new Intent(mContext, ShowImageAndGifActivity.class);
//                        if ("gif".equals(listBean.getType())) {
//                            String url = listBean.getGif().getImages().get(0);
//                            intent.putExtra("url", url);
//                            mContext.startActivity(intent);
//                        }
//                    }
//                }
//            });
//
//            ivBottomComment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //评论详情页面地址
//                    String detailUrl = MyConstants.START + listBean.getId() + MyConstants.END;
//                    TempBean tempBean = new TempBean();
////                        tempBean.setHeaderUrl(listBean.getU().getHeader().get(0));//用户图像图片
////                        tempBean.setUserName(listBean.getU().getName());//用户名
////                        tempBean.setTime(listBean.getPasstime());//日期
////                        tempBean.setContent(listBean.getText());//内容
////                        tempBean.setDingNumber(listBean.getUp());//顶的数量
////                        tempBean.setCaiNumber(String.valueOf(listBean.getDown()));//踩的数量
////                        tempBean.setShareNumber(String.valueOf(listBean.getForward()));//分享的数量
////                        tempBean.setCommentNumber(listBean.getComment());//评论的数量
////                        tempBean.setType(listBean.getType());
//                    tempBean.setCommentsUrl(detailUrl);//评论详情页面的地址
//
//                    //进入新闻浏览页面
//                    Intent intent = new Intent(mContext, TextDetailActivity.class);
//                    //携带新闻页面的网址
//                    intent.putExtra(COMMENTS_PAGER, tempBean);
//                    mContext.startActivity(intent);
//                }
//            });
//        }
//    }
//
//    //    setData(EssenceBean.ListBean listBean)
//    //文本
//    class TextHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_headpic)
//        ImageView ivHeadpic;
//        @BindView(R.id.tv_name)
//        TextView tvName;
//        @BindView(R.id.tv_time_refresh)
//        TextView tvTimeRefresh;
//        @BindView(R.id.ll_user_info)
//        LinearLayout llVideoUserInfo;
//        @BindView(R.id.iv_right_more)
//        ImageView ivRightMore;
//        @BindView(R.id.tv_context)
//        TextView tvContext;
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
//        @BindView(ll_comments)
//        LinearLayout llComments;
//
//        public TextHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//
//        public void setData(final EssenceBean.ListBean listBean) {
//            //头部
//            if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
//                Glide.with(mContext).load(listBean.getU().getHeader().get(0)).into(ivHeadpic);
//            }
//            if (listBean.getU() != null && listBean.getU().getName() != null) {
//                tvName.setText(listBean.getU().getName());
//            }
//            tvTimeRefresh.setText(listBean.getPasstime());
//
//            //设置文本-
//            tvContext.setText(listBean.getText() + "_" + listBean.getType());
//
//            //底部点赞，踩,转发
//            tvShenheDingNumber.setText(listBean.getUp() + "");
//            tvShenheCaiNumber.setText(listBean.getDown() + "");
//            tvPostsNumber.setText(listBean.getForward() + "");
//            tvCommentNumber.setText(listBean.getComment() + "");
//
//            llComments.removeAllViews();
//            //评论
//            List<EssenceBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
//            //评论的点击事件，传递数据
//            if (top_comments != null && top_comments.size() > 0) {
//
//                for (int i = 0; i < top_comments.size(); i++) {
//                    TextView tv = new TextView(mContext);
//                    LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    tv.setTextSize(UIUtils.dp2px(10));
//                    tv.setPadding(UIUtils.dp2px(5), 0, UIUtils.dp2px(5), UIUtils.dp2px(0));
//
//                    final String username = top_comments.get(i).getU().getName();
//                    String content = " : " + top_comments.get(i).getContent();
//                    SpannableString spannableString = new SpannableString(username + content);
//                    //设置用户名前景色为蓝色
//                    spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, username.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), username.length() + 1,
//                            username.length() + content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置内容前景色为黑色
////
//                    tv.setText(spannableString);
//                    llComments.addView(tv, pm);
//
//                    tv.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(mContext, "username = " + username.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                }
//            }
//
//            //数据传递
//            ivBottomComment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //评论详情页面地址
//                    String detailUrl = MyConstants.START + listBean.getId() + MyConstants.END;
//                    TempBean tempBean = new TempBean();
//                    tempBean.setHeaderUrl(listBean.getU().getHeader().get(0));//用户图像图片
//                    tempBean.setUserName(listBean.getU().getName());//用户名
//                    tempBean.setTime(listBean.getPasstime());//日期
//                    tempBean.setContent(listBean.getText());//内容
//                    tempBean.setDingNumber(listBean.getUp());//顶的数量
//                    tempBean.setCaiNumber(String.valueOf(listBean.getDown()));//踩的数量
//                    tempBean.setShareNumber(String.valueOf(listBean.getForward()));//分享的数量
//                    tempBean.setCommentNumber(listBean.getComment());//评论的数量
//                    tempBean.setCommentsUrl(detailUrl);//评论详情页面的地址
//                    tempBean.setType(listBean.getType());
//
//                    //进入新闻浏览页面
//                    Intent intent = new Intent(mContext, TextDetailActivity.class);
//                    //携带新闻页面的网址
//                    intent.putExtra(COMMENTS_PAGER, tempBean);
//                    mContext.startActivity(intent);
//                }
//            });
//
//        }
//    }
//
//
//    //图片
//    class ImageHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_headpic)
//        ImageView ivHeadpic;
//        @BindView(R.id.tv_name)
//        TextView tvName;
//        @BindView(R.id.tv_time_refresh)
//        TextView tvTimeRefresh;
//        @BindView(R.id.ll_user_info)
//        LinearLayout llVideoUserInfo;
//        @BindView(R.id.iv_right_more)
//        ImageView ivRightMore;
//        @BindView(R.id.tv_context)
//        TextView tvContext;
//        @BindView(R.id.iv_image_icon)
//        ImageView ivImageIcon;
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
//        @BindView(ll_comment)
//        LinearLayout llComment;
//        @BindView(R.id.ll_ding)
//        LinearLayout llDing;
//
//        public ImageHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//
//        }
//
//        public void setData(final EssenceBean.ListBean listBean) {
//            //头部
//            if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
//                Glide.with(mContext).load(listBean.getU().getHeader().get(0)).into(ivHeadpic);
//            }
//            if (listBean.getU() != null && listBean.getU().getName() != null) {
//                tvName.setText(listBean.getU().getName());
//            }
//            tvTimeRefresh.setText(listBean.getPasstime());
//
//
//            //设置文本-所有的都有
//            tvContext.setText(listBean.getText() + "_" + listBean.getType());
//            //图片特有的
//            ivImageIcon.setImageResource(R.drawable.bg_item);
//            if (listBean.getImage() != null && listBean.getImage() != null && listBean.getImage().getSmall() != null) {
//                Glide.with(mContext).load(listBean.getImage()
//                        .getDownload_url().get(0)).placeholder(R.drawable.bg_item)
//                        .error(R.drawable.bg_item).diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(ivImageIcon);
//            }
//
//            //底部点赞，踩,转发
//            tvShenheDingNumber.setText(listBean.getUp() + "");
//            tvShenheCaiNumber.setText(listBean.getDown() + "");
//            tvPostsNumber.setText(listBean.getForward() + "");
//            tvCommentNumber.setText(listBean.getComment() + "");
//
//            //图片的点击事件
//            ivImageIcon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listBean != null) {
//                        //传递图片列表
//                        Intent intent = new Intent(mContext, ShowImageAndGifActivity.class);
//                        if ("image".equals(listBean.getType())) {
//                            String url = listBean.getImage().getBig().get(0);
//                            intent.putExtra("url", url);
//                            mContext.startActivity(intent);
//                        }
//                    }
//                }
//            });
//
//            ivBottomComment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //评论详情页面地址
//                    String detailUrl = MyConstants.START + listBean.getId() + MyConstants.END;
//                    TempBean tempBean = new TempBean();
////                        tempBean.setHeaderUrl(listBean.getU().getHeader().get(0));//用户图像图片
////                        tempBean.setUserName(listBean.getU().getName());//用户名
////                        tempBean.setTime(listBean.getPasstime());//日期
////                        tempBean.setContent(listBean.getText());//内容
////                        tempBean.setDingNumber(listBean.getUp());//顶的数量
////                        tempBean.setCaiNumber(String.valueOf(listBean.getDown()));//踩的数量
////                        tempBean.setShareNumber(String.valueOf(listBean.getForward()));//分享的数量
////                        tempBean.setCommentNumber(listBean.getComment());//评论的数量
////                        tempBean.setType(listBean.getType());
//                    tempBean.setCommentsUrl(detailUrl);//评论详情页面的地址
//
//                    //进入新闻浏览页面
//                    Intent intent = new Intent(mContext, TextDetailActivity.class);
//                    //携带新闻页面的网址
//                    intent.putExtra(COMMENTS_PAGER, tempBean);
//                    mContext.startActivity(intent);
//                }
//            });
//        }
//
//    }
//
//    //ViewHolder父类，拥有共同属性
////    class BaseViewHolder extends RecyclerView.ViewHolder {
////
////        ImageView ivHeadpic;
////        TextView tvName;
////        TextView tvTimeRefresh;
////        ImageView ivRightMore;
////        ImageView ivVideoKind;
////        TextView tvVideoKindText;
////        TextView tvShenheDingNumber;
////        TextView tvShenheCaiNumber;
////        TextView tvPostsNumber;
////        LinearLayout llDownload;
////
////        LinearLayout llVideoUserInfo;
////        RelativeLayout rlHolder;
////        ImageButton ivBottomDing;
////        ImageButton ivBottomCai;
////        LinearLayout llCai;
////        ImageButton ivBottomShare;
////        LinearLayout llShare;
////        ImageButton ivBottomComment;
////        TextView tvCommentNumber;
////
////        public BaseViewHolder(View view) {
////            super(view);
////            //公共的-头部
////            ivHeadpic = (ImageView) view.findViewById(R.id.iv_headpic);
////            tvName = (TextView) view.findViewById(R.id.tv_name);
////            tvTimeRefresh = (TextView) view.findViewById(R.id.tv_time_refresh);
////            ivRightMore = (ImageView) view.findViewById(R.id.iv_right_more);
//////            //公共部分-bottom
//////            ivVideoKind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
//////            tvVideoKindText = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
////            tvShenheDingNumber = (TextView) view.findViewById(R.id.tv_shenhe_ding_number);
////            tvShenheCaiNumber = (TextView) view.findViewById(R.id.tv_shenhe_cai_number);
////            tvCommentNumber = (TextView) view.findViewById(R.id.tv_comment_number);
////            tvPostsNumber = (TextView) view.findViewById(R.id.tv_posts_number);
////            ivBottomDing = (ImageButton) view.findViewById(R.id.iv_bottom_ding);
////            ivBottomCai = (ImageButton) view.findViewById(R.id.iv_bottom_cai);
////            ivBottomShare = (ImageButton) view.findViewById(R.id.iv_bottom_share);
////            ivBottomComment = (ImageButton) view.findViewById(R.id.iv_bottom_comment);
////
////            view.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
//////                    EssenceBean.ListBean listBean = datas.get(getLayoutPosition());
//////                    if(listBean != null) {
//////                        //传递图片列表
//////                        Intent intent = new Intent(mContext, ShowImageAndGifActivity.class);
//////                        if("gif".equals(listBean.getType())) {
//////                            String url = listBean.getGif().getImages().get(0);
//////                            intent.putExtra("url",url);
//////                            mContext.startActivity(intent);
//////                        }else if(listBean.getType().equals("image")){
//////                            String url = listBean.getImage().getBig().get(0);
//////                            intent.putExtra("url",url);
//////                            mContext.startActivity(intent);
//////                        }
//////
//////                    }
////                }
////            });
////        }
////
////        public void setData(EssenceBean.ListBean listBean) {
////            if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
////                Glide.with(mContext).load(listBean.getU().getHeader().get(0)).into(ivHeadpic);
////            }
////            if (listBean.getU() != null && listBean.getU().getName() != null) {
//////                tvName.setText(listBean.getU().getName() + "");
////                tvName.setText(listBean.getText() + "");
////            }
////
////            tvTimeRefresh.setText(listBean.getPasstime());
////
////
////            //设置点赞，踩,转发
////
////            tvShenheDingNumber.setText(listBean.getUp() + "");
////            tvShenheCaiNumber.setText(listBean.getDown() + "");
////            tvPostsNumber.setText(listBean.getForward() + "");
////            tvCommentNumber.setText(listBean.getComment() + "");
////
////        }
////    }
//
//    //视频
//    class VideoHolder extends RecyclerView.ViewHolder {
//        private final Context mContext;
//        @BindView(R.id.iv_headpic)
//        ImageView ivHeadpic;
//        @BindView(R.id.tv_name)
//        TextView tvName;
//        @BindView(R.id.tv_time_refresh)
//        TextView tvTimeRefresh;
//        @BindView(R.id.ll_user_info)
//        LinearLayout llVideoUserInfo;
//        @BindView(R.id.iv_right_more)
//        ImageView ivRightMore;
//        @BindView(R.id.tv_context)
//        TextView tvContext;
//        @BindView(R.id.jcv_videoplayer)
//        JCVideoPlayerStandard jcvVideoplayer;
//        @BindView(R.id.tv_play_nums)
//        TextView tvPlayNums;
//        @BindView(R.id.tv_video_duration)
//        TextView tvVideoDuration;
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
//        @BindView(ll_comment)
//        LinearLayout llComment;
//        @BindView(R.id.ll_ding)
//        LinearLayout llDing;
//        @BindView(ll_comments)
//        LinearLayout llComments;
//
//
//        public VideoHolder(Context mContext, View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            this.mContext = mContext;
//        }
//
//        public void setData(final EssenceBean.ListBean listBean) {
//            //头部
//            if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
//                Glide.with(mContext).load(listBean.getU().getHeader().get(0)).into(ivHeadpic);
//            }
//            if (listBean.getU() != null && listBean.getU().getName() != null) {
//                tvName.setText(listBean.getU().getName());
//            }
//            tvTimeRefresh.setText(listBean.getPasstime());
//
//            //设置视频
//            tvContext.setText(listBean.getText() + "_" + listBean.getType());
//            //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
//            boolean setUp = jcvVideoplayer.setUp(listBean.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
//            //加载图片
//            if (setUp) {
//                Glide.with(mContext).load(listBean.getVideo().getThumbnail().get(0)).into(jcvVideoplayer.thumbImageView);
//            }
//            tvPlayNums.setText(listBean.getVideo().getPlaycount() + "次播放");
//            tvVideoDuration.setText(utils.stringForTime(listBean.getVideo().getDuration() * 1000) + "");
//
//            //设置底部点赞，踩,转发
//            tvShenheDingNumber.setText(listBean.getUp() + "");
//            tvShenheCaiNumber.setText(listBean.getDown() + "");
//            tvPostsNumber.setText(listBean.getForward() + "");
//            tvCommentNumber.setText(listBean.getComment() + "");
//
//            llComments.removeAllViews();
//            //评论
//            List<EssenceBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
//            if (top_comments != null && top_comments.size() > 0) {
//                for (int i = 0; i < top_comments.size(); i++) {
//                    //得到每条具体评论
//                    EssenceBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
//                    /**
//                     * 线性布局
//                     */
//                    final LinearLayout myLinear = new LinearLayout(mContext);
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
//                    //用户名和评论
//                    LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    //用户
//                    TextView textView = new TextView(mContext);
//                    textView.setTextColor(Color.BLUE);
//                    textView.setText(topCommentsBean.getU().getName());
//                    myLinear.addView(textView, tvParams);
//                    //评论
//                    TextView textView1 = new TextView(mContext);
//                    textView1.setTextColor(Color.BLACK);
//                    textView1.setText(" : " + topCommentsBean.getContent());
//                    myLinear.addView(textView1, tvParams);
//
//                    llComments.addView(myLinear, params);
//                }
//            }
//            //评论的点击事件，传递数据
//            ivBottomComment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //评论详情页面地址
//                    String detailUrl = MyConstants.START + listBean.getId() + MyConstants.END;
//                    TempBean tempBean = new TempBean();
////                        tempBean.setHeaderUrl(listBean.getU().getHeader().get(0));//用户图像图片
////                        tempBean.setUserName(listBean.getU().getName());//用户名
////                        tempBean.setTime(listBean.getPasstime());//日期
////                        tempBean.setContent(listBean.getText());//内容
////                        tempBean.setDingNumber(listBean.getUp());//顶的数量
////                        tempBean.setCaiNumber(String.valueOf(listBean.getDown()));//踩的数量
////                        tempBean.setShareNumber(String.valueOf(listBean.getForward()));//分享的数量
////                        tempBean.setCommentNumber(listBean.getComment());//评论的数量
////                        tempBean.setType(listBean.getType());
//                    tempBean.setCommentsUrl(detailUrl);//评论详情页面的地址
//
//                    //进入新闻浏览页面
//                    Intent intent = new Intent(mContext, TextDetailActivity.class);
//                    //携带新闻页面的网址
//                    intent.putExtra(COMMENTS_PAGER, tempBean);
//                    mContext.startActivity(intent);
//                }
//            });
//
//            //分享
//            llShare.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showShare();
//                }
//            });
//
//
//        }
//
//        private void showShare() {
//            ShareSDK.initSDK(mContext);
//            OnekeyShare oks = new OnekeyShare();
//            //关闭sso授权
//            oks.disableSSOWhenAuthorize();
//
//            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
//            oks.setTitle("标题");
//            // titleUrl是标题的网络链接，QQ和QQ空间等使用
//            oks.setTitleUrl("http://sharesdk.cn");
//            // text是分享文本，所有平台都需要这个字段
//            oks.setText("我是分享文本");
//            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//            // url仅在微信（包括好友和朋友圈）中使用
//            oks.setUrl("http://sharesdk.cn");
//            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//            oks.setComment("我是测试评论文本");
//            // site是分享此内容的网站名称，仅在QQ空间使用
//            oks.setSite("百思不得姐");
//            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//            oks.setSiteUrl("http://sharesdk.cn");
//
//            // 启动分享GUI
//            oks.show(mContext);
//        }
//    }

}
