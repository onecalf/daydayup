package com.zhj.demo1.myrxjava;

public interface Observer<T> {
    void onCompleted();
    void onError(Throwable t);
    void onNext(T var1);
}






