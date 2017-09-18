package com.grandline.showcaseepoxy.ui.components.catalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.BaseEpoxyModel_;
import com.grandline.showcaseepoxy.ui.models.HorizontalLineModel_;
import com.grandline.showcaseepoxy.ui.models.ProductCardModel;
import com.grandline.showcaseepoxy.ui.models.ProductFooterModel;
import com.grandline.showcaseepoxy.ui.models.ProductFooterModel_;

import com.grandline.showcaseepoxy.ui.models.ProductHeaderModel_;
import com.grandline.showcaseepoxy.utils.RecyclerState;

import java.util.List;

/**
 * Created by home on 9/7/17.
 */

public class CatalogAdapter extends EpoxyAdapter {

    public interface CatalogCallbacks{
        void onCatalogClicked(Product product);
        void onCatalogLongClicked(int position);
        void onAddToCartClicked(int position);
        void onShowMoreClicked(Products products);
    }
    private CatalogCallbacks callback;
    private ProductCardModel.OnModelClick onCarouselItemClick = new ProductCardModel.OnModelClick() {
        @Override public void onClick(ProductCardModel model) {
            callback.onCatalogClicked(model.product);
        }
    };
    private ProductFooterModel.OnModelClick onFooterItemClick = new ProductFooterModel.OnModelClick() {
        @Override
        public void onClick(Products products) {
            callback.onShowMoreClicked(products);
        }
    };
    public void setCatalog(Products products) {
        models.clear();
        CarouselAdapter carouselAdapter = new CarouselAdapter(products.getProduct(), onCarouselItemClick);
        models.add(new ProductHeaderModel_().title(products.getCategory()).count(products.getProduct().size()));
        models.add(new BaseEpoxyModel_<CarouselAdapter>().adapter(carouselAdapter));
        models.add(new HorizontalLineModel_());
        models.add(new ProductFooterModel_().products(products).clickListener(onFooterItemClick));

        //notifyModelsChanged();
    }
    public void setCallback(CatalogCallbacks callback) {
        this.callback = callback;
    }

    /*
    public CatalogAdapter() {
        enableDiffing();
    }
    */
}
