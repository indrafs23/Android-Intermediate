package com.example.moviecatalogue.main

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import org.junit.Rule
import org.junit.Test

class DetailTvShowActivityTest {
    @Rule
    @JvmField
    val activityTestRule = object :
        ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity::class.java){
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailTvShowActivity::class.java)
            result.putExtra("id",1412)
            return result
        }
    }

    @Test
    fun loadDetailTvShow() {
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.detail_TvShow_photo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_TvShow_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_TvShow_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_TvShow_popularity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_TvShow_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detail_TvShow_creator))
            .perform(ViewActions.scrollTo()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}