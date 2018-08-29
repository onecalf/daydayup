package com.onecalf.hard.util;

import android.content.Context;
import android.graphics.Paint;
import android.util.TypedValue;

public class ViewUtil {

    public static float sp2px(Context context,float spValue){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,context.getResources().getDisplayMetrics());
    }

    public static float dp2px(Context context,float dpValue){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,context.getResources().getDisplayMetrics());
    }

    /**
     * 返回 Canvas.drawText()中第三个参数的值
     * @return
     */
    public static float getTextBaseLine(Paint paint){
        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
        paint.getFontMetrics(fontMetrics);
        float centerY = (fontMetrics.bottom - fontMetrics.top) / 2;

        return centerY + (centerY - fontMetrics.bottom);
    }
}
