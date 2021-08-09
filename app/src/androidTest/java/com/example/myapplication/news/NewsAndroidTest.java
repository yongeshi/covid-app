package com.example.myapplication.news;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@RunWith(AndroidJUnit4.class)
public class NewsAndroidTest {

    private MainActivity mainActivity;
    @Rule
    public final ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mainActivity = mainActivityTestRule.getActivity();
        assertThat(mainActivity, notNullValue());
    }

    @Test
    public void checkNewsSelect() {
        onView(allOf(withId(R.id.news), isDescendantOfA(withId(R.id.nav_view))))
                .perform(click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.topheadlines)).perform(waitUntilGone(5000));
        onView(withId(R.id.topheadlines))
                .check(matches(isDisplayed()));

        onView(withId(R.id.errorLayout))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void newsItemToDetail() {
        onView(allOf(withId(R.id.news), isDescendantOfA(withId(R.id.nav_view))))
                .perform(click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.topheadlines)).perform(waitUntilGone(5000));

        onView(withId(R.id.recyclerView))
                .perform(
                        RecyclerViewActions.scrollToPosition(0),
                        RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.appbar)).perform(waitUntilGone(5000));
        onView(withId(R.id.appbar))
                .check(matches(isDisplayed()));
    }

    public static ViewAction waitId(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return any(View.class);
            }

            @Override
            public String getDescription() {
                return "Wait for a specific view with id < > during"
                        + millis + "milliseconds";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                //final Matcher<View> viewMatcher = withId(viewId);

                do {
                    if (view.getVisibility() == View.VISIBLE) {
                        return ;
                    }
                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    public ViewAction waitUntilGone(final long millis) {
        return waitId(millis);
    }
}
