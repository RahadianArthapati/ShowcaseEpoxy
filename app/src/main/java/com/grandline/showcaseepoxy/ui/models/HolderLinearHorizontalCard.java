package com.grandline.showcaseepoxy.ui.models;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.View;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.helpers.HorizontalCarouselSpacingDecoration;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;


import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.view_holder_carousel)

public abstract class HolderLinearHorizontalCard<A extends EpoxyAdapter> extends EpoxyModelWithHolder<HolderLinearHorizontalCard.RecyclerHolder> {

    @EpoxyAttribute
    A adapter;


    @Override
    public boolean shouldSaveViewState() {
        return true;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void bind(RecyclerHolder holder) {
        super.bind(holder);
        holder.recyclerView.swapAdapter(adapter, false);

    }

    @Override
    public void unbind(RecyclerHolder holder) {
        super.unbind(holder);
        holder.recyclerView.swapAdapter(null,true);

    }

    static class RecyclerHolder extends BaseEpoxyHolder {
        @BindView(R.id.recycler)
        RecyclerView recyclerView;

        @Override
        protected void bindView(View itemView) {
            super.bindView(itemView);
            LinearLayoutManager layout = new LinearLayoutManager(itemView.getContext());
            layout.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.addItemDecoration(new HorizontalCarouselSpacingDecoration());
            recyclerView.setLayoutManager(layout);
            recyclerView.setHasFixedSize(true);
            SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
            snapHelperStart.attachToRecyclerView(recyclerView);

        }
    }
}
