package com.grandline.showcaseepoxy.data.source;

import com.grandline.showcaseepoxy.data.model.ProductsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by home on 9/14/17.
 */

public interface ProductService {
    @GET("/stores/{category}")
    Call<ProductsList> fetchProducts(@Path("category") String category);
}
