package com.onecalf.hard;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.onecalf.hard.buck.BaseDao;
import com.onecalf.hard.buck.Person;
import com.onecalf.hard.myrxjava.Observable;
import com.onecalf.hard.myrxjava.Observer;
import com.onecalf.hard.myrxjava.Schedulers;
import com.onecalf.hard.myrxjava.Subscriber;
import com.onecalf.hard.plugin.PluginTestActivity;
import com.onecalf.hard.reflect.Reflect;
import com.onecalf.hard.util.LogUtil;
import com.onecalf.hard.util.StatusUtil;

public class MainActivity extends Activity implements View.OnClickListener {

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
        findViewById(R.id.btn_myrxjava).setOnClickListener(this);
        findViewById(R.id.btn_third_rxjava).setOnClickListener(this);
        findViewById(R.id.btn_third_retrofit).setOnClickListener(this);

        initData();

    }

    private void initData() {
//        Reflect reflect1 = Reflect.on(Object.class);
//        Reflect reflect2 = Reflect.on("java.lang.Object");
//        Reflect reflect3 = Reflect.on("java.lang.Object",ClassLoader.getSystemClassLoader());


        String world = Reflect.on("java.lang.String")  // Like Class.forName()
                .create("Hello World").get();// Call most specific matching constructor
//                .call("substring", 6)  // Call most specific matching substring() method
//                .call("toString")      // Call toString()
//                .get();


        LogUtil.d("zhj", "world=" + world);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_custom_view:
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                break;
            case R.id.btn_animation:
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                break;
            case R.id.btn_tool:
                startActivity(new Intent(MainActivity.this, ToolActivity.class));
                break;
            case R.id.btn_test_plugin:
                startActivity(new Intent(MainActivity.this, PluginTestActivity.class));
                break;
            case R.id.btn_drag_view:
                startActivity(new Intent(MainActivity.this, DragViewActivity.class));
                break;
            case R.id.btn_myrxjava:
                onMyRxJava();
                break;
            case R.id.btn_third_rxjava:
                onThirdRxJava();
                break;
            case R.id.btn_third_retrofit:
                onRetrofitTest();
                break;
        }


    }


    private void onMyRxJava() {
//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onStart();
//                subscriber.onNext("hello,world");
//                subscriber.onCompleted();
//            }
//        });
//
//        Subscriber<String> subscriber = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.d("zh", "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d("zh", "onNext=" + s);
//            }
//        };


        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ " + Thread.currentThread().getName()); //new Thread
                subscriber.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d("zh", "onCompleted...");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("zh", "onError...");
                    }

                    @Override
                    public void onNext(Integer var1) {
                        Log.d("zh", "onNext... var1=" + var1);
                    }
                });
    }

    private void onThirdRxJava() {

    }

    private void onRetrofitTest() {

        String sqliteDatabasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/meicai.db";

        Log.e("zhj","dbpath=" + sqliteDatabasePath);

        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath,null);
        BaseDao<Person> baseDao = new BaseDao<>();
        baseDao.init(Person.class,sqLiteDatabase);



    }


}














