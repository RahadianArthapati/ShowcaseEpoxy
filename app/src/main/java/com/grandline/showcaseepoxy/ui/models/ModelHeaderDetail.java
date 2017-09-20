package com.grandline.showcaseepoxy.ui.models;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Pricing;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;

import static com.grandline.showcaseepoxy.utils.ObjectUtils.isEmpty;

/**
 * Created by home on 9/11/17.
 */

@EpoxyModelClass(layout = R.layout.view_header_detail)
public abstract class ModelHeaderDetail extends EpoxyModelWithHolder<ModelHeaderDetail.ViewHolder> {

    @EpoxyAttribute
    Product product;

    @Override public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override public void bind(ViewHolder holder) {

        holder.brandNameView.setText(product.getBrands());
        holder.productNameView.setText(product.getName());

        if(!isEmpty(product.getPricing().getPrice().toString())){
            Pricing price = product.getPricing();
            boolean sale = price.getPrice()>price.getPromoPrice()&&price.getPromoPrice()>0;
            holder.priceSaleView.setVisibility(sale?View.VISIBLE:View.GONE);
            holder.priceOriginalView.setPaintFlags(sale ?
                    holder.priceOriginalView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                    : holder.priceOriginalView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.priceOriginalView.setText(NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                    .format(price.getPrice()));
            holder.priceSaleView.setText(NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                    .format(price.getPromoPrice()));
        }
    }

    static class ViewHolder extends BaseEpoxyHolder {
        @BindView(R.id.header_brand_name)
        TextView brandNameView;
        @BindView(R.id.header_product_name) TextView productNameView;
        @BindView(R.id.header_price_original) TextView priceOriginalView;
        @BindView(R.id.header_price_sale) TextView priceSaleView;
    }
}