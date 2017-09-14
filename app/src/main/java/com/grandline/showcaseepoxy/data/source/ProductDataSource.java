package com.grandline.showcaseepoxy.data.source;



import com.grandline.showcaseepoxy.data.model.ProductsList;

import io.reactivex.Observable;
import io.rx_cache2.Reply;

/**
 * Created by home on 9/14/17.
 */

public interface ProductDataSource {
    Observable<Reply<ProductsList>> fetchProductsList(String category, boolean evict);
}
