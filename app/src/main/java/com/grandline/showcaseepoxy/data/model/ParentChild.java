package com.grandline.showcaseepoxy.data.model;

import java.util.List;

/**
 * Created by home on 9/6/17.
 */

public class ParentChild {
    Header header;
    List<?> child;

    public ParentChild(Header header,List<?> child) {
        this.header = header;
        this.child = child;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<?> getChild() {
        return child;
    }

    public void setChild(List<?> child) {
        this.child = child;
    }
}
