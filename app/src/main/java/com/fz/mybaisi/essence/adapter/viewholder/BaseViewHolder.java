package com.fz.mybaisi.essence.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fz.mybaisi.R;
import com.fz.mybaisi.activity.PersonalDetailActivity;
import com.fz.mybaisi.activity.TextDetailActivity;
import com.fz.mybaisi.essence.bean.EssenceBean;
import com.fz.mybaisi.essence.bean.TempBean;
import com.fz.mybaisi.utils.MyConstants;
import com.fz.mybaisi.utils.UIUtils;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.fz.mybaisi.R.id.tv_content;

/**
 * @FileName:com.fz.mybaisi.essence.adapter.viewholder.BaseViewHolder.java
 * @author：方志
 * @date: 2017-01-04 16:10
 * @QQ：459119626
 * @微信：15549433151
 * @function <分类型ViewHolder基类，共同有的属性>
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public static final String COMMENTS_PAGER = "comments_pager";
    public static final String PERSONAL_PAGER = "personal_pager";

    private ImageView ivHeadpic;
    private TextView tvName;
    private TextView tvTimeRefresh;
    private ImageView ivRightMore;
    private ImageView ivVideoKind;
    private TextView tvVideoKindText;
    private TextView tvShenheDingNumber;
    private TextView tvShenheCaiNumber;
    private TextView tvPostsNumber;
    private TextView tvContent;

    LinearLayout llDing;
    private ImageButton ivBottomDing;
    private LinearLayout llCai;
    private ImageButton ivBottomCai;

    private LinearLayout llShare;
    private TextView tvCommentNumber;

    LinearLayout llComments;

    private Context mContext;

    public BaseViewHolder(View view) {
        super(view);

        ivHeadpic = (ImageView) view.findViewById(R.id.iv_headpic);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvTimeRefresh = (TextView) view.findViewById(R.id.tv_time_refresh);
        ivRightMore = (ImageView) view.findViewById(R.id.iv_right_more);
        tvContent = (TextView) view.findViewById(tv_content);
        //公共部分-bottom
        tvShenheDingNumber = (TextView) view.findViewById(R.id.tv_shenhe_ding_number);
        tvShenheCaiNumber = (TextView) view.findViewById(R.id.tv_shenhe_cai_number);
        tvCommentNumber = (TextView) view.findViewById(R.id.tv_comment_number);
        tvPostsNumber = (TextView) view.findViewById(R.id.tv_posts_number);

        llShare = (LinearLayout) view.findViewById(R.id.ll_share);
        llComments = (LinearLayout) view.findViewById(R.id.ll_comments);

    }


    public void setData(final Context mContext, EssenceBean.ListBean listBean) {
        this.mContext = mContext;

        //头部
        Glide.with(mContext).load(listBean.getU().getHeader().get(0)).into(ivHeadpic);
        tvName.setText(listBean.getU().getName());
        tvTimeRefresh.setText(listBean.getPasstime());//发表日期

        //进入个人详情页面
        toPersonalDetailPager(listBean);


        //底部点赞，踩,转发
        tvShenheDingNumber.setText(listBean.getUp() + "");
        tvShenheCaiNumber.setText(listBean.getDown() + "");
        tvPostsNumber.setText(listBean.getForward() + "");
        tvCommentNumber.setText(listBean.getComment() + "");
        //评论
        comment(mContext, listBean);
        //分享
        share(listBean);
        //进入详情页面
        toDetailPagers(listBean);


    }

    /**
     * 进入个人详情页面
     * @param listBean
     */
    private void toPersonalDetailPager(final EssenceBean.ListBean listBean) {
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取个人页面的url
                String personalUrl = MyConstants.USER_START + listBean.getU().getUid() + MyConstants.USER_END;

                TempBean tempBean = new TempBean();
                tempBean.setUserName(listBean.getU().getName());//用户名
                tempBean.setHeaderUrl(listBean.getU().getHeader().get(0));//图像地址
                tempBean.setPersonalUrl(personalUrl);

                //进入新闻浏览页面
                Intent intent = new Intent(mContext, PersonalDetailActivity.class);
                //携带新闻页面的网址
                intent.putExtra(PERSONAL_PAGER, tempBean);
                mContext.startActivity(intent);

            }
        });
    }

    /**
     * //进入详情页面
     */
    private void toDetailPagers(final EssenceBean.ListBean listBean) {
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //评论详情页面地址
                String detailUrl = MyConstants.START + listBean.getId() + MyConstants.END;

                TempBean tempBean = new TempBean();
                tempBean.setHeaderUrl(listBean.getU().getHeader().get(0));//用户图像图片
                tempBean.setUserName(listBean.getU().getName());//用户名
                tempBean.setTime(listBean.getPasstime());//日期
                tempBean.setDingNumber(listBean.getUp());//顶的数量
                tempBean.setCaiNumber(String.valueOf(listBean.getDown()));//踩的数量
                tempBean.setShareNumber(String.valueOf(listBean.getForward()));//分享的数量
                tempBean.setCommentNumber(listBean.getComment());//评论的数量
                tempBean.setCommentsUrl(detailUrl);//评论详情页面的地址
                tempBean.setContent(listBean.getText());
                tempBean.setType(listBean.getType());

                switch (listBean.getType()) {
                    case "video":
                        tempBean.setVideoUrl(listBean.getVideo().getVideo().get(0));
                        tempBean.setThumbnailUrl(listBean.getVideo().getThumbnail().get(0));
                        break;

                    case "text":
                        tempBean.setContent(listBean.getText());//内容
                        break;

                    case "image":
                        tempBean.setImageUrl(listBean.getImage().getBig().get(0));
                        break;

                    case "gif":
                        tempBean.setGifUrl(listBean.getGif().getImages().get(0));
                        break;
                }

                //进入新闻浏览页面
                Intent intent = new Intent(mContext, TextDetailActivity.class);
                //携带新闻页面的网址
                intent.putExtra(COMMENTS_PAGER, tempBean);
                mContext.startActivity(intent);
            }
        }
    );
}

    /**
     * 分享
     * @param listBean
     */
    private void share(final EssenceBean.ListBean listBean) {
        llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSDK.initSDK(mContext);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
                oks.setTitle("标题");
                // titleUrl是标题的网络链接，QQ和QQ空间等使用
//                oks.setTitleUrl("http://sharesdk.cn");
                oks.setTitleUrl(listBean.getShare_url());
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite("百思不得姐");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");


                // 在九宫格设置自定义的图标
                Bitmap logo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.copy_press);
                String label = "复制衔接";
                View.OnClickListener listener = new View.OnClickListener() {
                   public void onClick(View v) {
                        Toast.makeText(mContext, "我是自定义的", Toast.LENGTH_SHORT).show();
                    }
                };
                oks.setCustomerLogo(logo, label, listener);



                // 启动分享GUI
                oks.show(mContext);
            }
        });
    }

    /**
     * 评论
     *
     * @param mContext
     * @param listBean
     */
    private void comment(final Context mContext, EssenceBean.ListBean listBean) {
        llComments.removeAllViews();
        //评论
        List<EssenceBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
        //评论的点击事件，传递数据
        if (top_comments != null && top_comments.size() > 0) {

            for (int i = 0; i < top_comments.size(); i++) {
                TextView tv = new TextView(mContext);
                LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tv.setTextSize(UIUtils.dp2px(6));
                tv.setPadding(UIUtils.dp2px(5), 0, UIUtils.dp2px(5), 0);

                final String username = top_comments.get(i).getU().getName();
                String content = " : " + top_comments.get(i).getContent();
                SpannableString spannableString = new SpannableString(username + content);
                //设置用户名前景色为蓝色
                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, username.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), username.length() + 1,
                        username.length() + content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置内容前景色为黑色
//
                tv.setText(spannableString);
                llComments.addView(tv, pm);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "username = " + username.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }

}