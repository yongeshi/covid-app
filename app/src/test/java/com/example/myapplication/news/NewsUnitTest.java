package com.example.myapplication.news;

import com.example.myapplication.ui.news.NewsFragment;
import com.example.myapplication.ui.news.Utils;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class NewsUnitTest {
    
    @Test
    public void testDateFormat() {
        //oldStringDate = 2021-03-27T09:58:00Z
        //newDate = Sat, 27 Mar 2021
        assertEquals("Sat, 27 Mar 2021", Utils.DateFormat("2021-03-27T09:58:00Z"));
        assertEquals("Date not found", Utils.DateFormat("Sunday"));
    }
}
