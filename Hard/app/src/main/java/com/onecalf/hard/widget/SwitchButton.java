package com.onecalf.hard.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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

public class SwitchButton extends View {
    private int mDotColor = Color.parseColor("#ffffff");
    private int mOpenBackgroundColor = Color.parseColor("#51a938");
    private int mCloseBackgroundColor = Color.parseColor("#989898");
    private boolean mOpen = false;

    private Paint mBackgroundPaint;
    private Paint mDotPaint;

    private OnSwitchClickedListener mListener;
    private ValueAnimator animator = new ValueAnimator();

    private int mDotX;
    private int mDotY;


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

        animator.setDuration(3000);
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
        animator.setIntValues(45, w - 45);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.top = 0;
        rectF.right = getWidth();
        rectF.bottom = getHeight();

        if(mOpen){
            mBackgroundPaint.setColor(mOpenBackgroundColor);
        }else {
            mBackgroundPaint.setColor(mCloseBackgroundColor);
        }
        canvas.drawRoundRect(rectF, 60, 60, mBackgroundPaint);

        if (mOpen) {
            mDotX = getWidth() - 45;
            mDotY = getHeight() / 2;
        } else {
            mDotX = 45;
            mDotY = getHeight() / 2;
        }

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
                    if (mListener != null) {
                        mListener.onSwitchClicked(mOpen);
                        animator.start();
                    }
                }

                break;
        }

        return true;
    }

    public void setSwitchOnClickedListener(OnSwitchClickedListener listener) {
        mListener = listener;
    }

    public interface OnSwitchClickedListener {
        void onSwitchClicked(boolean isOpen);
    }


}
