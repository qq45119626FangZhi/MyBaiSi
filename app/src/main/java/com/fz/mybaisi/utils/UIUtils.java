package com.fz.mybaisi.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.fz.mybaisi.application.MyApplication;


/**
 * @function <常用UI工具类>
 * @FileName:com.fz.p2pinvest.utils.UIUtils.java
 * @author：方志
 * @date: 2016-12-02 10:16
 * @QQ：459119626
 * @微信：15549433151
 */

public class UIUtils {

    //上下文
    public static Context getContext(){
        return MyApplication.context;
    }

    //处理器
    public static Handler getHandler(){
        return MyApplication.handler;
    }

    //颜色
    public static int getColor(int colorId){
        return getContext().getResources().getColor(colorId);
    }

    //加载指定的ViewId的视图对象。并返回
    public static View getView(int viewId){
        return View.inflate(getContext(),viewId,null);
    }

    //将dp转换为xp
    public static int dp2px(double dp){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;

        return (int) (dp * density + 0.5);//加0.5实现四舍五入
    }

    //将xp转换为dp
    public static int px2dp(int xp){
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;

        return (int) (xp / density + 0.5);//加0.5实现四舍五入
    }

    //保证runnable中的操作在主线程中执行
    public static void runOnUiThread(Runnable runnable) {
        if(isInMainThread()){
            runnable.run();
        }else{
            UIUtils.getHandler().post(runnable);
        }
    }
    //判断当前线程是否是主线程
    private static boolean isInMainThread() {
        int currentThreadId = android.os.Process.myTid();
        return MyApplication.mainThreadId == currentThreadId;

    }

    public static String[] getStringArr(int strArrId){
        String[] stringArray = getContext().getResources().getStringArray(strArrId);
        return stringArray;
    }


    public static void toast(String s, boolean b) {
        Toast.makeText(UIUtils.getContext(), s, b? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
