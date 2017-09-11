package com.grandline.showcaseepoxy.data.service;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.grandline.showcaseepoxy.BuildConfig;
import com.grandline.showcaseepoxy.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by home on 9/7/17.
 */

public class ProductService {

    private static ProductAPI serviceInstance = null;
    //Network constants
    private final int TIMEOUT_CONNECT = 30;   //In seconds
    private final int TIMEOUT_READ = 30;   //In seconds
    private final String CONTENT_TYPE = "Content-Type";
    private final String CONTENT_TYPE_VALUE = "application/json";

    public static ProductAPI getApi() {
        return serviceInstance == null ? createService() : serviceInstance;
    }

    private static ProductAPI createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(createHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        return retrofit.create(ProductAPI.class);
    }

    private static OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }
}
