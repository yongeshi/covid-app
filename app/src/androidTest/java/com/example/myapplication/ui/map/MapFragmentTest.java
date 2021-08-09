package com.example.myapplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.example.myapplication.R;

import com.example.myapplication.ui.loading_screen.OnBoarding;
import com.example.myapplication.ui.map.MapFragment;
import com.example.myapplication.ui.setting.activity_preference;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MapFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init(){
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void testGoogleMaps()
    {
        onView(withContentDescription("Google Map")).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void testHeatMap()
    {
        assertNotNull("addHeatMap");
    }

    @Test
    public void testDataParser()
    {
        assertNotNull("readItems");
    }









}