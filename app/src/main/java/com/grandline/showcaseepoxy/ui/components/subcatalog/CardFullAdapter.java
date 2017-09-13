package com.grandline.showcaseepoxy.ui.components.subcatalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.ui.models.ProductCardFullModel;
import com.grandline.showcaseepoxy.ui.models.ProductCardFullModel_;

/**
 * Created by home on 9/13/17.
 */

public class CardFullAdapter extends EpoxyAdapter {

    public CardFullAdapter(Product p, ProductCardFullModel.OnModelClick listener) {
        addModel(new ProductCardFullModel_()
                .product(p)
                .clickListener(listener)
        );
    }
}
