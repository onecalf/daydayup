package com.zhj.demo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton extends Button {


    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zhj2","MyButton event= ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zhj2","MyButton event= ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zhj2","MyButton event= ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zhj2","MyButton event= ACTION_CANCEL");
                break;
        }


        return super.dispatchTouchEvent(event);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                Log.e("zhj2","MyButton onTouchEvent event= ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.e("zhj2","MyButton onTouchEvent event= ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e("zhj2","MyButton onTouchEvent event= ACTION_UP");
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                Log.e("zhj2","MyButton onTouchEvent event= ACTION_CANCEL");
//                break;
//        }
//
//        return true;
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.BLUE);
//        canvas.drawCircle(80,80,40,paint);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
//        paint.setColor(Color.BLUE);
//        canvas.drawCircle(80,80,40,paint);
    }
}
