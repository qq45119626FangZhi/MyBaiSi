package com.fz.mybaisi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fz.mybaisi.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends AppCompatActivity {

    @BindView(R.id.regist_cancel_tv)
    TextView registCancelTv;
    @BindView(R.id.regist_exist_tv)
    TextView registExistTv;
    @BindView(R.id.regist_name_et)
    EditText registNameEt;
    @BindView(R.id.regist_possword_et)
    EditText registPosswordEt;
    @BindView(R.id.regist_ibt)
    ImageButton registIbt;
    @BindView(R.id.ll_regist)
    LinearLayout llRegist;
    @BindView(R.id.login_name_et)
    EditText loginNameEt;
    @BindView(R.id.login_possword_et)
    EditText loginPosswordEt;
    @BindView(R.id.login_ibt)
    ImageButton loginIbt;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.weixin_btn)
    Button weixinBtn;
    @BindView(R.id.qq_btn)
    Button qqBtn;

    private Tencent mTencent;
    private UserInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        //QQ的初始化
        mTencent = Tencent.createInstance("1105704769", this.getApplicationContext());
        mInfo = new UserInfo(this, mTencent.getQQToken());

    }


    private boolean isLogin;
    @OnClick({R.id.regist_cancel_tv, R.id.regist_exist_tv, R.id.regist_ibt, R.id.login_ibt,R.id.weixin_btn, R.id.qq_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regist_cancel_tv:
                finish();
                break;
            case R.id.regist_exist_tv:
                if (!isLogin) {
                    isLogin = true;
                    registExistTv.setText("已有账号？");
                    login();

                } else {
                    isLogin = false;
                    registExistTv.setText("注册账号");
                    regist();
                }
                break;
            case R.id.regist_ibt:
                Toast.makeText(RegistActivity.this, "注册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_ibt:
                Toast.makeText(RegistActivity.this, "登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.weixin_btn:
                Toast.makeText(RegistActivity.this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.qq_btn:
                Toast.makeText(RegistActivity.this, "QQ登录", Toast.LENGTH_SHORT).show();

                mTencent.login(this, "all", loginListener);

                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        llRegist.setVisibility(View.VISIBLE);
        llLogin.setVisibility(View.GONE);
    }

    /**
     * 注册
     */
    private void regist() {
        llRegist.setVisibility(View.GONE);
        llLogin.setVisibility(View.VISIBLE);
    }


    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {

            initOpenidAndToken(values);

            //下面的这个必须放到这个地方，要不然就会出错
            updateUserInfo();

        }
    };

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onCancel() {

                }
            };

            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        } else {

        }
    }

    public void initOpenidAndToken(JSONObject jsonObject) {
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
            e.getMessage();
        }
    }


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject response = (JSONObject) msg.obj;
            //设置 用户名 密码
//        setAvator(imgurl);
//            loginNameEt.setText(nickname);
//            loginPosswordEt.setText(password);
//            //保存
//            PreferenceUtils.putString(RegistActivity.this, MyConstants.USER_NAME, nickname);
//            PreferenceUtils.putString(RegistActivity.this,MyConstants.USER_PASSWORD, password);
////        PreferenceUtils.putString(this,
////                MyConstants.IMAGE_URL,imgurl);
//            //设置返回的结果
//            Intent intent = getIntent();
//            intent.putExtra("screen_name",nickname);
//            intent.putExtra("screen_password",password);
//            setResult(RESULT_OK,intent);
//            Log.e("TAG", "nickname=========" + nickname);

//            finish();

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("ruolan", "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
            loginNameEt.setText(data.getStringExtra("screen_name"));
            loginPosswordEt.setText(data.getStringExtra("screen_password"));

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;

            if (jsonResponse == null && jsonResponse.length() == 0) {
                return;
            }
            doComplete((JSONObject) response);
        }

        @Override
        public void onError(UiError e) {

        }

        @Override
        public void onCancel() {

        }

        protected void doComplete(JSONObject values) {

        }
    }


}
