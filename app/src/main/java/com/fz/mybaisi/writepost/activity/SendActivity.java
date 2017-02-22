package com.fz.mybaisi.writepost.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fz.mybaisi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendActivity extends AppCompatActivity {


    @BindView(R.id.iv_send_icon)
    ImageView ivSendIcon;
    @BindView(R.id.tv_send_video)
    TextView tvSendVideo;
    @BindView(R.id.tv_send_picture)
    TextView tvSendPicture;
    @BindView(R.id.tv_send_text)
    TextView tvSendText;
    @BindView(R.id.tv_send_voice)
    TextView tvSendVoice;
    @BindView(R.id.tv_send_link)
    TextView tvSendLink;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.llVideoPictureText)
    LinearLayout llVideoPictureText;
    @BindView(R.id.llVoiceLink)
    LinearLayout llVoiceLink;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        ButterKnife.bind(this);

        setAnimation();

    }

    /**
     * 进入动画
     */
    private void setAnimation() {
        llVoiceLink.setVisibility(View.INVISIBLE);
        llVideoPictureText.setVisibility(View.INVISIBLE);
        ivSendIcon.setVisibility(View.INVISIBLE);

        //初始化hanlder
        handler = new Handler();

        llVoiceLink.setVisibility(View.VISIBLE);
        llVoiceLink.startAnimation(AnimationUtils.loadAnimation(SendActivity.this, R.anim.mainactivity_push_bottom_in));

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                llVideoPictureText.setVisibility(View.VISIBLE);
                llVideoPictureText.startAnimation(AnimationUtils.loadAnimation(SendActivity.this, R.anim.mainactivity_push_bottom_in));
            }
        }, 200);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                ivSendIcon.setVisibility(View.VISIBLE);
                ivSendIcon.startAnimation(AnimationUtils.loadAnimation(SendActivity.this, R.anim.mainactivity_push_bottom_in));
            }
        }, 400);


    }

    /**
     * 退出动画
     */
    private void endAnimation() {

        ivSendIcon.startAnimation(AnimationUtils.loadAnimation(SendActivity.this, R.anim.mainactivity_push_bottom_out));
        ivSendIcon.setVisibility(View.GONE);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                llVideoPictureText.startAnimation(AnimationUtils.loadAnimation(SendActivity.this, R.anim.mainactivity_push_bottom_out));
                llVideoPictureText.setVisibility(View.GONE);
            }
        }, 200);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                llVoiceLink.startAnimation(AnimationUtils.loadAnimation(SendActivity.this, R.anim.mainactivity_push_bottom_out));
                llVoiceLink.setVisibility(View.GONE);

            }
        }, 400);

    }


    @OnClick({R.id.tv_send_video, R.id.tv_send_picture, R.id.tv_send_text, R.id.tv_send_voice, R.id.tv_send_link, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send_video:
                Toast.makeText(SendActivity.this, "发视频", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_send_picture:
                Toast.makeText(SendActivity.this, "发图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_send_text:
                Toast.makeText(SendActivity.this, "发段子", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_send_voice:
                Toast.makeText(SendActivity.this, "发声音", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_send_link:
                Toast.makeText(SendActivity.this, "发衔接", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_cancel:
                //退出动画
                endAnimation();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },2000);
                break;
        }
    }

}
