package com.zhj.demo2;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

public class Demo2Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);

    }
}
