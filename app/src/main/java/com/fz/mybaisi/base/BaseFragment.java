package com.fz.mybaisi.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @FileName:com.fz.shoppingmall.base.BaseFragment.java
 * @author：方志
 * @date: 2016-12-19 21:11
 * @QQ：459119626
 * @微信：15549433151
 * @function <FragmentJ基类>
 */

public abstract class BaseFragment extends Fragment {

    //上下文
    public Context mContext;

    public BaseFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    //初始化布局，由子类实现
    public abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //添加数据，由子类实现
    public abstract void initData();
}
