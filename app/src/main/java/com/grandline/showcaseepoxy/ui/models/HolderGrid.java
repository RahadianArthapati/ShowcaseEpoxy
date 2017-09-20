package com.grandline.showcaseepoxy.ui.models;

/**
 * Created by home on 9/19/17.
 */

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;

import butterknife.BindView;

/**
 * Created by home on 9/19/17.
 */

@EpoxyModelClass(layout = R.layout.view_holder_special_child)

public abstract class HolderGrid<A extends EpoxyAdapter> extends EpoxyModelWithHolder<HolderGrid.RecyclerHolder> {

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
    public void bind(HolderGrid.RecyclerHolder holder) {
        super.bind(holder);
        holder.recyclerView.swapAdapter(adapter, false);

    }

    @Override
    public void unbind(HolderGrid.RecyclerHolder holder) {
        super.unbind(holder);
        holder.recyclerView.swapAdapter(null,true);

    }

    static class RecyclerHolder extends BaseEpoxyHolder {
        @BindView(R.id.recycler)
        RecyclerView recyclerView;

        @Override
        protected void bindView(View itemView) {
            super.bindView(itemView);
            RecyclerView.LayoutManager layout = new GridLayoutManager(itemView.getContext(), 2);
            recyclerView.setLayoutManager(layout);
            recyclerView.setHasFixedSize(true);
        }
    }
}