package com.grandline.showcaseepoxy.ui.components.subcatalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.ui.models.HolderLinearHorizontalCard_;
import com.grandline.showcaseepoxy.ui.models.ModelCardFullProduct;
import com.grandline.showcaseepoxy.ui.models.ModelCardProduct;
import com.grandline.showcaseepoxy.ui.models.ModelCardProduct_;


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
    private ModelCardProduct.OnModelClick onProductCardItemClick = new ModelCardProduct.OnModelClick() {
        @Override public void onClick(ModelCardProduct model) {
            callback.onSubCatalogClicked(model.product);
        }
    };
    private ModelCardFullProduct.OnModelClick onProductCardFullItemClick = new ModelCardFullProduct.OnModelClick() {
        @Override public void onClick(ModelCardFullProduct model) {
            callback.onSubCatalogClicked(model.product);
        }
    };
    public void setSubCatalog(List<Product> p) {
        models.clear();
        for (int i=0;i<p.size();i++) {
            if(i!=0 && i%6==0){
                CardFullAdapter cardFullAdapter = new CardFullAdapter(p.get(i), onProductCardFullItemClick);
                models.add(new HolderLinearHorizontalCard_<CardFullAdapter>().adapter(cardFullAdapter));

            }else{
                models.add(new ModelCardProduct_().product(p.get(i)).clickListener(onProductCardItemClick));

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
