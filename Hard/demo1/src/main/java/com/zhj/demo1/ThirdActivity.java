package com.zhj.demo1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ThirdActivity extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.i("zhj",  "ThirdActivity 所在的任务的id为: " +  getTaskId());

    }
}
