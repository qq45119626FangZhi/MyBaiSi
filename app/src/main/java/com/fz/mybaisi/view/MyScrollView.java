package com.fz.mybaisi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @FileName:com.fz.mybaisi.view.MyScrollView.java
 * @author：方志
 * @date: 2016-12-30 19:28
 * @QQ：459119626
 * @微信：15549433151
 * @function <当前类的功能>
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            getParent().requestDisallowInterceptTouchEvent(false);
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
