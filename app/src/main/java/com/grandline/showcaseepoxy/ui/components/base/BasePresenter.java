package com.grandline.showcaseepoxy.ui.components.base;


import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;


public abstract class BasePresenter<T extends BaseView> {

    private WeakReference<T> view;

    protected AtomicBoolean isViewAlive = new AtomicBoolean();

    public T getView() {
        return view.get();
    }


    public void setView(T view) {
        this.view = new WeakReference<>(view);
    }

    public void initialize(Bundle extras) {
    }

    public void start() {
        isViewAlive.set(true);
    }

    public void finalizeView() {
        isViewAlive.set(false);
    }

}
