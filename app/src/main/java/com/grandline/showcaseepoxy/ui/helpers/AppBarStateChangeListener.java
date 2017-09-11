package com.grandline.showcaseepoxy.ui.helpers;


import android.support.design.widget.AppBarLayout;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    private State currentState = State.IDLE;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0 && currentState != State.EXPANDED) {
            onStateChanged(appBarLayout, State.EXPANDED);
            currentState = State.EXPANDED;
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()
                && currentState != State.COLLAPSED) {
            onStateChanged(appBarLayout, State.COLLAPSED);
            currentState = State.COLLAPSED;
        } else if (currentState != State.IDLE) {
            onStateChanged(appBarLayout, State.IDLE);
            currentState = State.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);

    public enum State {
        COLLAPSED, EXPANDED, IDLE
    }
}
