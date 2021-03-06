package com.grandline.showcaseepoxy.domain;
import android.content.Context;

import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.data.model.ProductsList;
import com.grandline.showcaseepoxy.data.remote.RemoteRepository;
import com.grandline.showcaseepoxy.data.source.ProductsCallback;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.Reply;

/**
 * Created by home on 9/4/17.
 */

public class ProductsDomain {
    private RemoteRepository repository;
    private CompositeDisposable compositeDisposable;
    private Disposable productsListDisposable;
    private Single<ProductsList> productListSingle;
    private DisposableSingleObserver<ProductsList> disposableSingleObserver;

    @Inject
    public ProductsDomain(RemoteRepository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getProducts(ProductsCallback callback, Context context, String category, boolean evict) {
        disposableSingleObserver = new DisposableSingleObserver<ProductsList>() {
            @Override
            public void onSuccess(ProductsList products) {
                callback.onSuccess(products);
            }

            @Override
            public void onError(Throwable e) {
                callback.onFail(e.getMessage());
            }
        };
        if (!compositeDisposable.isDisposed()) {
            productListSingle = repository.fetchProducts(context, category,evict);
            productsListDisposable = productListSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(disposableSingleObserver);
            compositeDisposable.add(productsListDisposable);
        }
    }
    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.remove(productsListDisposable);
        }
    }
}
