package com.grandline.showcaseepoxy.data.remote;

import android.content.Context;

import io.reactivex.Single;

/**
 * Created by home on 8/29/17.
 */

interface RemoteSource {

    Single fetchProducts(Context context, String category, boolean evict);
    //Single getProduct(String category);
}
