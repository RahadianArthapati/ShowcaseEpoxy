package com.grandline.showcaseepoxy.data.model;

import com.squareup.moshi.Json;

/**
 * Created by home on 8/24/17.
 */

public class Images {
    @Json(name = "name")
    private String name;
    @Json(name = "position")
    private Integer position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
