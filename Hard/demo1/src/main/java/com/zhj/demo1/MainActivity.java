package com.zhj.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhj.demo1.eventtest.MotionEventTestActivity;
import com.zhj.demo1.myrxjava.Observable;
import com.zhj.demo1.myrxjava.OnSubscribe;
import com.zhj.demo1.myrxjava.Subscriber;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onRxjava();
                onMyRxjava();
            }
        });
        findViewById(R.id.btn_constraint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onConstraint();

                Intent intent = new Intent(MainActivity.this, MotionEventTestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onConstraint() {


    }

    private void onMyRxjava() {
        Observable.create(new OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                subscriber.onNext("hello,world");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onNext(String var1) {

            }
        });
    }

    private void onRxjava() {
//        Observable.just("普通的人")
//                .map(new Function<String, Person>() {
//                    @Override
//                    public Person apply(String s) throws Exception {
//                        Log.d("zhj", "s=" + s);
//
//                        Person person = new Person();
//                        person.desc = "一个很" + s;
//                        return person;
//                    }
//                })
//                .map(new Function<Person, GoodPerson>() {
//                    @Override
//                    public GoodPerson apply(Person person) throws Exception {
//                        person.show();
//
//                        GoodPerson goodPerson = new GoodPerson();
//                        goodPerson.desc = person.desc + ",后来变成了好人";
//
//                        return goodPerson;
//                    }
//                })
////                .subscribeOn(Schedulers.newThread())
////                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GoodPerson>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(GoodPerson goodPerson) {
//                        goodPerson.show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }
}
