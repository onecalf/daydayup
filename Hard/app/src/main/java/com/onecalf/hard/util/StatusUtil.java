package com.onecalf.hard.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * github上也有StatusUtil
 */
public class StatusUtil {

    /**
     * 设置activity的状态栏颜色
     *  5.0 以下头部状态栏默认是黑色，5.0以上是有颜色的
     *  这个是系统帮我们做的处理，默认获取的是 attrs/color/colorPrimaryDark
     *  4.4以下没办法处理
     *  主要针对4.4以上
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity,int color){
        // 5.0以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //直接调用系统的方法
            activity.getWindow().setStatusBarColor(color);
        }

        //4.4 - 5.0 之间
        //采用一个技巧，首先把它弄成一个全屏，在状态栏的部分加一个布局
        //然后再对空上布局进行设置颜色
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //在状态栏的部分加一个布局,高度是状态栏的高度
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View view = new View(activity);

            int statusHeight = getStatusHeight(activity);
            LogUtil.d("zhj","statusHeight=" + statusHeight);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,statusHeight));
            decorView.addView(view,0);
            view.setBackgroundColor(color);

            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);

            //第一种方法：
//            contentView.setPadding(0,statusHeight,0,0);

            //第二种方法
            View activityView = contentView.getChildAt(0);
            activityView.setFitsSystemWindows(true);
            activityView.setPadding(0,statusHeight,0,0);
        }

    }

    public static int getStatusHeight(Activity activity){
        //怎么获取资源？ 先获取资源id,根据id获取资源
        Resources resources = activity.getResources();
        int statusBarHeightId = resources.getIdentifier("status_bar_height","dimen","android");
        return resources.getDimensionPixelOffset(statusBarHeightId);
    }


    /**
     * 设置activity全屏
     * @param activity
     */
    public static void setActivityTranslucent(Activity activity){
        // 5.0以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}



















