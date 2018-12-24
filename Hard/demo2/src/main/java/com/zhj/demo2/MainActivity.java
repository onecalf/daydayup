package com.zhj.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhj.demo2.view.MyButton;

public class MainActivity extends Activity {
    MyButton myButton;
    ImageView imageView;
    Button btnMainAct2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = findViewById(R.id.btn_test);
        imageView = findViewById(R.id.image);
        btnMainAct2 = findViewById(R.id.btn_main_act2);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTest();
            }
        });

        findViewById(R.id.btn_my_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomViewActivity.class);
                startActivity(intent);
            }
        });

        btnMainAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMainAct2();
            }
        });
    }

    private void onMainAct2() {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

    private void onTest() {
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);


//        String url = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";
//        Glide.with(this)
//                .load(url)
//                .into(imageView);


    }

}
