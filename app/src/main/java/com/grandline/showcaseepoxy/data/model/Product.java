package com.grandline.showcaseepoxy.data.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by home on 8/24/17.
 */

public class Product {
    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "desc")
    private String desc;
    @Json(name = "weight")
    private String weight;
    @Json(name = "tag")
    private List<String> tag = null;
    @Json(name = "pricing")
    private Pricing pricing;
    @Json(name = "promotions")
    private List<Promotion> promotions = null;
    @Json(name = "rating")
    private Double rating;
    @Json(name = "images")
    private List<Images> images = null;
    @Json(name = "tags")
    private List<String> tags = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    /*
    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
    */
    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
