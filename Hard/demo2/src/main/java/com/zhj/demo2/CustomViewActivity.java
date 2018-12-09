package com.zhj.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.zhj.demo2.view.MyView;

public class CustomViewActivity extends Activity {
    MyView myView;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        myView = findViewById(R.id.myview);
        imageView = findViewById(R.id.imageview);


        findViewById(R.id.myview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyView view = (MyView) v;

                float x = view.getX();
                float y = view.getY();
                float transX = view.getTranslationX();
                float transY = view.getTranslationY();

                float left = view.getLeft();
                Log.e("TAG", "x=" + x + " y=" + y + " transX=" + transX + " left=" + left);
            }
        });

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myView.setTranslationX(20);
                imageView.scrollBy(100,100);
            }
        });

        findViewById(R.id.btn_scroll_to).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.scrollTo(100,100);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
