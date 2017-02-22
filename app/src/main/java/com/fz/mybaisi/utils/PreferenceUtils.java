package com.fz.mybaisi.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @FileName:com.fz.mybaisi.utils.PreferenceUtils.java
 * @author：方志
 * @date: 2017-01-06 09:02
 * @QQ：459119626
 * @微信：15549433151
 * @function <sp储存>
 */

public class PreferenceUtils {

    /**
     * 保持boolean类型数据
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("mysp",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();

    }

    /**
     * 得到boolean类型数据
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("mysp",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }



}
