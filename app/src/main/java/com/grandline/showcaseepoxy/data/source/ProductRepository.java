package com.grandline.showcaseepoxy.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;
import com.grandline.showcaseepoxy.BuildConfig;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.data.model.ProductsList;
import com.grandline.showcaseepoxy.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.MoshiSpeaker;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

/**
 * Created by home on 9/14/17.
 */

public class ProductRepository implements ProductDataSource {
    private static final long DEFAULT_TIMEOUT = 5;
    private static final long DEFAULT_READ_WRITE_TIMEOUT = 10;

    private final ProductService service;
    private final ProductCache cacheProvider;

    private static ProductRepository INSTANCE;


    public synchronized static ProductRepository getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ProductRepository(context.getCacheDir());
        }
        return INSTANCE;
    }

    private ProductRepository(File cacheDir) {
        service = new Retrofit.Builder()
                .client(setupOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL).build().create(ProductService.class);

        cacheProvider = new RxCache.Builder()
                .persistence(cacheDir, new MoshiSpeaker())
                .using(ProductCache.class);
    }
    @NonNull
    private OkHttpClient setupOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

            builder.addInterceptor(new Interceptor() {
                @Override public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(request);
                }
            });

            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_READ_WRITE_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }
    /*
    @Override
    public Single<Reply<List<Products>>> fetchProducts(String category, boolean evict) {
        Single<List<Products>> productsObservable = service.fetchProducts(category)
                .map(res -> res.body().getProducts());
        return cacheProvider.fetchProducts(productsObservable, new EvictProvider(evict));
    }*/
}
