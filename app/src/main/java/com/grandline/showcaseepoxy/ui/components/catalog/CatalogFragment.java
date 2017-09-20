package com.grandline.showcaseepoxy.ui.components.catalog;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grandline.showcaseepoxy.App;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.data.model.ProductsList;
import com.grandline.showcaseepoxy.data.model.Special;
import com.grandline.showcaseepoxy.ui.components.base.BaseFragment;
import com.grandline.showcaseepoxy.ui.components.detail.DetailActivity;
import com.grandline.showcaseepoxy.ui.components.subcatalog.SubCatalogActivity;
import com.grandline.showcaseepoxy.ui.helpers.VerticalGridCardSpacingDecoration;
import com.grandline.showcaseepoxy.utils.Constants;
import com.grandline.showcaseepoxy.utils.ScreenUtils;
import com.squareup.moshi.Moshi;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by home on 9/14/17.
 */

public class CatalogFragment extends BaseFragment implements CatalogCallback,CatalogContract.View,SwipeRefreshLayout.OnRefreshListener {
    @Inject
    CatalogPresenter presenter;
    @BindView(R.id.products_rv)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    ContainerAdapter adapter;
    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private ProductsList productsList = new ProductsList();
    @Override
    protected void initializeDagger() {
        App app = (App) getActivity().getApplicationContext();
        app.getAppComponent().inject(CatalogFragment.this);
    }

    @Override
    protected void initializePresenter(Context context) {
        super.presenter = presenter;
        presenter.setView(this);
        presenter.setContext(context);
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
    public void onShowMoreClicked(Products products) {
        navigateToSubCatalogScreen(products);
    }

    @Override
    public void onSpecialClicked(Special special) {
        Snackbar.make(swipeRefreshLayout, special.getName(),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onShowMoreSpecialClicked() {
        Snackbar.make(swipeRefreshLayout, "Special clicked",Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void update(ProductsList productsList) {
        if(adapter!=null) {
            adapter.setData(productsList);
        }
    }

    @Override
    public void initialize() {
        adapter = new ContainerAdapter();
        adapter.setCallback(this);
        recycledViewPool.setMaxRecycledViews(R.layout.view_card_product, 100);
        recycledViewPool.setMaxRecycledViews(R.layout.view_card_special_product, 10);
        recycledViewPool.setMaxRecycledViews(R.layout.view_holder_carousel, 10);
        recycledViewPool.setMaxRecycledViews(R.layout.view_holder_card, 10);
        recycledViewPool.setMaxRecycledViews(R.layout.view_footer_product,10);
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
        String productJson = new Moshi.Builder().build().adapter(Product.class).toJson(product);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("product_detail", productJson);
        startActivity(intent);
    }

    @Override
    public void navigateToSubCatalogScreen(Products products) {
        String productJson = new Moshi.Builder().build().adapter(Products.class).toJson(products);
        Intent intent = new Intent(getActivity(), SubCatalogActivity.class);
        intent.putExtra("products", productJson);
        startActivity(intent);
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
