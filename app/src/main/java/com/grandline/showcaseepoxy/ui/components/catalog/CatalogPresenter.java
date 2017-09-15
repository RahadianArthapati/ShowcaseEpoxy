package com.grandline.showcaseepoxy.ui.components.catalog;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;

import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.domain.ProductsDomain;
import com.grandline.showcaseepoxy.ui.components.base.BasePresenter;
import com.grandline.showcaseepoxy.data.source.ProductsCallback;
import com.grandline.showcaseepoxy.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import static com.grandline.showcaseepoxy.utils.ObjectUtils.isNull;


/**
 * Created by home on 8/29/17.
 */

public class CatalogPresenter extends BasePresenter<CatalogContract.View> implements CatalogContract.Presenter {
    private final ProductsDomain productsDomain;
    private List<Products> product;

    @Inject
    public CatalogPresenter(ProductsDomain domain) {
        productsDomain = domain;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().initialize();
        getProductList(Constants.CATEGORY_BEVERAGES);

    }

    @Override
    public void getProductList(String category) {
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        productsDomain.getProducts(callback, category, true);
    }
    private void showList(boolean isVisible) {
        getView().setNoDataVisibility(!isVisible);
        getView().setListVisibility(isVisible);
    }

    @Override
    public void unSubscribe() {
        productsDomain.unSubscribe();
    }

    @Override
    public RecyclerItemListener getRecyclerItemListener() {
        return recyclerItemListener;
    }

    private final RecyclerItemListener recyclerItemListener = (position,title) -> {
        for(int i=0;i<product.size();i++){
            if(product.get(i).getCategory().equalsIgnoreCase(title)){
                getView().navigateToDetailsScreen(product.get(i).getProduct().get(position));
            }
        }

    };

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public List<Products> getProductsList() {
        return product;
    }

    private final ProductsCallback callback = new ProductsCallback() {
        @Override
        public void onSuccess(List<Products> product) {

            CatalogPresenter.this.product = product;
            if (!isNull(product) && !product.isEmpty()) {
                //getView().initialize(product);
                getView().update(product);
                showList(true);
            } else {
                showList(false);
            }
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onFail(String message) {

            showList(false);
            getView().setLoaderVisibility(false);
        }
    };
}
