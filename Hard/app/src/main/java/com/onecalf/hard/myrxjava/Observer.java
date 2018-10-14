package com.onecalf.hard.myrxjava;

public interface Observer<T> {
    void onCompleted();
    void onError(Throwable t);
    void onNext(T t);
}

