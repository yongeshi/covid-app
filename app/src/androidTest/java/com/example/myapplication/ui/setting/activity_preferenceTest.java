package com.example.myapplication.ui.setting;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.TypedValue;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.PreferenceMatchers.isEnabled;
import static androidx.test.espresso.matcher.PreferenceMatchers.withKey;
import static androidx.test.espresso.matcher.PreferenceMatchers.withSummary;
import static androidx.test.espresso.matcher.PreferenceMatchers.withSummaryText;
import static androidx.test.espresso.matcher.PreferenceMatchers.withTitle;
import static androidx.test.espresso.matcher.PreferenceMatchers.withTitleText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.matcher.PreferenceMatchers;

import com.example.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class
activity_preferenceTest {
    @Rule
    public ActivityTestRule<activity_preference> pActivityTestRule
            = new ActivityTestRule<>(activity_preference.class);

    private activity_preference aPref = null;

    @Before
    public void setUp() throws Exception {
        aPref = pActivityTestRule.getActivity();
    }
    @Test
    public void testSettingLaunch() {
        assertNotNull("Setting page cannot be launched", aPref);
    }

    @Test
    public void testPreferenceClick(){
        onData(withKey("BUTTON")).perform(click());
    }

//    @Test
//    public void testCheckList(){
//        // Check if it is displayed
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//
//        onData(allOf(
//                is(instanceOf(activity_preference.class)),
//                withKey(appContext.getResources().getString(R.string.fetch_location))))
//                .check(matches(isDisplayed()));
//    }


//    @Test
//    public void testWithTitle() {
////        CheckBoxPreference pref = new CheckBoxPreference(InstrumentationRegistry.getInstrumentation().getContext());
////        pref.setTitle();
////        assertThat(pref, withTitle(R.string.other_string));
////        assertThat(pref, not(withTitle(R.string.something)));
////        assertThat(pref, withTitleText("Goodbye!!"));
////        assertThat(pref, not(withTitleText(("Hello Mars"))));
////        assertThat(pref, withTitleText(is("Goodbye!!")));
//        // Check if it is displayed
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//
//        onData(allOf(
//                is(instanceOf(activity_preference.class)),
//                withKey(appContext.getResources().getString(R.string.BUTTON))))
//                .check(matches(isDisplayed()));
//
////        // Check if click is working
////        onData(allOf(
////                is(instanceOf(activity_preference.class)),
////                withKey(appContext.getResources().getString(R.string.NIGHT_MODE))))
////                .onChildView(withText(appContext.getResources()
////                        .getString(R.string.pref_units_label))).perform(click());
//    }
    @Test
    public void testWithKey() {

        CheckBoxPreference pref = new CheckBoxPreference(getInstrumentation().getContext());
        pref.setKey("fetch_location");
        assertThat(pref, withKey("fetch_location"));
        assertThat(pref, not(withKey("bar")));
        assertThat(pref, withKey(is("fetch_location")));

        CheckBoxPreference pref_noti = new CheckBoxPreference(getInstrumentation().getContext());
        pref_noti.setKey("NOTI");
        assertThat(pref_noti, withKey("NOTI"));
        assertThat(pref_noti, not(withKey("notification")));
        assertThat(pref_noti, withKey(is("NOTI")));
    }

    @Test
    public void testWithSummary() {
        CheckBoxPreference pref = new CheckBoxPreference(getInstrumentation().getContext());
        pref.setSummary("Night mode is activated");
        assertThat(pref, withSummaryText("Night mode is activated"));
        assertThat(pref, not(withSummaryText("Night mode is deactivated")));
        assertThat(pref, withSummaryText(is("Night mode is activated")));

        CheckBoxPreference pref_loc = new CheckBoxPreference(getInstrumentation().getContext());
        pref_loc.setSummary("Sharing is activated");
        assertThat(pref_loc, withSummaryText("Sharing is activated"));
        assertThat(pref_loc, not(withSummaryText("Sharing is deactivated")));
        assertThat(pref_loc, withSummaryText(is("Sharing is activated")));


        ListPreference pref2 = new ListPreference(getInstrumentation().getContext());
        pref2.setTitle("Screen Orientation");
        assertThat(pref2, withTitleText("Screen Orientation"));
        assertThat(pref2, not(withTitleText(("screen"))));
        assertThat(pref2, withTitleText(is("Screen Orientation")));

        Preference pref3 = new Preference(getInstrumentation().getContext());
        pref3.setTitle("about");
        assertThat(pref3, withTitleText("about"));
        assertThat(pref3, not(withTitleText(("ab"))));
        assertThat(pref3, withTitleText(is("about")));

    }

    @Test
    public void testWithTitle() {
        CheckBoxPreference pref = new CheckBoxPreference(getInstrumentation().getContext());
        pref.setTitle("fetch_location");
        assertThat(pref, withTitleText("fetch_location"));
        assertThat(pref, not(withTitleText(("Hello Mars"))));
        assertThat(pref, withTitleText(is("fetch_location")));

        ListPreference pref2 = new ListPreference(getInstrumentation().getContext());
        pref2.setTitle("Screen Orientation");
        assertThat(pref2, withTitleText("Screen Orientation"));
        assertThat(pref2, not(withTitleText(("screen"))));
        assertThat(pref2, withTitleText(is("Screen Orientation")));

        Preference pref3 = new Preference(getInstrumentation().getContext());
        pref3.setTitle("about");
        assertThat(pref3, withTitleText("about"));
        assertThat(pref3, not(withTitleText(("ab"))));
        assertThat(pref3, withTitleText(is("about")));

    }

    @Test
    public void testIsEnabled() {
        CheckBoxPreference pref = new CheckBoxPreference(getInstrumentation().getContext());
        pref.setEnabled(true);
        assertThat(pref, isEnabled());
        pref.setEnabled(false);
        assertThat(pref, not(isEnabled()));

        ListPreference pref2 = new ListPreference(getInstrumentation().getContext());
        pref2.setEnabled(true);
        assertThat(pref2, isEnabled());
        pref2.setEnabled(false);
        assertThat(pref2, not(isEnabled()));

        Preference pref3 = new Preference(getInstrumentation().getContext());
        pref3.setEnabled(true);
        assertThat(pref3, isEnabled());
        pref3.setEnabled(false);
        assertThat(pref3, not(isEnabled()));

    }

    @After
    public void tearDown() throws Exception {
        aPref = null;
    }
}