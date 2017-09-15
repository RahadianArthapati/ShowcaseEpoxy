package com.grandline.showcaseepoxy.data.remote;

import io.reactivex.Single;

/**
 * Created by home on 8/29/17.
 */

interface RemoteSource {

    Single fetchProducts(String category,boolean evict);
    //Single getProduct(String category);
}
