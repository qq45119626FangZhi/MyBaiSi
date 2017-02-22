package com.fz.mybaisi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fz.mybaisi.R;
import com.fz.mybaisi.base.BaseFragment;
import com.fz.mybaisi.essence.fragment.EssenceFragment;
import com.fz.mybaisi.follow.FollowFragment;
import com.fz.mybaisi.me.fragment.MeFragment;
import com.fz.mybaisi.newspost.NewsPostFragment;
import com.fz.mybaisi.writepost.activity.SendActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fz.mybaisi.R.id.rb_follow;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_frame_layout)
    FrameLayout mainFrameLayout;
    @BindView(R.id.rb_write)
    ImageView rbWrite;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    //fragment集合
    private List<BaseFragment> fragments;
    //记录当前fragment角标
    private int position;
    //缓存的fragment
    private Fragment tempFragment;
    public FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //添加fragment
        initFragment();
        //设置RadioGroup的监听
        initListener();

    }

    //添加fragment
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new EssenceFragment());//精华
        fragments.add(new NewsPostFragment());//新帖
        fragments.add(new FollowFragment());//关注
        fragments.add(new MeFragment());//我的

    }

    //发帖子页面背景切换判断
    boolean flag = false;

    //监听radioButton
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_essence://精华
                        position = 0;
                        break;
                    case R.id.rb_newspost://新帖
                        position = 1;
                        break;
                    case rb_follow://关注
                        position = 2;
                        break;
                    case R.id.rb_me://我的
                        position = 3;
                        break;
                }
                //切换到当前要显示的fragment
                switchFragment(getFragment(position));
            }
        });

        //中间发帖子页面
        rbWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SendActivity.class));
            }
        });

        //默认选中首页
        rgMain.check(R.id.rb_essence);
    }

    /**
     * 获取当前的fragment
     *
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    //切换fragment
    private void switchFragment(Fragment currentFragemnt) {
        //判断缓存的fragmen 和当前的fragment，不一样
        if (tempFragment != currentFragemnt) {
            //Fragment管理
            ft = getSupportFragmentManager().beginTransaction();
            //当前的fragment没有添加
            if (!currentFragemnt.isAdded()) {
                //把上一个隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //添加当前的fragment,并提交
                ft.add(R.id.main_frame_layout, currentFragemnt).commit();
            } else {//当前的fragment已经添加过
                //把上一个隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //直接显示
                ft.show(currentFragemnt).commit();


            }
            //赋值
            tempFragment = currentFragemnt;
        }
    }

//    private int tempProgress;
//    private PlayTimeUtils utils = new PlayTimeUtils();

    //消息处理
    public Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what) {
//                /**
//                 * 音乐播放时间更新
//                 */
//                case MUSIC_PLAY:
//                    TextView obj = (TextView) msg.obj;
//                    tempProgress++;
//                    obj.setText(utils.stringForTime(tempProgress * 1000) + "");
//
//                    handler.removeMessages(MUSIC_PLAY);
//                    Message obtain = Message.obtain();
//                    obtain.what = MUSIC_PLAY;
//                    obtain.obj = obj;
//
//                    handler.sendMessageDelayed(obtain, 1000);
//
//                    break;
//            }
//
//        }
    };


    private boolean flag1 = false;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //返回true不能退出
        if (position != 0) {
            position = 0;
            rgMain.check(R.id.rb_essence);
            return true;
        } else if (!flag1) {
            flag1 = true;
            Toast.makeText(MainActivity.this, "再按一次退出界面", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag1 = false;
                }
            }, 2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }

    /**
     * 刷新
     */
    public void refresh() {
        /*finish();
        Intent intent = new Intent(RefreshActivityTest.this, RefreshActivityTest.class);
        startActivity(intent);*/

        onCreate(null);
    }

}
