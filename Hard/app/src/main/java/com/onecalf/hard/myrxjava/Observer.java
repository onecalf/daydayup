package com.onecalf.hard.myrxjava;

//https://my.oschina.net/sfshine/blog/1809921
public interface Observer<T> {
    void onCompleted();
    void onError(Throwable t);
    void onNext(T t);
}

