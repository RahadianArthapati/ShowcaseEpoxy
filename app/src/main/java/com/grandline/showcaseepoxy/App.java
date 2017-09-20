package com.grandline.showcaseepoxy;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.facebook.stetho.Stetho;
import com.grandline.showcaseepoxy.di.AppComponent;
import com.grandline.showcaseepoxy.di.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by home on 9/14/17.
 */

public class App extends Application {
    private AppComponent appComponent;
    //private static Context context;
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
        //context = getApplicationContext();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        }
        ButterKnife.setDebug(BuildConfig.DEBUG);
        refWatcher = installLeakCanary();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /*
    public static Context getContext() {
        return context;
    }
    */
    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }
    @VisibleForTesting
    public void setComponent(AppComponent AppComponent) {
        this.appComponent = appComponent;
    }
}
