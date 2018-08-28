package com.onecalf.hard.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.onecalf.hard.util.ViewUtil;

public class SwitchButton extends View {
    private int mDotColor = Color.parseColor("#ffffff");
    private int mOpenBackgroundColor = Color.parseColor("#51a938");
    private int mCloseBackgroundColor = Color.parseColor("#989898");
    private boolean mOpen = false;

    private Paint mBackgroundPaint;
    private Paint mDotPaint;

    private OnSwitchClickedListener mListener;
    private ValueAnimator animator = new ValueAnimator();

    //白色圆点的圆心坐标
    private int mDotX ;
    private int mDotY;

    //mDotX的取值范围
    private int mDotMinX;
    private int mDotMaxX;

    //drawRoundRect的横向和纵向半径
    private int mRoundCx;
    private int mRoundCy;


    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(mOpen ? mOpenBackgroundColor : mCloseBackgroundColor);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint.setColor(mDotColor);
        mDotPaint.setStyle(Paint.Style.FILL);

        animator.setDuration(180);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                mDotX = value;
                postInvalidate();
            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRoundCx = Math.min(w,h);
        mRoundCy = Math.min(w,h);

        mDotMinX = mRoundCx / 2;
        mDotMaxX = w - mDotMinX;

        mDotX = mOpen ? mDotMaxX : mDotMinX;
        mDotY = getHeight() / 2;

        animator.setIntValues(mDotMinX, mDotMaxX);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF(0,0,getWidth(),getHeight());

        mBackgroundPaint.setColor( mOpen ? mOpenBackgroundColor : mCloseBackgroundColor );
        canvas.drawRoundRect(rectF, mRoundCx, mRoundCy, mBackgroundPaint);

        int radius = Math.min(getWidth(), getHeight()) / 2 - 5;
        canvas.drawCircle(mDotX, mDotY, radius, mDotPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();

                if (x <= getWidth() && x > 0 && y <= getHeight() && y > 0) {
                    mOpen = !mOpen;
                    if(mOpen){
                        animator.setIntValues(mDotMinX, mDotMaxX);
                    }else {
                        animator.setIntValues(mDotMaxX, mDotMinX);
                    }

                    if (mListener != null) {
                        mListener.onSwitchClicked(mOpen);
                        animator.start();
                    }
                }

                break;
        }

        return true;
    }

    //设置监听器
    public void setSwitchOnClickedListener(OnSwitchClickedListener listener) {
        mListener = listener;
    }

    //是否处于打开状态
    public boolean isOpen(){
        return mOpen;
    }

    public void setOpen(boolean isOpen){
        mOpen = isOpen;
        invalidate();
    }

    public interface OnSwitchClickedListener {
        void onSwitchClicked(boolean isOpen);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int resultWidth = (int) ViewUtil.dp2px(getContext(),85);
        int resultHeight = (int) ViewUtil.dp2px(getContext(),40);
        if(widthMode == MeasureSpec.EXACTLY){
            resultWidth = MeasureSpec.getSize(widthMeasureSpec);
        }else {
            resultWidth = (int) ViewUtil.dp2px(getContext(),85);
        }

        if(heightMode == MeasureSpec.EXACTLY){
            resultHeight = MeasureSpec.getSize(heightMeasureSpec);
        }else {
            resultHeight = (int) ViewUtil.dp2px(getContext(),40);
        }


        setMeasuredDimension(resultWidth,resultHeight);
    }
}
