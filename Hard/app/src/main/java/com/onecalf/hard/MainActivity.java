package com.onecalf.hard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_custom_view).setOnClickListener(this);
        findViewById(R.id.btn_animation).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_custom_view:
                startActivity(new Intent(MainActivity.this,CustomViewActivity.class));
                break;
            case R.id.btn_animation:
                startActivity(new Intent(MainActivity.this,AnimationActivity.class));
                break;
        }
    }
}
