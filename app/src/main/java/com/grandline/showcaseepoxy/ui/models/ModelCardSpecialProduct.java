package com.grandline.showcaseepoxy.ui.models;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.data.model.Special;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;
import com.grandline.showcaseepoxy.utils.Constants;

import butterknife.BindView;

import static com.grandline.showcaseepoxy.utils.ObjectUtils.isEmpty;

/**
 * Created by home on 9/19/17.
 */
@EpoxyModelClass(layout = R.layout.view_card_special_product)
public abstract class ModelCardSpecialProduct extends EpoxyModelWithHolder<ModelCardSpecialProduct.ViewHolder> {

    @EpoxyAttribute
    public Special special;
    @EpoxyAttribute(hash = false)
    ModelCardSpecialProduct.OnModelClick clickListener;
    @Override

    public void bind(ModelCardSpecialProduct.ViewHolder holder) {
        if(!isEmpty(special.getName())){
            holder.title.setText(special.getName());
        }
        if(!isEmpty(special.getImage())){
            Glide.with(holder.poster.getContext()).load(Constants.IMG_URL+special.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.poster);
        }
        holder.frame.setOnClickListener(v->clickListener.onClick(ModelCardSpecialProduct.this));
    }
    public interface OnModelClick {
        void onClick(ModelCardSpecialProduct model);
    }
    static class ViewHolder extends BaseEpoxyHolder {
        @BindView(R.id.frame)
        FrameLayout frame;
        @BindView(R.id.special_poster)
        ImageView poster;
        @BindView(R.id.special_title)
        TextView title;
    }
}
