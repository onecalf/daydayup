package com.onecalf.hard.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.onecalf.hard.R;

/**
 * 评分控件
 */
public class RatingView extends View {
    private Bitmap mStarNormalBitmap;
    private Bitmap mStarFocusBitmap;

    private int mGradeNumber;       //总分数
    private int mCurrentGrade;      //当前分数


    public RatingView(Context context) {
        this(context, null);
    }

    public RatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatingView);
        int starFocusId = ta.getResourceId(R.styleable.RatingView_starFocus, 0);
        int starNormalId = ta.getResourceId(R.styleable.RatingView_starNormal, 0);
        if (starFocusId == 0 || starNormalId == 0) {
            throw new RuntimeException("请设置属性 starFocus 和 starNormal");
        }

        mStarNormalBitmap = BitmapFactory.decodeResource(getResources(), starNormalId);
        mStarFocusBitmap = BitmapFactory.decodeResource(getResources(), starFocusId);
        mGradeNumber = ta.getInt(R.styleable.RatingView_gradeNumber, 0);

        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = mStarNormalBitmap.getWidth() * mGradeNumber;
        int height = mStarNormalBitmap.getHeight();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mGradeNumber; i++) {
            int x = i * mStarNormalBitmap.getWidth();

            if(mCurrentGrade > i){
                canvas.drawBitmap(mStarFocusBitmap, x, 0, null);
            }else {
                canvas.drawBitmap(mStarNormalBitmap, x, 0, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int currentGrade;
                float moveX = event.getX();
                if(moveX <=0 ){
                    currentGrade = 0;
                }else if(moveX >= getWidth()){
                    currentGrade = mGradeNumber;
                }else {
                    currentGrade = (int) (moveX / mStarNormalBitmap.getWidth() + 1);
                }

                if(currentGrade != mCurrentGrade){
                    mCurrentGrade = currentGrade;
                    invalidate();
                }

                break;
        }

        return true;
    }

    public int getCurrentGrade(){
        return mCurrentGrade;
    }

    public void setCurrentGrade(int currentGrade){
        mCurrentGrade = currentGrade;
        invalidate();
    }
}








