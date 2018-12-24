package com.zhj.demo2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends Activity implements View.OnClickListener {
    Button btnOne;
    Button btnTwo;
    Button btnThree;

    Button btnScrollTo;
    Button btnScrollBy;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnOne = findViewById(R.id.btn_one);
        btnTwo = findViewById(R.id.btn_two);
        btnThree = findViewById(R.id.btn_three);

        btnScrollBy = findViewById(R.id.btn_scroll_by);
        btnScrollTo = findViewById(R.id.btn_scroll_to);
        imageView = findViewById(R.id.imageview);

        btnScrollBy.setOnClickListener(this);
        btnScrollTo.setOnClickListener(this);
        imageView.setOnClickListener(this);


        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "one", Toast.LENGTH_LONG).show();
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "two", Toast.LENGTH_LONG).show();
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "three", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroll_to:
//                imageView.scrollTo(-60,-100);

                ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 200);
                mObjectAnimator.setDuration(300);
                mObjectAnimator.start();

                break;
            case R.id.btn_scroll_by:
                imageView.scrollTo(-60, -100);
                break;

            case R.id.imageview:
                imageView.scrollTo(-60, -100);
                break;

        }


    }
}
