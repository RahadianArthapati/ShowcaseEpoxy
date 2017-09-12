package com.grandline.showcaseepoxy.ui.components.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.ui.helpers.AppBarStateChangeListener;
import com.grandline.showcaseepoxy.utils.Constants;
import com.grandline.showcaseepoxy.utils.ScreenUtils;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by home on 9/11/17.
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.header_main_photo)
    ImageView mainPhotoView;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindString(R.string.article_recommendation_text) String recommendationsTitle;
    @BindString(R.string.article_brand_offer_text) String brandOfferTitle;
    private Unbinder unbinder;
    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();

    private DetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                onBackPressed();
            }
        });

        String detailJson = getIntent().getStringExtra("product_detail");
        try {
            final Product product = new Moshi.Builder().build().adapter(Product.class).fromJson(detailJson);
            initLayout(product);
            //Toast.makeText(this,product.getName(),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLayout(Product product){
        toolbar.setTitle(product.getName());
        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    toolbar.setTitle(product.getName());
                } else {
                    toolbar.setTitle("");
                }
            }
        });

        Glide.with(this)
                .load(Constants.IMG_URL+product.getImages().get(0).getName())
                .into(mainPhotoView);

        adapter = new DetailAdapter();
        adapter.setDetail(product);

        int spanCount = ScreenUtils.calculateNoOfColumns(this);

        recycledViewPool.setMaxRecycledViews(R.layout.model_cardview, 50);
        recyclerView.setRecycledViewPool(recycledViewPool);

        adapter.setSpanCount(spanCount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        gridLayoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycledViewPool.clear();
        unbinder.unbind();
    }
}

