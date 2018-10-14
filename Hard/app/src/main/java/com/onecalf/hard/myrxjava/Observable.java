package com.onecalf.hard.myrxjava;

/**
 * 被观察者
 * @param <T>
 */
public class Observable<T> {
    OnSubscribe<T> onSubscribe;

    //私有构造函数
    private Observable(OnSubscribe<T> onSubscribe){
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe){
        return new Observable<>(onSubscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber){
        subscriber.onStart();
        onSubscribe.call(subscriber);
    }

    public <R> Observable<R> map(Transformer<? super T, ? extends R> transformer) {
        return create(new MapOnSubscribe<T, R>(this, transformer));
    }


    public interface OnSubscribe<T>{
        void call(Subscriber<? super T> subscriber);
    }


    public interface Transformer<T, R> {
        R call(T from);
    }


    public Observable<T> subscribeOn(final Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                subscriber.onStart();
                // 将事件的生产切换到新的线程。
                scheduler.createWorker().schedule(new Runnable() {
                    @Override
                    public void run() {
                        Observable.this.onSubscribe.call(subscriber);
                    }
                });
            }
        });
    }

    public Observable<T> observeOn(final Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                subscriber.onStart();
                final Scheduler.Worker worker = scheduler.createWorker();
                Observable.this.onSubscribe.call(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onCompleted();
                            }
                        });
                    }
                    @Override
                    public void onError(final Throwable t) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onError(t);
                            }
                        });
                    }
                    @Override
                    public void onNext(final T var1) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onNext(var1);
                            }
                        });
                    }
                });
            }
        });
    }
}


