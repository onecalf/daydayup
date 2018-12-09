package com.zhj.demo2.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

public class MyView extends View {
    Scroller mScroller;


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    int lastX;
    int lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;// x 方向偏移量
                int offsetY = y - lastY;// y 方向偏移量


                //第 1 种方法
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);


//                //第 2 种方法
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                //第 3 种方法
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                //第 4 种方法
                ((View) getParent()).scrollBy(-offsetX, -offsetY);


//                mScroller.startScroll(startX, int startY, int dx, int dy)


                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY()
                );
                invalidate();
                break;

        }
        return true;
    }




    @Override
    public void computeScroll() {
        //判断 Scroller 是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过重绘来不断调用
            invalidate();
        }
    }
}
