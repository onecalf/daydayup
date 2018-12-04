package com.zhj.demo1.weibo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class ParentViewPager extends ViewPager {
    public ParentViewPager(@NonNull Context context) {
        super(context);
    }

    public ParentViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
