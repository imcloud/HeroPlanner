package com.aceegg.data.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jinwenxiu on 2017/12/12.
 */
@Singleton
public class AppExecutors {

    private final static int NETWORK_THREAD_SIZE = 3;

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainthread;

    AppExecutors(Executor diskIO, Executor networkIO, Executor mainthread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainthread = mainthread;
    }

    @Inject
    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(NETWORK_THREAD_SIZE), new MainThreadExecutor());
    }

    public Executor getDiskIO() {
        return this.diskIO;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainthread() {
        return mainthread;
    }

    private static class MainThreadExecutor implements Executor {

        Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable runnable) {
            handler.post(runnable);
        }
    }
}
