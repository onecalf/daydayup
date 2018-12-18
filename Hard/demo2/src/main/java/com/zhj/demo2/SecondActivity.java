package com.zhj.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity implements View.OnClickListener {
    Button btnSecond;
    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                Log.d("SecondActivity", "收到message了");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnSecond = findViewById(R.id.btn_second);
        btnSecond.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_second:
                Message message = uiHandler.obtainMessage();
                message.what = 100;
                uiHandler.sendMessageDelayed(message, 10 * 1000);

                finish();
                break;
            default:
                break;
        }
    }
}
