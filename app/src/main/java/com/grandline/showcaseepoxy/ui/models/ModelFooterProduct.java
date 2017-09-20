package com.grandline.showcaseepoxy.ui.models;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;

import butterknife.BindView;

/**
 * Created by home on 9/13/17.
 */
@EpoxyModelClass(layout = R.layout.view_footer_product)

public abstract class ModelFooterProduct extends EpoxyModelWithHolder<ModelFooterProduct.ViewHolder> {
    @EpoxyAttribute
    Products products;
    @EpoxyAttribute(hash = false)
    ModelFooterProduct.OnModelClick clickListener;

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void bind(ModelFooterProduct.ViewHolder holder) {
        super.bind(holder);
        holder.showMoreView.setOnClickListener(v->clickListener.onClick(products));
    }
    public static class ViewHolder extends BaseEpoxyHolder {
        @BindView(R.id.show_more_text)
        TextView showMoreView;
    }
    public interface OnModelClick {
        void onClick(Products products);
    }
}
