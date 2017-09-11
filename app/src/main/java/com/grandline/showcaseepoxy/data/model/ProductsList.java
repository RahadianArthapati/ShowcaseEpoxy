package com.grandline.showcaseepoxy.data.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by home on 8/29/17.
 */

public class ProductsList {
    @Json(name = "products")
    private List<Products> products = null;

    public List<Products> getProducts() {
        return products;
    }
    public void setProducts(List<Products> products) {
        this.products = products;
    }
}