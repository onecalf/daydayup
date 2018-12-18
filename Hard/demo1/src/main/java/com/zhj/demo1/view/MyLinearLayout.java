package com.zhj.demo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {


    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zhj","MyViewGroup event= ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zhj","MyViewGroup event= ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zhj","MyViewGroup event= ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zhj","MyViewGroup event= ACTION_CANCEL");
                break;
        }


        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//        Log.e("zhj2","MyLinearLayout onInterceptHoverEvent()");
//        if(event.getAction() == MotionEvent.ACTION_MOVE && event.getRawY() > 800){
//            Log.e("zhj","我拦截事件了");
//            return true;
//        }else {
//            return false;
//        }

        return super.onInterceptTouchEvent(event);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return true;
//    }
//



    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.parseColor("#aa3311"));
//        canvas.drawCircle(200,200,50,paint);

        Log.i("myLinearLayout","onDraw");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        paint.setColor(Color.parseColor("#aa3311"));
        canvas.drawCircle(400,200,50,paint);
        Log.i("myLinearLayout","dispatchDraw");
    }
}
