package com.grandline.showcaseepoxy.ui.components.catalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Special;
import com.grandline.showcaseepoxy.ui.models.HolderGrid_;
import com.grandline.showcaseepoxy.ui.models.ModelCardSpecialProduct;
import com.grandline.showcaseepoxy.ui.models.ModelFooterProduct_;
import com.grandline.showcaseepoxy.ui.models.ModelFooterSpecialProduct;
import com.grandline.showcaseepoxy.ui.models.ModelFooterSpecialProduct_;
import com.grandline.showcaseepoxy.ui.models.ModelHeaderProduct_;
import com.grandline.showcaseepoxy.ui.models.ModelLineHorizontal_;
import com.grandline.showcaseepoxy.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 9/19/17.
 */

public class SpecialAdapter extends EpoxyAdapter {
    private CatalogCallback callback;

    public SpecialAdapter() {
        //enableDiffing();
    }

    private ModelCardSpecialProduct.OnModelClick onSpecialItemClick = new ModelCardSpecialProduct.OnModelClick(){
        @Override
        public void onClick(ModelCardSpecialProduct model) {
            callback.onSpecialClicked(model.special);
        }
    };
    private ModelFooterSpecialProduct.OnModelClick onSpecialMoreItemClick = new ModelFooterSpecialProduct.OnModelClick(){
        @Override
        public void onClick() {
            callback.onShowMoreSpecialClicked();
        }
    };
    public void setSpecial(List<Special> special) {
        //models.clear();
        models.add(new ModelHeaderProduct_().title("Special").count(0));
        GridAdapter adapter = new GridAdapter(special,onSpecialItemClick);
        models.add(new HolderGrid_<GridAdapter>().adapter(adapter));
        if(special.size()> Constants.MAX_SPECIALS_PRODUCT){
            models.add(new ModelLineHorizontal_());
            models.add(new ModelFooterSpecialProduct_().clickListener(onSpecialMoreItemClick));
        }
        //notifyModelsChanged();
    }
    public void setCallback(CatalogCallback callback) {
        this.callback = callback;
    }
}
