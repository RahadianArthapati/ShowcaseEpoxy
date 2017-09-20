package com.grandline.showcaseepoxy.catalog;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.grandline.showcaseepoxy.R;
import com.grandline.showcaseepoxy.ui.components.HomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import butterknife.OnClick;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by home on 9/19/17.
 */
@RunWith(AndroidJUnit4.class)
public class CatalogPresenterTest {
    @Rule
    public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void shouldBeAbleToLoadProducts() throws InterruptedException
    {
        Thread.sleep(3000);
        onView(withId(R.id.products_rv)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToClickShowMore() throws InterruptedException
    {
        Thread.sleep(3000);
        onView(withId(R.id.show_more_text)).perform(click());
        onView(withId(R.id.subcatalog_rv)).check(matches(isDisplayed()));
        //onView(withId(R.id.subcatalog_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //onView(withText("Coffee")).check(matches(isDisplayed()));
    }
}
