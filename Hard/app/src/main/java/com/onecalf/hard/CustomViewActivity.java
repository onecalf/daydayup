package com.onecalf.hard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.widget.TextView;

public class CustomViewActivity extends Activity{
    TextView tvHello;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom_layout);
        tvHello = findViewById(R.id.tv_hello);

//        tvHello.setTextSize(16);
        tvHello.setTextSize(TypedValue.COMPLEX_UNIT_PX, 16);

    }
}
