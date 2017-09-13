package com.grandline.showcaseepoxy.ui.components.catalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.CarouselEpoxyModel_;
import com.grandline.showcaseepoxy.ui.models.ProductCardModel;
import com.grandline.showcaseepoxy.ui.models.ProductFooterModel;
import com.grandline.showcaseepoxy.ui.models.ProductFooterModel_;

import com.grandline.showcaseepoxy.ui.models.ProductHeaderModel_;

import java.util.List;

/**
 * Created by home on 9/7/17.
 */

public class CatalogAdapter extends EpoxyAdapter {

    public interface CatalogCallbacks{
        void onCatalogClicked(Product product);
        void onCatalogLongClicked(int position);
        void onAddtoCartClicked(int position);
        void onShowMoreClicked(String category);
    }
    private CatalogCallbacks callback;
    private ProductCardModel.OnModelClick onCarouselItemClick = new ProductCardModel.OnModelClick() {
        @Override public void onClick(ProductCardModel model) {
            callback.onCatalogClicked(model.product);
        }
    };
    private ProductFooterModel.OnModelClick onFooterItemClick = new ProductFooterModel.OnModelClick() {
        @Override
        public void onClick(String category) {
            callback.onShowMoreClicked(category);
        }
    };
    public void setCatalog(List<Products> products) {
        models.clear();
        for (int i=0;i<products.size();i++) {
            CarouselAdapter carouselAdapter = new CarouselAdapter(products.get(i).getProduct(), onCarouselItemClick);
            models.add(new ProductHeaderModel_().title(products.get(i).getCategory()).count(products.get(i).getProduct().size()));
            models.add(new CarouselEpoxyModel_<CarouselAdapter>().adapter(carouselAdapter));
            models.add(new ProductFooterModel_().title(products.get(i).getCategory()).clickListener(onFooterItemClick));
        }
        notifyModelsChanged();
    }
    public void setCallback(CatalogCallbacks callback) {
        this.callback = callback;
    }

    public CatalogAdapter() {
        enableDiffing();
    }
}
