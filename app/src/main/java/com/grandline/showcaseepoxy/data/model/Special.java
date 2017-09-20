package com.grandline.showcaseepoxy.data.model;

/**
 * Created by home on 9/18/17.
 */

import com.squareup.moshi.Json;

public class Special {

    @Json(name = "name")
    private String name;
    @Json(name = "uri")
    private String uri;
    @Json(name = "count")
    private Integer count;
    @Json(name = "image")
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
