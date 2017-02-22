package com.fz.mybaisi.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fz.mybaisi.R;
import com.fz.mybaisi.follow.utils.Virtualkeyboard;
import com.fz.mybaisi.utils.PreferenceUtils;
import com.fz.mybaisi.utils.Util;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginActivity extends AppCompatActivity {

    public static final String QQ_LOGIN_KEY = "qq_login_key";
    private static final String RB_FOLLOW = "rb_follow";
    @BindView(R.id.login_tv_cancel)
    TextView loginTvCancel;
    @BindView(R.id.login_weixin_btn)
    Button loginWeixinBtn;
    @BindView(R.id.login_qq_btn)
    Button loginQqBtn;
    @BindView(R.id.login_other)
    TextView loginOther;
    @BindView(R.id.activity_login)
    RelativeLayout activityLogin;

    private static UserInfo mInfo;

    public static Tencent mTencent;
    private static boolean isServerSideLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //QQ的初始化
        mTencent = Tencent.createInstance("1105704769", this.getApplicationContext());
        mInfo = new UserInfo(this, mTencent.getQQToken());

        updateLoginButton();

    }

    @OnClick({R.id.login_tv_cancel, R.id.login_weixin_btn, R.id.login_qq_btn, R.id.login_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv_cancel:
                finish();
                break;
            case R.id.login_weixin_btn:
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_qq_btn:
                Toast.makeText(this, "QQ登录", Toast.LENGTH_SHORT).show();

                if (!mTencent.isSessionValid()) {
                    mTencent.loginServerSide(this, "all", loginListener);
                    isServerSideLogin = true;
                    Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                } else {
                    if (!isServerSideLogin) { // SSO模式的登陆，先退出，再进行Server-Side模式登陆
                        mTencent.logout(this);
                        mTencent.loginServerSide(this, "all", loginListener);
                        isServerSideLogin = true;
                        Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                        return;
                    }
                    mTencent.logout(this);
                    isServerSideLogin = false;
//                    updateUserInfo();
                    updateLoginButton();
                }

                break;
            case R.id.login_other:
//                Toast.makeText(this, "注册", Toast.LENGTH_SHORT).show();
                startPopupWindow();
                break;
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
//            updateUserInfo();
            updateLoginButton();
        }
    };

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                Util.showResultDialog(LoginActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                Util.showResultDialog(LoginActivity.this, "返回为空", "登录失败");
                return;
            }

            PreferenceUtils.putBoolean(LoginActivity.this,QQ_LOGIN_KEY,true);

//            Util.showResultDialog(LoginActivity.this, jsonResponse.toString(), "登录成功");
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            MainActivity.getInstance.refresh();
            finish();
//            // 有奖分享处理
//            handlePrizeShare();
            Log.e("TAG", "1111111111111111111LoginActivity" + jsonResponse);


            doComplete(jsonResponse);

        }

        protected void doComplete(JSONObject values) {
            finish();
        }

        @Override
        public void onError(UiError e) {
            Util.toastMessage(LoginActivity.this, "onError: " + e.errorDetail);
            Util.dismissDialog();
        }

        @Override
        public void onCancel() {
            Util.toastMessage(LoginActivity.this, "onCancel: ");
            Util.dismissDialog();
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }


//    private void updateUserInfo() {
//        if (mTencent != null && mTencent.isSessionValid()) {
//            IUiListener listener = new IUiListener() {
//
//                @Override
//                public void onError(UiError e) {
//
//                }
//
//                @Override
//                public void onComplete(final Object response) {
//                    Message msg = new Message();
//                    msg.obj = response;
//                    msg.what = 0;
//                    mHandler.sendMessage(msg);
//                    new Thread(){
//
//                        @Override
//                        public void run() {
//                            JSONObject json = (JSONObject)response;
//                            if(json.has("figureurl")){
//                                Bitmap bitmap = null;
//                                try {
//                                    bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
//                                } catch (JSONException e) {
//
//                                }
//                                Message msg = new Message();
//                                msg.obj = bitmap;
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
//                            }
//                        }
//
//                    }.start();
//                }
//
//                @Override
//                public void onCancel() {
//
//                }
//            };
//            mInfo = new UserInfo(this, mTencent.getQQToken());
//            mInfo.getUserInfo(listener);
//
//        } else {
////            mUserInfo.setText("");
////            mUserInfo.setVisibility(android.view.View.GONE);
////            mUserLogo.setVisibility(android.view.View.GONE);
//        }
//    }

    private void updateLoginButton() {
        if (mTencent != null && mTencent.isSessionValid()) {
            if (isServerSideLogin) {
                loginQqBtn.setTextColor(Color.BLUE);
                loginQqBtn.setText("登录");
            } else {
                loginQqBtn.setTextColor(Color.RED);
                loginQqBtn.setText("退出帐号");
            }
        } else {
            loginQqBtn.setTextColor(Color.BLUE);
            loginQqBtn.setText("登录");
        }
    }

//    Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0) {
//                JSONObject response = (JSONObject) msg.obj;
//                if (response.has("nickname")) {
//                    try {
//                        String nickname = response.getString("nickname");
//                        Log.e("TAG", "nickname" + nickname);
////                        mUserInfo.setVisibility(android.view.View.VISIBLE);
////                        mUserInfo.setText(response.getString("nickname"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }else if(msg.what == 1){
//                Bitmap bitmap = (Bitmap)msg.obj;
////                mUserLogo.setImageBitmap(bitmap);
////                mUserLogo.setVisibility(android.view.View.VISIBLE);
//            }
//        }
//
//    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG", "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 注册
     */
    private void startPopupWindow() {
        //加载布局
        View view = View.inflate(this, R.layout.popupwindow_regist, null);
        // 两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        window.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable drawable = new ColorDrawable(0x99FFFFFF);
        window.setBackgroundDrawable(drawable);

        //设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);

        TextView popup_regist_tv = (TextView) view.findViewById(R.id.popup_regist_tv);
        TextView popup_cancel_tv = (TextView) view.findViewById(R.id.popup_cancel_tv);

        //取消注册
        popup_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        //使用手机号登录
        popup_regist_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                finish();
            }
        });

        //显示位置
        window.showAtLocation(LoginActivity.this.findViewById(R.id.login_other),
                Gravity.BOTTOM, 0, Virtualkeyboard.getBottomStatusHeight(LoginActivity.this));


    }

}
