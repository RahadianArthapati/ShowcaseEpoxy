package com.grandline.showcaseepoxy.ui.models;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;

import butterknife.BindView;

/**
 * Created by home on 9/13/17.
 */
@EpoxyModelClass(layout = R.layout.product_footer_view)

public abstract class ProductFooterModel extends EpoxyModelWithHolder<ProductFooterModel.ViewHolder> {
    @EpoxyAttribute
    String title;
    @EpoxyAttribute(hash = false)
    ProductFooterModel.OnModelClick clickListener;

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void bind(ProductFooterModel.ViewHolder holder) {
        super.bind(holder);
        holder.showMoreView.setOnClickListener(v->clickListener.onClick(title));
    }
    public static class ViewHolder extends BaseEpoxyHolder {
        @BindView(R.id.show_more_text)
        TextView showMoreView;
    }
    public interface OnModelClick {
        void onClick(String category);
    }
}
