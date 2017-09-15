package com.grandline.showcaseepoxy.di;


import com.grandline.showcaseepoxy.data.local.LocalRepository;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by home on 8/29/17.
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    public LocalRepository provideLocalRepository() {
        return new LocalRepository();
    }

    @Provides
    @Singleton
    public Moshi provideMoshi() {
        Moshi moshi = new Moshi.Builder().build();
        return moshi;
    }

    @Provides
    public CompositeDisposable provideCompositeSubscription() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        return compositeDisposable;
    }
}