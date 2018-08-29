package com.onecalf.hard.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.onecalf.hard.R;
import com.onecalf.hard.util.ViewUtil;

public class StepView extends View {
    private int mStepBorderWidth;
    private int mOuterColor = Color.BLUE;
    private int mInnerColor = Color.RED;
    private int mStepTextSize = 20; //px
    private int mStepTextColor = Color.RED;

    private Paint mOutPaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;

    //当前的步数
    private int mCurrentStep = 30;
    //总的步数
    private int mStepMax = 100;


    public StepView(Context context) {
        this(context,null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StepView);
        mOuterColor = ta.getColor(R.styleable.StepView_outerColor,mOuterColor);
        mInnerColor = ta.getColor(R.styleable.StepView_innerColor,mInnerColor);
        mStepBorderWidth = (int) ta.getDimension(R.styleable.StepView_borderWidth,mStepBorderWidth);
        mStepTextSize = ta.getDimensionPixelSize(R.styleable.StepView_stepTextSize,mStepTextSize);
        mStepTextColor = ta.getColor(R.styleable.StepView_stepTextColor,mStepTextColor);
        ta.recycle();


        mOutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutPaint.setColor(mOuterColor);
        mOutPaint.setStrokeWidth(mStepBorderWidth);
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setStrokeCap(Paint.Cap.ROUND); //设置线条端点形状的方法

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setStrokeWidth(mStepBorderWidth);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //确保是个正方形
        int r = Math.min(width,height);
        setMeasuredDimension(r,r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1 画外弧
        //半径为圆弧中点的位置
        RectF rectF = new RectF(mStepBorderWidth / 2,mStepBorderWidth / 2,getWidth() - mStepBorderWidth  ,getHeight() - mStepBorderWidth);

        canvas.drawArc(rectF,135,270,false,mOutPaint);

        //2 画内弧
        if(mStepMax == 0){
            return;
        }

        float sweepAngle = (float) mCurrentStep / mStepMax;
        canvas.drawArc(rectF,135,270 * sweepAngle,false,mInnerPaint);


        //3 画文字
        String stepText = mCurrentStep + "";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText,0,stepText.length(),textBounds);
        int dx = getWidth() / 2 - textBounds.width() / 2;
        canvas.drawText(stepText,dx,getHeight() / 2,mTextPaint);
    }

    public void setCurrentStep(int currentStep){
        mCurrentStep = currentStep;
        invalidate();
    }


}










