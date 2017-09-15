package com.grandline.showcaseepoxy.data.service;

import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.data.model.ProductsList;

import java.util.concurrent.TimeUnit;

import io.rx_cache2.LifeCache;
import retrofit2.Call;

/**
 * Created by home on 9/5/17.
 */

public interface CacheProviders
{
    @LifeCache(duration = 1, timeUnit = TimeUnit.HOURS)
    Call<Products> fetchProducts(Call<Products> fetchProductsCall);
}
