package com.zhj.demo1;

import android.util.Log;

public class GoodPerson extends Person{

    @Override
    public void show() {
        Log.d("zhj","我是一个大好人: " + desc);
    }
}
