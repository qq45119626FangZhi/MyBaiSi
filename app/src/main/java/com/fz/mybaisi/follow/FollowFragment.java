package com.fz.mybaisi.follow;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fz.mybaisi.R;
import com.fz.mybaisi.activity.LoginActivity;
import com.fz.mybaisi.base.BaseFragment;
import com.fz.mybaisi.qrcode.activity.CaptureActivity;
import com.fz.mybaisi.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @FileName:com.fz.mybaisi.essence.EssenceFragment.java
 * @author：方志
 * @date: 2016-12-29 11:49
 * @QQ：459119626
 * @微信：15549433151
 * @function <新帖页面的fragment>
 */

public class FollowFragment extends BaseFragment {


    @BindView(R.id.follow_ib_examine)
    ImageButton followIbExamine;
    @BindView(R.id.follow_login_btn)
    Button followLoginBtn;
    @BindView(R.id.follow_regist_btn)
    Button followRegistBtn;
    @BindView(R.id.follow_qr_code)
    ImageButton followQrCode;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.ibn_look)
    Button ibnLook;
    @BindView(R.id.ll_look)
    LinearLayout llLook;



    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_follow_item, null);
        ButterKnife.bind(this, view);
        showLoginLook();

        return view;
    }

    @Override
    public void initData() {

    }

    /**
     * 判断是否有登录，显示不同界面
     */
    private void showLoginLook() {
        boolean aBoolean = PreferenceUtils.getBoolean(mContext, LoginActivity.QQ_LOGIN_KEY);
        if(aBoolean) {
            llLook.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
        }else {
            llLogin.setVisibility(View.VISIBLE);
            llLook.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.follow_ib_examine, R.id.follow_login_btn, R.id.follow_regist_btn, R.id.follow_qr_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.follow_ib_examine:
                Toast.makeText(mContext, "进入推荐关注页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.follow_login_btn:
//                Toast.makeText(mContext, "进入登录", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.follow_regist_btn:
//                Toast.makeText(mContext, "进入注册", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.follow_qr_code:
//                Toast.makeText(mContext, "二维码扫描", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(mContext, CaptureActivity.class), 0);
                break;
            case R.id.ibn_look:
                Toast.makeText(mContext, "去看看", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
