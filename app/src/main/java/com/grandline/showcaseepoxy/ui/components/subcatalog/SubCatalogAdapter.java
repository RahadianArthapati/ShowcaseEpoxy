package com.grandline.showcaseepoxy.ui.components.subcatalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.ui.models.BaseEpoxyModel_;
import com.grandline.showcaseepoxy.ui.models.ProductCardFullModel;
import com.grandline.showcaseepoxy.ui.models.ProductCardModel;
import com.grandline.showcaseepoxy.ui.models.ProductCardModel_;


import java.util.List;

/**
 * Created by home on 9/13/17.
 */

public class SubCatalogAdapter extends EpoxyAdapter {
    public interface SubCatalogCallbacks{
        void onSubCatalogClicked(Product product);
        void onSubCatalogLongClicked(int position);
        void onAddtoCartClicked(int position);
    }
    private SubCatalogAdapter.SubCatalogCallbacks callback;
    private ProductCardModel.OnModelClick onProductCardItemClick = new ProductCardModel.OnModelClick() {
        @Override public void onClick(ProductCardModel model) {
            callback.onSubCatalogClicked(model.product);
        }
    };
    private ProductCardFullModel.OnModelClick onProductCardFullItemClick = new ProductCardFullModel.OnModelClick() {
        @Override public void onClick(ProductCardFullModel model) {
            callback.onSubCatalogClicked(model.product);
        }
    };
    public void setSubCatalog(List<Product> p) {
        models.clear();
        for (int i=0;i<p.size();i++) {
            if(i!=0 && i%6==0){
                CardFullAdapter cardFullAdapter = new CardFullAdapter(p.get(i), onProductCardFullItemClick);
                models.add(new BaseEpoxyModel_<CardFullAdapter>().adapter(cardFullAdapter));

            }else{
                models.add(new ProductCardModel_().product(p.get(i)).clickListener(onProductCardItemClick));

            }
        }
        notifyModelsChanged();
    }
    public void setCallback(SubCatalogAdapter.SubCatalogCallbacks callback) {
        this.callback = callback;
    }

    public SubCatalogAdapter() {
        enableDiffing();
    }
}
