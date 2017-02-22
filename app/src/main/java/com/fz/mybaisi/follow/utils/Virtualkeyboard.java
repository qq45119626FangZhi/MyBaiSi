package com.fz.mybaisi.follow.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * @FileName:com.fz.shoppingmall.shoppingcart.utils.VirtualkeyboardHeight.java
 * @author：方志
 * @date: 2016-12-23 20:42
 * @QQ：459119626
 * @微信：15549433151
 * @function <获取屏幕和底部的高度，获取底部虚拟键盘的高度，获取屏幕的高度,宽度>
 */

public class Virtualkeyboard {
    // 获取屏幕和底部的高度
    public static int getDpi(Context context){

        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();

        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi=displayMetrics.heightPixels;
        }catch(Exception e){
            e.printStackTrace();
        }

        return dpi;
    }

    // 获取底部虚拟键盘的高度
    public static  int getBottomStatusHeight(Context context){
        int totalHeight = getDpi(context);

        int contentHeight = getScreenHeight(context);

        return totalHeight  - contentHeight;
    }

    // 获取屏幕的高度
    public static int getScreenHeight(Context context){

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics.heightPixels;
    }
    // 获取屏幕的宽度
    public static int getScreenWidth(Context context){

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics.widthPixels;
    }


}
