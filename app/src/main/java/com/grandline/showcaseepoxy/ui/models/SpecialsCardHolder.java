package com.grandline.showcaseepoxy.ui.models;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;

import butterknife.BindView;

/**
 * Created by home on 9/15/17.
 */
@EpoxyModelClass(layout = R.layout.special_card_view_holder)

public abstract class SpecialsCardHolder<A extends EpoxyAdapter> extends EpoxyModelWithHolder<SpecialsCardHolder.RecyclerHolder>{

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
    public void bind(SpecialsCardHolder.RecyclerHolder holder) {
        super.bind(holder);
        holder.recyclerView.swapAdapter(adapter, false);

    }

    @Override
    public void unbind(SpecialsCardHolder.RecyclerHolder holder) {
        super.unbind(holder);
        holder.recyclerView.swapAdapter(null,true);

    }

    static class RecyclerHolder extends BaseEpoxyHolder{
        @BindView(R.id.recycler_special)
        RecyclerView recyclerView;

        @Override
        protected void bindView(View itemView) {
            super.bindView(itemView);
            //GridLayoutManager layout = new GridLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL,2,false);
            LinearLayoutManager layout = new LinearLayoutManager(itemView.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            recyclerView.setLayoutManager(layout);
            recyclerView.setHasFixedSize(true);
        }
    }
}
