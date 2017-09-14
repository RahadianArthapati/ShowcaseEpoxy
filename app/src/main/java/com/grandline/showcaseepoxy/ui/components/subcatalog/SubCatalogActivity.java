package com.grandline.showcaseepoxy.ui.components.subcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.components.detail.DetailActivity;
import com.grandline.showcaseepoxy.utils.ScreenUtils;
import com.squareup.moshi.Moshi;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by home on 9/13/17.
 */

public class SubCatalogActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,SubCatalogAdapter.SubCatalogCallbacks {
    @BindView(R.id.subcatalog_rv)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private SubCatalogAdapter adapter;
    private Unbinder unbinder;

    @Override
    public void onSubCatalogClicked(Product product) {
        String productJson = new Moshi.Builder().build().adapter(Product.class).toJson(product);
        Intent intent = new Intent(SubCatalogActivity.this, DetailActivity.class);
        intent.putExtra("product_detail", productJson);
        startActivity(intent);
    }

    @Override
    public void onSubCatalogLongClicked(int position) {

    }

    @Override
    public void onAddtoCartClicked(int position) {

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        recycledViewPool.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcatalog);
        unbinder = ButterKnife.bind(this);
        adapter = new SubCatalogAdapter();
        adapter.setCallback(this);
        recycledViewPool.setMaxRecycledViews(R.layout.product_card_view, 50);
        recycledViewPool.setMaxRecycledViews(R.layout.view_holder_carousel, 5);
        recycledViewPool.setMaxRecycledViews(R.layout.product_card_view_full, 5);
        recyclerView.setRecycledViewPool(recycledViewPool);

        adapter.setSpanCount(ScreenUtils.calculateNoOfColumns(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,ScreenUtils.calculateNoOfColumns(this),LinearLayoutManager.VERTICAL,false);

        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        //recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        String json = getIntent().getStringExtra("products");
        try {
            final Products p = new Moshi.Builder().build().adapter(Products.class).fromJson(json);
            getProduct(p.getProduct());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getProduct(List<Product> products){
        adapter.setSubCatalog(products);
    }
}
