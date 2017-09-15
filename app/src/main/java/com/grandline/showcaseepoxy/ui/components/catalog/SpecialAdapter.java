package com.grandline.showcaseepoxy.ui.components.catalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.BaseEpoxyModel_;
import com.grandline.showcaseepoxy.ui.models.ProductFooterModel_;
import com.grandline.showcaseepoxy.ui.models.ProductHeaderModel_;
import com.grandline.showcaseepoxy.ui.models.SpecialsCardHolder_;

import java.util.List;

/**
 * Created by home on 9/15/17.
 */

public class SpecialAdapter extends EpoxyAdapter {

    public SpecialAdapter() {
        enableDiffing();
    }
    public void setData(List<Products> products){
        models.clear();
        for (int i=0;i<products.size();i++) {
            CatalogAdapter adapter = new CatalogAdapter();
            adapter.setCatalog(products.get(i));
            models.add(new SpecialsCardHolder_<CatalogAdapter>().adapter(adapter));
        }
        notifyModelsChanged();
    }
}
