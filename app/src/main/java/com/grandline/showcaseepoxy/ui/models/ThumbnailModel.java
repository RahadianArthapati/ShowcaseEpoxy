package com.grandline.showcaseepoxy.ui.models;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Product;
import com.grandline.showcaseepoxy.data.model.Products;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;
import com.grandline.showcaseepoxy.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.model_thumbnail_view)
public abstract class ThumbnailModel extends EpoxyModelWithHolder<ThumbnailModel.ThumbnailHolder> {

    @EpoxyAttribute
    public Product product;
    @EpoxyAttribute(hash = false) OnModelClick clickListener;

    @Override
    public void bind(ThumbnailHolder holder) {
        holder.nameView.setText(product.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(ThumbnailModel.this);
            }
        });

        Picasso.with(holder.imageView.getContext())
                .load(Constants.IMG_URL+product.getImages().get(0).getName())
                .fit()
                .centerInside()
                .placeholder(R.color.medium_grey)
                .into(holder.imageView);
    }

    public interface OnModelClick {
        void onClick(ThumbnailModel model);
    }

    static class ThumbnailHolder extends BaseEpoxyHolder {
        @BindView(R.id.thumbnail_image)
        ImageView imageView;
        @BindView(R.id.thumbnail_name)
        TextView nameView;
    }
}
