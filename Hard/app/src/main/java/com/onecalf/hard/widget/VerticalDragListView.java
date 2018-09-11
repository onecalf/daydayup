package com.onecalf.hard.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

public class VerticalDragListView extends FrameLayout{
    // 系统为我们写好的工具类
    private ViewDragHelper mDragHelper;
    private View mDragListView;
    private int mMenuHeight;
    private boolean mMenuOpened = false;//菜单是否打开


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
                    mMenuOpened = true;
                }else {//关闭，滚动到上面
                    mDragHelper.settleCapturedViewAt(0,0);
                    mMenuOpened = false;
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

    private float mDownY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if(mMenuOpened){
            return true;
        }

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY = event.getY();
                //让DragHelper拿到一个完整的事件
                mDragHelper.processTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                if(moveY - mDownY > 0 && !canChildScrollUp() ) {
                    //向下滑动并且滚动到了底部，拦截,不让ListView处理
                    return true;
                }
                break;
        }

        return super.onInterceptHoverEvent(event);
    }

    //判断列表是否滚动了最顶部
    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mDragListView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mDragListView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mDragListView, -1) || mDragListView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mDragListView, -1);
        }
    }
}

















