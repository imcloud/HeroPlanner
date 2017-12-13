package com.aceegg.heroplanner;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by jinwenxiu on 2017/12/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
