package com.onecalf.hard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import com.onecalf.hard.widget.SwitchButton;

public class CustomViewActivity extends Activity{
    TextView tvHello;
    SwitchButton switchButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom_layout);
        tvHello = findViewById(R.id.tv_hello);
        switchButton = findViewById(R.id.switchButton);

//        tvHello.setTextSize(16);
        tvHello.setTextSize(TypedValue.COMPLEX_UNIT_PX, 46);

        switchButton.setSwitchOnClickedListener(new SwitchButton.OnSwitchClickedListener() {
            @Override
            public void onSwitchClicked(boolean isOpen) {
                Toast.makeText(CustomViewActivity.this,"isOpen=" + isOpen,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
