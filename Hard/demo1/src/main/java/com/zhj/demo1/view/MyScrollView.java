package com.zhj.demo1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        Log.e("zhj","result=" + result);

        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zhj2","MyScrollView event= ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zhj2","MyScrollView event= ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zhj2","MyScrollView event= ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zhj2","MyScrollView event= ACTION_CANCEL");
                break;
        }


        return super.onTouchEvent(ev);
    }
}
