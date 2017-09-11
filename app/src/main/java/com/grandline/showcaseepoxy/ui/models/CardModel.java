package com.grandline.showcaseepoxy.ui.models;

/**
 * Created by home on 9/7/17.
 */

import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.Glide;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;

import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.model_cardview)
public abstract class CardModel extends EpoxyModelWithHolder<CardModel.CardHolder> {

    @EpoxyAttribute
    String title;
    @EpoxyAttribute
    String url;

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void bind(CardHolder holder) {
        super.bind(holder);
        holder.titleView.setText(title);
        Glide.with(holder.imageView.getContext())
                .load(url)
                .fitCenter()
                .into(holder.imageView);
    }

    static class CardHolder extends BaseEpoxyHolder {
        @BindView(R.id.title)
        TextView titleView;
        @BindView(R.id.product_poster)
        ImageView imageView;
    }

}
