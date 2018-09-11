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
    private View mDragListView;
    private int mMenuHeight;


    public VerticalDragListView(@NonNull Context context) {
        this(context,null);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //1.创建一个实例
        mDragHelper = ViewDragHelper.create(this,mDragHelperCallback);

    }

    //3. 重写onTouchEvent，把事件交给mDragHelper处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    //2. 拖动我们的View
    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            //指定该子View是否可以拖动
            //返回true表示所有的子view都可以拖动
//            return true;

            //只可以拖动mDragListView
            return mDragListView == child;
        }

        //垂直拖动的位置
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(top < 0){
                top = 0;
            }else if(top > mMenuHeight){
                top = mMenuHeight;
            }

            return top;
        }



        //水平拖动的位置,默认水平不能拖动
//        @Override
//        public int clampViewPositionHorizontal(View child, int left, int dx) {
//            return super.clampViewPositionHorizontal(child, left, dx);
//        }

        //手指松开时调用这个方法
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if(releasedChild == mDragListView){
                int top = mDragListView.getTop();
                if(top > mMenuHeight / 2){//打开，滚动到下面
                    mDragHelper.settleCapturedViewAt(0,mMenuHeight);
                }else {//关闭，滚动到上面
                    mDragHelper.settleCapturedViewAt(0,0);
                }

                invalidate();
            }
        }
    };

    @Override
    public void computeScroll() {
        super.computeScroll();
        //响应滚动
        if(mDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if(childCount != 2 ){
            throw new RuntimeException("must have two view");
        }

        mDragListView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取后面view的高度
        mMenuHeight = getChildAt(0).getMeasuredHeight();
    }

}

















