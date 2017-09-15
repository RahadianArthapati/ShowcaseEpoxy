package com.grandline.showcaseepoxy.ui.components.catalog;

import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.components.base.BaseView;

import java.util.List;

/**
 * Created by home on 9/14/17.
 */

public class CatalogContract {
    interface View extends BaseView {
        //void initialize(List<? extends Products> products);

        void initialize();

        void update(List<Products> products);

        void setLoaderVisibility(boolean isVisible);

        void navigateToDetailsScreen(Product product);

        void navigateToSubCatalogScreen(String subcategory);

        void setNoDataVisibility(boolean isVisible);

        void setListVisibility(boolean isVisible);

        void showSearchError();

        void showNoProductsError();

    }


    interface Presenter {
        void getProductList(String category);

        void unSubscribe();

        RecyclerItemListener getRecyclerItemListener();
    }
}
