package com.onecalf.hard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class CustomViewActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom_layout);
    }
}
