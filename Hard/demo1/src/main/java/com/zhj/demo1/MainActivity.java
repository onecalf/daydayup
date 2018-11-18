package com.zhj.demo1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRxjava();
            }
        });
    }

    private void onRxjava() {
        Observable.just("普通的人")
                .map(new Function<String, Person>() {
                    @Override
                    public Person apply(String s) throws Exception {
                        Log.d("zhj", "s=" + s);

                        Person person = new Person();
                        person.desc = "一个很" + s;
                        return person;
                    }
                })
                .map(new Function<Person, GoodPerson>() {
                    @Override
                    public GoodPerson apply(Person person) throws Exception {
                        person.show();

                        GoodPerson goodPerson = new GoodPerson();
                        goodPerson.desc = person.desc + ",后来变成了好人";

                        return goodPerson;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodPerson>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodPerson goodPerson) {
                        goodPerson.show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
