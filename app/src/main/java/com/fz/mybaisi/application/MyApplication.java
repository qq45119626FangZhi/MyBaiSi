package com.fz.mybaisi.application;


import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.sharesdk.framework.ShareSDK;
import okhttp3.OkHttpClient;


/**
 * 作者：尚硅谷-杨光福 on 2016/12/13 09:23
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：Application
 */
public class MyApplication extends Application {

    public static Context context;
    public static Handler handler;
    public static Thread mainThread;
    public static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xUtils3
//        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

        //初始化极光推送
//        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);

        //监听软件异常崩溃处理
//        CrashHandler catchHandler = CrashHandler.getInstance();
//        catchHandler.init(getApplicationContext());
//        CrashHandler.getCrashHandler().init();
//        //初始化ShareSDK
//        ShareSDK.initSDK(this);

        //okhttp
        initOkhttpUtils();

        //初始化ShareSDK
        ShareSDK.initSDK(this);

        //初始化
        context = this.getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
    }

    private void initOkhttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
