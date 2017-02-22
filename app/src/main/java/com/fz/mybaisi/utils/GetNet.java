package com.fz.mybaisi.utils;

import com.alibaba.fastjson.JSON;
import com.fz.mybaisi.interfaces.OnGetNetListener;
import com.fz.mybaisi.newspost.bean.MusicBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @FileName:com.fz.mybaisi.utils.GetNet.java
 * @author：方志
 * @date: 2016-12-29 17:00
 * @QQ：459119626
 * @微信：15549433151
 * @function <联网请求>
 */

public class GetNet {

    public static void getNetData(String url, Map<String, String> map, final OnGetNetListener onGetNetListener) {

        OkHttpUtils.get().url(url).build()
                .execute(new StringCallback() {

                    @Override
                    public void onResponse(String response, int id) {
                        if (onGetNetListener != null) {
                            onGetNetListener.onSuccess(response);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (onGetNetListener != null) {
                            onGetNetListener.onError(e);
                        }
                    }
                });

    }

    public static List<MusicBean.ListBean> getData(final String url) {


        List<MusicBean.ListBean> list = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body().string();
                MusicBean musicBean = JSON.parseObject(json, MusicBean.class);
                return musicBean.getList();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }
}