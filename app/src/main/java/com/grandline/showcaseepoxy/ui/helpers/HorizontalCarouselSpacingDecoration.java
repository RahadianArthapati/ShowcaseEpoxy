package com.grandline.showcaseepoxy.ui.helpers;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

public class HorizontalCarouselSpacingDecoration extends ItemDecoration {
    private static final int EDGE_PADDING_DP = 16;
    private static final int INNER_PADDING_DP = 8;
    private int edgePadding = -1;
    private int innerPadding = -1;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        if (edgePadding == -1 || innerPadding == -1) {
            DisplayMetrics m = view.getResources().getDisplayMetrics();
            edgePadding = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, EDGE_PADDING_DP, m);
            innerPadding = (int) TypedValue.applyDimension(COMPLEX_UNIT_DIP, INNER_PADDING_DP, m);
        }

        int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        int itemsCount = state.getItemCount();

        outRect.setEmpty();

        if (itemsCount == 1) {
            //only item
            outRect.left = edgePadding;
            outRect.right = edgePadding;
        } else if (itemPosition == 0) {
            //first item
            outRect.left = edgePadding;
            outRect.right = innerPadding / 2;
        } else if (itemPosition == itemsCount - 1) {
            //last item
            outRect.left = innerPadding / 2;
            outRect.right = edgePadding;
        } else {
            outRect.left = innerPadding / 2;
            outRect.right = innerPadding / 2;
        }
    }
}