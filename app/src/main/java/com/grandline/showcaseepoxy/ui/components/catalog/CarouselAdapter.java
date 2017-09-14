package com.grandline.showcaseepoxy.ui.components.catalog;


import com.airbnb.epoxy.EpoxyAdapter;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.ui.models.ProductCardModel;
import com.grandline.showcaseepoxy.ui.models.ProductCardModel_;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarouselAdapter extends EpoxyAdapter {

    public CarouselAdapter(List<Product> product, ProductCardModel.OnModelClick listener){
        ArrayList<Product> new_product = new ArrayList<>(5);
        for(Product p:product){
            if(!p.getPromotions().get(0).getType().equals(0)){
                new_product.add(p);
            }
        }
        if(new_product.size()<5){
            ArrayList<Product> sorted_product = new ArrayList<>(product);
            Collections.sort(sorted_product, new Comparator<Product>() {
                @Override
                public int compare(Product product, Product t1) {
                    return t1.getRating().compareTo(product.getRating());
                }
            });
            for(int i=0;i<sorted_product.size();i++){
                if(new_product.size()<5 && !new_product.contains(sorted_product.get(i))){
                    new_product.add(sorted_product.get(i));
                }
            }
        }
        for (Product p:new_product) {
            addModel(new ProductCardModel_()
                    .product(p)
                    .clickListener(listener)
            );
        }
    }

}
