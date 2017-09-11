package com.grandline.showcaseepoxy.data.model;

import com.squareup.moshi.Json;

/**
 * Created by home on 8/24/17.
 */

public class Pricing {
    @Json(name = "price")
    private Double price;
    @Json(name = "promo_price")
    private Double promoPrice;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(Double promoPrice) {
        this.promoPrice = promoPrice;
    }
}
