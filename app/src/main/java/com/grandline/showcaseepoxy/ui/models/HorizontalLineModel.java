package com.grandline.showcaseepoxy.ui.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;

import butterknife.BindView;

/**
 * Created by home on 9/15/17.
 */
@EpoxyModelClass(layout = R.layout.horizontal_line_view)
public abstract class HorizontalLineModel extends EpoxyModelWithHolder<HorizontalLineModel.ViewHolder> {

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void bind(HorizontalLineModel.ViewHolder holder) {
        super.bind(holder);
        holder.horizontalLines.setVisibility(View.VISIBLE);
    }
    public static class ViewHolder extends BaseEpoxyHolder {
        @BindView(R.id.horizontal_line)
        View horizontalLines;
    }
}
