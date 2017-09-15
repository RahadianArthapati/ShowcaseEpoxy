package com.grandline.showcaseepoxy.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.moshi.Moshi;
import com.grandline.showcaseepoxy.BuildConfig;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by home on 8/29/17.
 */

@Singleton
public class ServiceGenerator {

    private final int TIMEOUT_CONNECT = 30;   //In seconds
    private final int TIMEOUT_READ = 30;   //In seconds
    private final int TIMEOUT_WRITE  = 10;
    private final String CONTENT_TYPE = "Content-Type";
    private final String CONTENT_TYPE_VALUE = "application/json";

    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit retrofit;
    private Moshi moshi;

    @Inject
    public ServiceGenerator(Moshi moshi) {
        this.okHttpBuilder = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor());

        this.moshi = moshi;
    }

    public <S> S createService(Class<S> serviceClass, String baseUrl) {
        OkHttpClient client = okHttpBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return retrofit.create(serviceClass);
    }

    Interceptor headerInterceptor = chain -> {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                //.header(API_KEY, API_KEY_VALUE)
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    };

    private HttpLoggingInterceptor getLogger() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS).setLevel
                    (HttpLoggingInterceptor.Level.BODY);
        }
        return loggingInterceptor;
    }
}
