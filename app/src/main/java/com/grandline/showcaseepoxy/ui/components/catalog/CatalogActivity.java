package com.grandline.showcaseepoxy.ui.components.catalog;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.data.model.ProductsList;
import com.grandline.showcaseepoxy.data.service.ProductService;
import com.grandline.showcaseepoxy.ui.components.detail.DetailActivity;
import com.grandline.showcaseepoxy.ui.components.subcatalog.SubCatalogActivity;
import com.grandline.showcaseepoxy.ui.helpers.VerticalGridCardSpacingDecoration;
import com.grandline.showcaseepoxy.utils.ScreenUtils;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends AppCompatActivity implements CatalogAdapter.CatalogCallbacks, Callback<ProductsList>, SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.products_rv)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private List<Products> products = new ArrayList<>();
    private CatalogAdapter adapter;
    private Unbinder unbinder;
    @Override
    public void onResponse(Call<ProductsList> call, Response<ProductsList> response) {
        swipeRefreshLayout.setRefreshing(false);
        if(response.isSuccessful()){
            products = Collections.emptyList();
            products = response.body().getProducts();
            adapter.setCatalog(products);
        }else{
            Toast.makeText(CatalogActivity.this,"Response Failed",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycledViewPool.clear();
        unbinder.unbind();
    }

    @Override
    public void onFailure(Call<ProductsList> call, Throwable t) {
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(CatalogActivity.this,t.getMessage().toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCatalogClicked(Product product) {
        String productJson = new Moshi.Builder().build().adapter(Product.class).toJson(product);
        Intent intent = new Intent(CatalogActivity.this, DetailActivity.class);
        intent.putExtra("product_detail", productJson);
        startActivity(intent);
    }

    @Override
    public void onCatalogLongClicked(int position) {

    }

    @Override
    public void onAddtoCartClicked(int position) {

    }

    @Override
    public void onShowMoreClicked(String category) {
        //Toast.makeText(CatalogActivity.this,category,Toast.LENGTH_SHORT).show();
        Products p = new Products();
        for(int i=0;i<products.size();i++){
            if(products.get(i).getCategory().equalsIgnoreCase(category)){
                p.setProduct(products.get(i).getProduct());
            }
        }
        String productJson = new Moshi.Builder().build().adapter(Products.class).toJson(p);
        Intent intent = new Intent(CatalogActivity.this, SubCatalogActivity.class);
        intent.putExtra("products", productJson);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {

        getProduct();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        unbinder = ButterKnife.bind(this);

        adapter = new CatalogAdapter();
        adapter.setCallback(this);
        recycledViewPool.setMaxRecycledViews(R.layout.product_card_view, 100);
        recycledViewPool.setMaxRecycledViews(R.layout.product_header_view, 10);
        recycledViewPool.setMaxRecycledViews(R.layout.product_footer_view,10);
        recyclerView.setRecycledViewPool(recycledViewPool);


        adapter.setSpanCount(ScreenUtils.calculateNoOfColumns(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, ScreenUtils.calculateNoOfColumns(this));
        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        getProduct();

    }

    private void getProduct(){
        swipeRefreshLayout.setRefreshing(true);
        ProductService.getApi()
                .fetchProductsList()
                .enqueue(this);
    }

}
