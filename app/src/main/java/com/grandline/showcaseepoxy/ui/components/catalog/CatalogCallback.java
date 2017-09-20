package com.grandline.showcaseepoxy.ui.components.catalog;

import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.data.model.Special;

/**
 * Created by home on 9/19/17.
 */

public interface CatalogCallback {
    void onCatalogClicked(Product product);
    void onCatalogLongClicked(int position);
    void onAddToCartClicked(int position);
    void onShowMoreClicked(Products products);
    void onSpecialClicked(Special special);
    void onShowMoreSpecialClicked();
}
