package com.onecalf.hard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.butterknife.annotation.BindView;
import com.onecalf.hard.plugin.PluginTestActivity;
import com.onecalf.hard.reflect.Reflect;
import com.onecalf.hard.util.LogUtil;
import com.onecalf.hard.util.StatusUtil;

public class MainActivity extends Activity implements View.OnClickListener{
    @BindView(R.id.btn_hello)
    private Button BtnHello;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置状态栏的颜色
//        StatusUtil.setStatusBarColor(this,Color.RED);
        StatusUtil.setActivityTranslucent(this);


        findViewById(R.id.btn_custom_view).setOnClickListener(this);
        findViewById(R.id.btn_animation).setOnClickListener(this);
        findViewById(R.id.btn_tool).setOnClickListener(this);
        findViewById(R.id.btn_test_plugin).setOnClickListener(this);
        findViewById(R.id.btn_drag_view).setOnClickListener(this);

        initData();

    }

    private void initData() {
//        Reflect reflect1 = Reflect.on(Object.class);
//        Reflect reflect2 = Reflect.on("java.lang.Object");
//        Reflect reflect3 = Reflect.on("java.lang.Object",ClassLoader.getSystemClassLoader());


        String world = Reflect.on("java.lang.String")  // Like Class.forName()
                .create("Hello World").get() ;// Call most specific matching constructor
//                .call("substring", 6)  // Call most specific matching substring() method
//                .call("toString")      // Call toString()
//                .get();


        LogUtil.d("zhj","world=" + world);



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
            case R.id.btn_tool:
                startActivity(new Intent(MainActivity.this,ToolActivity.class));
                break;
            case R.id.btn_test_plugin:
                startActivity(new Intent(MainActivity.this,PluginTestActivity.class));
                break;
            case R.id.btn_drag_view:
                startActivity(new Intent(MainActivity.this,DragViewActivity.class));
                break;
        }
    }
}
