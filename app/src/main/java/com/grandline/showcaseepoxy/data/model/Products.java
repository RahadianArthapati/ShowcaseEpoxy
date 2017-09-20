package com.grandline.showcaseepoxy.data.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by home on 8/29/17.
 */

public class Products {

    @Json(name = "category")
    private String category;
    @Json(name = "poster_image")
    private String posterImage;
    @Json(name = "product")
    private List<Product> product = null;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }



}
