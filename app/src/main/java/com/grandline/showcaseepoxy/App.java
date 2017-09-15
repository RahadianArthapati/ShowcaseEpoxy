package com.grandline.showcaseepoxy;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.grandline.showcaseepoxy.di.AppComponent;
import com.grandline.showcaseepoxy.di.DaggerAppComponent;

import timber.log.Timber;

/**
 * Created by home on 9/14/17.
 */

public class App extends Application {
    private AppComponent appComponent;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
        context = getApplicationContext();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static Context getContext() {
        return context;
    }

    @VisibleForTesting
    public void setComponent(AppComponent AppComponent) {
        this.appComponent = appComponent;
    }
}
