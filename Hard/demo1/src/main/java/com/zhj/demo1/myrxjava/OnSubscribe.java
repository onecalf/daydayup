package com.zhj.demo1.myrxjava;

public interface OnSubscribe<T> {
    void call(Subscriber<? super T> subscriber);
}
