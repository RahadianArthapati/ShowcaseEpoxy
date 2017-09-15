package com.grandline.showcaseepoxy.di;

import com.grandline.showcaseepoxy.ui.components.catalog.CatalogFragment;

import javax.inject.Singleton;
import dagger.Component;


/**
 * Created by home on 8/29/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(CatalogFragment fragment);
}
