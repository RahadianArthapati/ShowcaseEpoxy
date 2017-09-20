package com.grandline.showcaseepoxy.ui.components.catalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.HolderLinearHorizontalCard_;
import com.grandline.showcaseepoxy.ui.models.ModelCardProduct;
import com.grandline.showcaseepoxy.ui.models.ModelFooterProduct;
import com.grandline.showcaseepoxy.ui.models.ModelFooterProduct_;
import com.grandline.showcaseepoxy.ui.models.ModelHeaderProduct_;
import com.grandline.showcaseepoxy.ui.models.ModelLineHorizontal_;

/**
 * Created by home on 9/7/17.
 */

public class CatalogAdapter extends EpoxyAdapter {


    public CatalogAdapter() {
        //enableDiffing();
    }

    private CatalogCallback callback;
    private ModelCardProduct.OnModelClick onCarouselItemClick = new ModelCardProduct.OnModelClick() {
        @Override public void onClick(ModelCardProduct model) {
            callback.onCatalogClicked(model.product);
        }
    };
    private ModelFooterProduct.OnModelClick onFooterItemClick = new ModelFooterProduct.OnModelClick() {
        @Override
        public void onClick(Products products) {
            callback.onShowMoreClicked(products);
        }
    };
    public void setCatalog(Products products) {
        //models.clear();
        CarouselAdapter carouselAdapter = new CarouselAdapter(products.getProduct(), onCarouselItemClick);
        models.add(new ModelHeaderProduct_().title(products.getCategory()).count(products.getProduct().size()));
        models.add(new HolderLinearHorizontalCard_<CarouselAdapter>().adapter(carouselAdapter));
        models.add(new ModelLineHorizontal_());
        models.add(new ModelFooterProduct_().products(products).clickListener(onFooterItemClick));
        //notifyModelsChanged();
    }
    public void setCallback(CatalogCallback callback) {
        this.callback = callback;
    }
}
