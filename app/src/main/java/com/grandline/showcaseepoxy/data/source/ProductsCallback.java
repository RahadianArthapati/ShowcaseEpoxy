package com.grandline.showcaseepoxy.data.source;


import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.data.model.ProductsList;

import java.util.List;

/**
 * Created by home on 9/4/17.
 */

public interface ProductsCallback {
    void onSuccess(ProductsList products);

    void onFail(String message);
}