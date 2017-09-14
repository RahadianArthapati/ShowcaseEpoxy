package com.grandline.showcaseepoxy.data.source;

import com.grandline.showcaseepoxy.data.model.ProductsList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by home on 9/14/17.
 */

public interface ProductService {
    @GET("/stores/{category}")
    Observable<Response<ProductsList>> fetchProductsList(@Path("category") String category);
}
