package com.grandline.showcaseepoxy.ui.models;

import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Pricing;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;
import com.grandline.showcaseepoxy.utils.Constants;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;

import static com.grandline.showcaseepoxy.utils.ObjectUtils.isEmpty;

/**
 * Created by home on 9/13/17.
 */

@EpoxyModelClass(layout = R.layout.view_card_full_product)
public abstract class ModelCardFullProduct extends EpoxyModelWithHolder<ModelCardFullProduct.ViewHolder> {

    @EpoxyAttribute
    public Product product;
    @EpoxyAttribute(hash = false)
    ModelCardFullProduct.OnModelClick clickListener;


    @Override
    public void bind(ModelCardFullProduct.ViewHolder holder) {
        if(!isEmpty(product.getName())){
            holder.name.setText(product.getName());
        }
        if(!isEmpty(product.getWeight())){
            holder.weight.setText(product.getWeight());
        }
        if(!isEmpty(product.getPricing().getPrice().toString())){
            Pricing price = product.getPricing();
            boolean sale = price.getPrice()>price.getPromoPrice()&&price.getPromoPrice()>0;
            holder.price_sale.setVisibility(sale? View.VISIBLE:View.GONE);
            holder.price_original.setPaintFlags(sale ?
                    holder.price_original.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                    : holder.price_original.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.price_original.setText(NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                    .format(price.getPrice()));
            holder.price_sale.setText(NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                    .format(price.getPromoPrice()));
        }
        if(!isEmpty(product.getPromotions().get(0).getPromoLabel())){
            holder.promo.setVisibility(View.VISIBLE);
            holder.promo.setText(product.getPromotions().get(0).getPromoLabel());
        }else{
            holder.promo.setVisibility(View.GONE);
        }
        if(!isEmpty(product.getImages().get(0).getName())){
            Glide.with(holder.poster.getContext()).load(Constants.IMG_URL+product.getImages().get(0).getName()).asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.poster);
        }
        holder.frame.setOnClickListener(v->clickListener.onClick(ModelCardFullProduct.this));
    }
    public interface OnModelClick {
        void onClick(ModelCardFullProduct model);
    }
    static class ViewHolder extends BaseEpoxyHolder {
        @BindView(R.id.frame)
        FrameLayout frame;
        @BindView(R.id.product_poster)
        ImageView poster;
        @BindView(R.id.title)
        TextView name;
        @BindView(R.id.weight)
        TextView weight;
        @BindView(R.id.price_original)
        TextView price_original;
        @BindView(R.id.price_sale)
        TextView price_sale;
        @BindView(R.id.promo_title)
        TextView promo;
    }
}

