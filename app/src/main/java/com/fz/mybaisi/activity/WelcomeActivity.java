package com.fz.mybaisi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.fz.mybaisi.R;

import butterknife.ButterKnife;

/**
 * 欢迎界面
 */
public class WelcomeActivity extends AppCompatActivity {

    //消息处理器
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        //3秒后进入主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMain();
            }
        },3000);
    }

    //进入主界面
    private void startMain() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    /**
     * 触摸屏幕快速进入主界面
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            startMain();
            return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 移除所有消息
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
