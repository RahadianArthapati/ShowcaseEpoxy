package com.grandline.showcaseepoxy.ui.models;

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
@EpoxyModelClass(layout = R.layout.view_holder_card)

public abstract class HolderLinearVertical<A extends EpoxyAdapter> extends EpoxyModelWithHolder<HolderLinearVertical.RecyclerHolder>{

    @EpoxyAttribute
    A adapter;

    @EpoxyAttribute
    int type;

    @Override
    public boolean shouldSaveViewState() {
        return true;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void bind(HolderLinearVertical.RecyclerHolder holder) {
        super.bind(holder);
        holder.recyclerView.swapAdapter(adapter, false);

    }

    @Override
    public void unbind(HolderLinearVertical.RecyclerHolder holder) {
        super.unbind(holder);
        holder.recyclerView.swapAdapter(null,true);

    }

    static class RecyclerHolder extends BaseEpoxyHolder{
        @BindView(R.id.recycler)
        RecyclerView recyclerView;
        @Override
        protected void bindView(View itemView) {
            super.bindView(itemView);
            LinearLayoutManager layout = new LinearLayoutManager(itemView.getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            recyclerView.setLayoutManager(layout);
            recyclerView.setHasFixedSize(true);
        }
    }
}
