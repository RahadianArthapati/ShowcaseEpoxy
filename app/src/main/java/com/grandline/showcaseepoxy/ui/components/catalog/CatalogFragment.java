package com.grandline.showcaseepoxy.ui.components.catalog;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grandline.showcaseepoxy.App;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.components.base.BaseFragment;
import com.grandline.showcaseepoxy.ui.helpers.VerticalGridCardSpacingDecoration;
import com.grandline.showcaseepoxy.utils.Constants;
import com.grandline.showcaseepoxy.utils.ScreenUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by home on 9/14/17.
 */

public class CatalogFragment extends BaseFragment implements CatalogAdapter.CatalogCallbacks,CatalogContract.View,SwipeRefreshLayout.OnRefreshListener {
    @Inject
    CatalogPresenter presenter;
    @BindView(R.id.products_rv)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    SpecialAdapter adapter;
    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    @Override
    protected void initializeDagger() {
        App app = (App) getActivity().getApplicationContext();
        app.getAppComponent().inject(CatalogFragment.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_catalog;
    }

    @Override
    public void onRefresh() {
        presenter.getProductList(Constants.CATEGORY_BEVERAGES);
    }

    @Override
    public void onCatalogClicked(Product product) {
        navigateToDetailsScreen(product);
    }

    @Override
    public void onCatalogLongClicked(int position) {

    }

    @Override
    public void onAddToCartClicked(int position) {

    }

    @Override
    public void onShowMoreClicked(String category) {
        navigateToSubCatalogScreen(category);
    }

    @Override
    public void update(List<Products> products) {
        if(adapter!=null) {
            adapter.setData(products);
        }
    }

    @Override
    public void initialize() {
        adapter = new SpecialAdapter();
        //adapter.setCallback(this);
        recycledViewPool.setMaxRecycledViews(R.layout.product_card_view, 100);
        recycledViewPool.setMaxRecycledViews(R.layout.product_header_view, 10);
        recycledViewPool.setMaxRecycledViews(R.layout.product_footer_view,10);
        recyclerView.setRecycledViewPool(recycledViewPool);

        int columns = ScreenUtils.calculateNoOfColumns(getActivity());
        adapter.setSpanCount(columns);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columns);
        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        swipeRefreshLayout.setRefreshing(isVisible);
    }

    @Override
    public void navigateToDetailsScreen(Product product) {
        Snackbar.make(swipeRefreshLayout,product.getName(),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToSubCatalogScreen(String subcategory) {

    }

    @Override
    public void setNoDataVisibility(boolean isVisible) {

    }

    @Override
    public void setListVisibility(boolean isVisible) {

    }

    @Override
    public void showSearchError() {
        Snackbar.make(swipeRefreshLayout,getString(R.string.error),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showNoProductsError() {
        Snackbar.make(swipeRefreshLayout, getString(R.string.product_error),Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
