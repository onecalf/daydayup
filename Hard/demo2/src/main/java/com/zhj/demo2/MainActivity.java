package com.zhj.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import com.zhj.demo2.view.MyButton;

public class MainActivity extends Activity {
    MyButton myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = findViewById(R.id.btn_test);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTest();
            }
        });
    }

    private void onTest() {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

}
