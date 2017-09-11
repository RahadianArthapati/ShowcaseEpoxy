package com.grandline.showcaseepoxy.ui.models;


import android.support.annotation.IdRes;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.models.base.BaseEpoxyHolder;


import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.model_title_view)
public abstract class TitleModel extends EpoxyModelWithHolder<TitleModel.TitleHolder> {

    @EpoxyAttribute
    String title;

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void bind(TitleHolder holder) {
        super.bind(holder);
        holder.titleView.setText(title);
    }

    public static class TitleHolder extends BaseEpoxyHolder {
        @BindView(R.id.title_text)
        TextView titleView;
    }

}
