package com.example.myapplication.ui.loading_screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import com.example.myapplication.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class OnBoardingTest {
    @Rule
    public ActivityTestRule<OnBoarding> mActivityTestRule =
            new ActivityTestRule<OnBoarding>(OnBoarding.class) {
                @Override
                protected void beforeActivityLaunched() {
                    clearSharedPrefs(InstrumentationRegistry.getTargetContext()); // This clears the sharedPreferences of the app so the tests/app opens the introduction slides every time
                    super.beforeActivityLaunched();
                }
            };

    private OnBoarding mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void onBoardingShowsCorrectlyOnFirstLoad() {
        assertNotNull("OnBoarding is not available", mActivity);
        ViewInteraction imageView = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.skip_btn), withText("SKIP"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.slider_desc), withText("Our map shows COVID severity of any place nearby or with-in the specified city. You can click on a city/neighborhood to display location related COVID information."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView.check(matches(withText("Our map shows COVID severity of any place nearby or with-in the specified city. You can click on a city/neighborhood to display location related COVID information.")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.slider_heading), withText("Access to COVID map wherever your are"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView2.check(matches(withText("Access to COVID map wherever your are")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.next_btn), withText("NEXT"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }

    @Test
    public void splashScreenOpensWithOnBoarding() {
        assertNotNull("OnBoarding is not available", mActivity);
        TypedValue outValue = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.splashThemeName, outValue, true);
        assertEquals("SplashThemeName", outValue.string);
    }

    @Test
    public void onBoardingFirstNextButtonFunctionsAndDisplaysCorrectPage() {
        ViewInteraction imageView = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.slider_heading), withText("Access to COVID map wherever your are"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView.check(matches(withText("Access to COVID map wherever your are")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.slider_desc), withText("Our map shows COVID severity of any place nearby or with-in the specified city. You can click on a city/neighborhood to display location related COVID information."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView2.check(matches(withText("Our map shows COVID severity of any place nearby or with-in the specified city. You can click on a city/neighborhood to display location related COVID information.")));

        ViewInteraction button = onView(
                allOf(withId(R.id.next_btn), withText("NEXT"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.slider_heading), withText("All your COVID related news in one spot"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView3.check(matches(withText("All your COVID related news in one spot")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.slider_desc), withText("You will able to view latest local COVID related news in the news page."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView4.check(matches(withText("You will able to view latest local COVID related news in the news page.")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.next_btn), withText("NEXT"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }

    @Test
    public void onBoardingSecondNextButtonFunctionsAndDisplaysCorrectPage() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.slider_heading), withText("All your COVID related news in one spot"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView3.check(matches(withText("All your COVID related news in one spot")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.slider_desc), withText("You will able to view latest local COVID related news in the news page."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView4.check(matches(withText("You will able to view latest local COVID related news in the news page.")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.next_btn), withText("NEXT"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.slider_heading), withText("Automatically tracks location"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView5.check(matches(withText("Automatically tracks location")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.slider_desc), withText("If your location settings are enabled, the app will handle tracking for you. The app will send you COVID notifications, so you can get the latest COVID updates while on the move."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView6.check(matches(withText("If your location settings are enabled, the app will handle tracking for you. The app will send you COVID notifications, so you can get the latest COVID updates while on the move.")));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.next_btn), withText("NEXT"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));
    }

    @Test
    public void onBoardingThirdNextButtonFunctionsAndDisplaysCorrectPage() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.slider_heading), withText("Automatically tracks location"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView5.check(matches(withText("Automatically tracks location")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.slider_desc), withText("If your location settings are enabled, the app will handle tracking for you. The app will send you COVID notifications, so you can get the latest COVID updates while on the move."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView6.check(matches(withText("If your location settings are enabled, the app will handle tracking for you. The app will send you COVID notifications, so you can get the latest COVID updates while on the move.")));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.next_btn), withText("NEXT"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.slider_heading), withText("You're in control"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView7.check(matches(withText("You're in control")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.slider_desc), withText("Your travel history will be stored for up to 21 days. It's easy to delete your past location history, and you can turn off location tracking at any time."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView8.check(matches(withText("Your travel history will be stored for up to 21 days. It's easy to delete your past location history, and you can turn off location tracking at any time.")));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.next_btn), withText("LETS GET STARTED"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));
    }

    @Test
    public void onBoardingLetsGetStartedButtonFunctionsAndDisplaysMain() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.slider_heading), withText("You're in control"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView7.check(matches(withText("You're in control")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.slider_desc), withText("Your travel history will be stored for up to 21 days. It's easy to delete your past location history, and you can turn off location tracking at any time."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView8.check(matches(withText("Your travel history will be stored for up to 21 days. It's easy to delete your past location history, and you can turn off location tracking at any time.")));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.next_btn), withText("LETS GET STARTED"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.next_btn), withText("Lets Get Started"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.map),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    @Test
    public void onBoardingFirstPageSkipButtonFunctionsAndDisplaysMain() {
        ViewInteraction imageView = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.skip_btn), withText("SKIP"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.slider_heading), withText("Access to COVID map wherever your are"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView.check(matches(withText("Access to COVID map wherever your are")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.slider_desc), withText("Our map shows COVID severity of any place nearby or with-in the specified city. You can click on a city/neighborhood to display location related COVID information."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView2.check(matches(withText("Our map shows COVID severity of any place nearby or with-in the specified city. You can click on a city/neighborhood to display location related COVID information.")));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.skip_btn), withText("Skip"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.map),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    @Test
    public void onBoardingSecondPageSkipButtonFunctionsAndDisplaysMain() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.slider_heading), withText("All your COVID related news in one spot"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView3.check(matches(withText("All your COVID related news in one spot")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.slider_desc), withText("You will able to view latest local COVID related news in the news page."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView4.check(matches(withText("You will able to view latest local COVID related news in the news page.")));

        ViewInteraction button = onView(
                allOf(withId(R.id.skip_btn), withText("SKIP"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.skip_btn), withText("Skip"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.map),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    @Test
    public void onBoardingThirdPageSkipButtonFunctionsAndDisplaysMain() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.next_btn), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.slider_image),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.slider_heading), withText("Automatically tracks location"),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView5.check(matches(withText("Automatically tracks location")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.slider_desc), withText("If your location settings are enabled, the app will handle tracking for you. The app will send you COVID notifications, so you can get the latest COVID updates while on the move."),
                        withParent(withParent(withId(R.id.slider))),
                        isDisplayed()));
        textView6.check(matches(withText("If your location settings are enabled, the app will handle tracking for you. The app will send you COVID notifications, so you can get the latest COVID updates while on the move.")));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.next_btn), withText("NEXT"),
                        withParent(allOf(withId(R.id.relativeLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.skip_btn), withText("SKIP"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.skip_btn), withText("Skip"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.map),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    /**
     * Clears everything in the SharedPreferences
     */
    private void clearSharedPrefs(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences("BOOT_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}