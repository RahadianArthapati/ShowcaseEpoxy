package com.grandline.showcaseepoxy.ui.components.catalog;

import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Special;
import com.grandline.showcaseepoxy.ui.models.ModelCardSpecialProduct;
import com.grandline.showcaseepoxy.ui.models.ModelCardSpecialProduct_;
import com.grandline.showcaseepoxy.utils.Constants;

import java.util.List;

/**
 * Created by home on 9/19/17.
 */

public class GridAdapter extends EpoxyAdapter {
    public GridAdapter(List<Special> specials, ModelCardSpecialProduct.OnModelClick listener) {
        for (int i=0;i<specials.size();i++) {
            if(i< Constants.MAX_SPECIALS_PRODUCT) {
                addModel(new ModelCardSpecialProduct_()
                        .special(specials.get(i))
                        .clickListener(listener)
                );
            }
        }
    }
}
