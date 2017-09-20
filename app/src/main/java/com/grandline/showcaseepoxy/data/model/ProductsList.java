package com.grandline.showcaseepoxy.data.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by home on 8/29/17.
 */

public class ProductsList {
    @Json(name = "products")
    private List<Products> products = null;
    @Json(name = "specials")
    private List<Special> specials = null;

    public List<Products> getProducts() {
        return products;
    }
    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public List<Special> getSpecials(){ return specials;}
    public void setSpecials(List<Special> specials) { this.specials = specials;}
}