package com.grandline.showcaseepoxy.data.model;

import com.squareup.moshi.Json;

/**
 * Created by home on 8/24/17.
 */

public class Promotion {
    @Json(name = "type")
    private Integer type;
    @Json(name = "promo_label")
    private String promoLabel;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPromoLabel() {
        return promoLabel;
    }

    public void setPromoLabel(String promoLabel) {
        this.promoLabel = promoLabel;
    }
}
