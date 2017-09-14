package com.grandline.showcaseepoxy.data.service;


import com.grandline.showcaseepoxy.data.model.ProductsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by home on 8/29/17.
 */

public interface ProductAPI {
    @GET("/stores/{category}")
    Call<ProductsList> fetchProductsList(@Path("category") String category);

}
