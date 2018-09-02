package com.onecalf.hard.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.onecalf.hard.util.ViewUtil;

/**
 * 字母索引侧边栏
 */
public class LetterSideBar extends View {
    private Paint mPaint;
    private int mTextSize;
    private int mTextColor;
    private int mSelectedTextColor;
    private String[] mLetters;
    private String mCurrentLetter;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mLetters = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
        };

        mTextSize = (int) ViewUtil.sp2px(context, 16);
        mTextColor = Color.BLUE;
        mSelectedTextColor = Color.RED;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float textWidth = mPaint.measureText("A");
        int width = (int) (getPaddingLeft() + getPaddingRight() + textWidth);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;

        for (int i = 0; i < mLetters.length; i++) {
            //知道每个字母的中心位置， 1
            int letterCenterY = i * itemHeight + itemHeight / 2 + getPaddingTop();

            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseLine = letterCenterY + dy;

            int textWidth = (int) mPaint.measureText(mLetters[i]);
            int x = getWidth() / 2 - textWidth / 2;

            if(mLetters[i].equals(mCurrentLetter)){
                mPaint.setColor(mSelectedTextColor);
                canvas.drawText(mLetters[i],x,baseLine,mPaint);
            }else {
                mPaint.setColor(mTextColor);
                canvas.drawText(mLetters[i],x,baseLine,mPaint);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //计算出当前触摸的字母
                int currentMoveY = (int) event.getY();//有可能为负数
                int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
                int currentIndex = currentMoveY / itemHeight;

                if(currentIndex < 0){
                    currentIndex = 0;
                }

                if(currentIndex > mLetters.length - 1){
                    currentIndex = mLetters.length - 1;
                }

                if(!mCurrentLetter.equals(mLetters[currentIndex])){
                    mCurrentLetter = mLetters[currentIndex];

                    if(mListener != null){
                        mListener.onTouch(mCurrentLetter);
                    }

                    invalidate();
                }
                break;

        }

        return true;
    }

    private LetterTouchListener mListener;

    public void setLetterTouchListener(LetterTouchListener listener){
        mListener = listener;
    }

    public interface LetterTouchListener{
        void onTouch(String letter);
    }
}






