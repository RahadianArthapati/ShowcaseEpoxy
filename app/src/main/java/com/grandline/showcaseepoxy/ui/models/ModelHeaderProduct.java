package com.grandline.showcaseepoxy.ui.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;

import butterknife.BindView;

/**
 * Created by home on 9/8/17.
 */
@EpoxyModelClass(layout = R.layout.view_header_product)

public abstract class ModelHeaderProduct extends EpoxyModelWithHolder<ModelHeaderProduct.ViewHolder> {
    @EpoxyAttribute
    String title;
    @EpoxyAttribute
    int count;

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void bind(ModelHeaderProduct.ViewHolder holder) {
        super.bind(holder);
        holder.titleView.setText(title);
        //holder.countView.setText((count>1)?String.valueOf(count)+" products":String.valueOf(count+" product"));
        if(count==0){
            holder.countView.setVisibility(View.GONE);
        }else{
            holder.countView.setVisibility(View.VISIBLE);
            holder.countView.setText(String.valueOf(count));
        }
    }
    public static class ViewHolder extends BaseEpoxyHolder {
        @BindView(R.id.title_text)
        TextView titleView;
        @BindView(R.id.count_text)
        TextView countView;
    }
}
