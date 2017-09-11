package com.grandline.showcaseepoxy.ui.components.catalog;

import android.support.v7.widget.RecyclerView;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyController;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.CardModel_;
import com.grandline.showcaseepoxy.ui.models.TitleModel_;

import java.util.Collections;
import java.util.List;

/**
 * Created by home on 9/7/17.
 */

public class CatalogEpoxyController extends EpoxyController {
    @AutoModel
    CardModel_ cardModel;
    private List<Products> products = Collections.emptyList();

    public interface CatalogCallbacks{
        void onCatalogClicked(int position);
        void onCatalogLongClicked(int position);
        void onAddtoCartClicked(int position);
    }
    private final CatalogCallbacks callbacks;
    private final RecyclerView.RecycledViewPool recycledViewPool;

    public CatalogEpoxyController(CatalogCallbacks callbacks, RecyclerView.RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
    }

    public void setProducts(List<Products> products){
        this.products = products;
        requestModelBuild();
    }

    @Override
    protected void buildModels() {
        if(products.size()>0) {
            for (int i = 0; i < products.size(); i++) {
                cardModel
                        .url(products.get(i).getPosterImage())
                        .title(products.get(i).getCategory())
                        .addTo(this);
            }
        }
    }
    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
