package com.grandline.showcaseepoxy.ui.components.detail;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.DetailHeaderModel;
import com.grandline.showcaseepoxy.ui.models.DetailHeaderModel_;

import java.util.List;

/**
 * Created by home on 9/11/17.
 */

public class DetailAdapter extends EpoxyAdapter {
    public void setDetail(Product product) {
        models.clear();
        models.add(new DetailHeaderModel_().product(product));
        notifyModelsChanged();
    }
    public DetailAdapter() {
        enableDiffing();
    }
}
