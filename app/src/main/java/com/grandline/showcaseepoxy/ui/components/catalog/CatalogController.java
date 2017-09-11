package com.grandline.showcaseepoxy.ui.components.catalog;

import android.support.v7.widget.RecyclerView;


import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.CardModel;
import com.grandline.showcaseepoxy.ui.models.CardModel_;
import com.grandline.showcaseepoxy.ui.models.TitleModel_;

import java.util.List;

/**
 * Created by home on 9/7/17.
 */

public class CatalogController extends TypedEpoxyController<List<Products>> {
    public interface CatalogCallbacks{
        void onCatalogClicked(int position);
        void onCatalogLongClicked(int position);
        void onAddtoCartClicked(int position);
    }
    private final CatalogCallbacks callbacks;
    private final RecyclerView.RecycledViewPool recycledViewPool;

    @AutoModel TitleModel_ titleModel;
    @AutoModel CardModel_ cardModel;

    public CatalogController(CatalogCallbacks callbacks, RecyclerView.RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<Products> data) {

        for(int i=0;i<data.size();i++){
            cardModel
                    .title(data.get(i).getCategory())
                    .url(data.get(i).getPosterImage())
                    .addTo(this);
            /*
            new CardModel_().id(data.get(i).getCategory())
                    .title(data.get(i).getCategory())
                    .url(data.get(i).getPosterImage())
                    .addTo(this);
            */
        }
    }
    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
