package com.onecalf.hard.myrxjava;

import java.util.concurrent.Executors;

public class Schedulers {

    private static final Scheduler ioScheduler = new Scheduler(Executors.newSingleThreadExecutor());

    public static Scheduler io() {
        return ioScheduler;
    }
}
