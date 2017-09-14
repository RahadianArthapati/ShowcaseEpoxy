package com.grandline.showcaseepoxy.data.source;

import com.grandline.showcaseepoxy.data.model.ProductsList;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;
import retrofit2.Response;

/**
 * Created by home on 9/14/17.
 */

public interface ProductCache {

    @LifeCache(duration = 1, timeUnit = TimeUnit.HOURS)
    Observable<Reply<ProductsList>>
    fetchProductsList(Observable<ProductsList> productsList, EvictProvider evictProvider);

}
