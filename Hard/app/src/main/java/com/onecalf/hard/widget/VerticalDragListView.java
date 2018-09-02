package com.onecalf.hard.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class VerticalDragListView extends FrameLayout{
    // 系统为我们写好的工具类
    private ViewDragHelper mDragHelper;


    public VerticalDragListView(@NonNull Context context) {
        this(context,null);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mDragHelper = ViewDragHelper.create(this,mDragHelperCallback);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            //指定该子View是否可以拖动
            return true;
        }
    };




}

















