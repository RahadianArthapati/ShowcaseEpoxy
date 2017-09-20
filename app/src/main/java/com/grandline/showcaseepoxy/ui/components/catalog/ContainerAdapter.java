package com.grandline.showcaseepoxy.ui.components.catalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.ProductsList;
import com.grandline.showcaseepoxy.ui.models.HolderGridCard_;
import com.grandline.showcaseepoxy.ui.models.HolderLinearVerticalNew_;
import com.grandline.showcaseepoxy.ui.models.HolderLinearVertical_;


/**
 * Created by home on 9/15/17.
 */

public class ContainerAdapter extends EpoxyAdapter {
    private CatalogCallback callback;
    public ContainerAdapter() {
        enableDiffing();
    }

    public void setData(ProductsList products){
        if(!models.isEmpty()) {
           models.clear();
        }
        SpecialAdapter specialAdapter = new SpecialAdapter();
        specialAdapter.setSpecial(products.getSpecials());
        specialAdapter.setCallback(callback);
        //models.add(new HolderLinearVerticalNew_<SpecialAdapter>().adapter(specialAdapter));
        addModel(new HolderLinearVertical_<SpecialAdapter>().adapter(specialAdapter));


        for (int i=0;i<products.getProducts().size();i++) {
            CatalogAdapter catalogAdapter = new CatalogAdapter();
            catalogAdapter.setCatalog(products.getProducts().get(i));
            catalogAdapter.setCallback(callback);
            //models.add(new HolderLinearVertical_<CatalogAdapter>().adapter(catalogAdapter));
            addModel(new HolderLinearVertical_<CatalogAdapter>().adapter(catalogAdapter));
        }
        //notifyDataSetChanged();

        notifyModelsChanged();

    }
    public void setCallback(CatalogCallback callback) {
        this.callback = callback;
    }

}
