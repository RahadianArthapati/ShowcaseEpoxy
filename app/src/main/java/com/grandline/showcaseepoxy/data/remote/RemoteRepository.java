package com.grandline.showcaseepoxy.data.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.util.Log;

import com.grandline.showcaseepoxy.App;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.data.model.ProductsList;
import com.grandline.showcaseepoxy.data.source.ProductCache;
import com.grandline.showcaseepoxy.data.source.ProductService;
import com.grandline.showcaseepoxy.utils.Constants;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.MoshiSpeaker;
import retrofit2.Call;

import static com.grandline.showcaseepoxy.data.remote.ServiceError.NETWORK_ERROR;
import static com.grandline.showcaseepoxy.data.remote.ServiceError.SUCCESS_CODE;
import static com.grandline.showcaseepoxy.utils.Constants.ERROR_UNDEFINED;
import static com.grandline.showcaseepoxy.utils.NetworkUtils.isConnected;
import static com.grandline.showcaseepoxy.utils.ObjectUtils.isNull;

/**
 * Created by home on 8/29/17.
 */

public class RemoteRepository implements RemoteSource {
    private ServiceGenerator serviceGenerator;
    private final String UNDELIVERABLE_TAG = "Undeliverable exception";
    private ProductCache cacheProviders;
    @Inject
    public RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
        cacheProviders = new RxCache.Builder()
                .setMaxMBPersistenceCache(5)
                .persistence(App.getContext().getFilesDir(), new MoshiSpeaker())
                .using(ProductCache.class);
    }

    @Override
    public Single fetchProducts(String category,boolean evict){
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i(UNDELIVERABLE_TAG, throwable.getMessage());
            return;
        });
        Single<List<Products>> products = Single.create(singleOnSubscribe ->{
            if(!isConnected(App.getContext())){
                Exception exception = new NetworkErrorException();
                singleOnSubscribe.onError(exception);
            }else{
                try{
                    ProductService productService = serviceGenerator.createService(ProductService.class, Constants.BASE_URL);
                    ServiceResponse serviceResponse = processCall(productService.fetchProducts(category), false);
                    if(serviceResponse.getCode() == SUCCESS_CODE){
                        ProductsList productsList = (ProductsList) serviceResponse.getData();
                        List<Products> prod = productsList.getProducts();
                        singleOnSubscribe.onSuccess(prod);
                    }else{
                        Throwable throwable = new NetworkErrorException();
                        singleOnSubscribe.onError(throwable);
                    }
                } catch (Exception e){
                    singleOnSubscribe.onError(e);
                }
            }
        });
        return products;
    }

    @NonNull
    private ServiceResponse processCall(Call call, boolean isVoid){
        if(!isConnected(App.getContext())){
            return new ServiceResponse(new ServiceError());
        }
        try{

            retrofit2.Response<ProductsList> response = call.execute();
            if(isNull(response)){
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if(response.isSuccessful()){
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            }else{
                ServiceError serviceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(serviceError);
            }
        }catch (IOException e){
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }
}
