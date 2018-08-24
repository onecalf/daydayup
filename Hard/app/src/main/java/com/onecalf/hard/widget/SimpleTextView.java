package com.onecalf.hard.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.onecalf.hard.util.ViewUtil;

public class SimpleTextView extends View {
    private Paint mPaint;


    public SimpleTextView(Context context) {
        this(context, null);
    }

    public SimpleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff000000);
        mPaint.setTextSize(ViewUtil.sp2px(getContext(),16));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("你好北京,Hello,world",0,ViewUtil.getTextBaseLine(mPaint),mPaint);
    }
}
