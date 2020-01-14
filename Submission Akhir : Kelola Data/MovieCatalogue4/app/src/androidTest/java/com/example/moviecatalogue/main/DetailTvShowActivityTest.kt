package com.example.moviecatalogue.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailTvShowActivityTest {
    @Rule
    @JvmField
    val activityTestRule = object :
        ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailTvShowActivity::class.java)
            result.putExtra("id", 1412)
            return result
        }
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.detail_TvShow_photo))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.detail_TvShow_name))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.detail_TvShow_date))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.detail_TvShow_popularity))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.detail_TvShow_description))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.detail_TvShow_creator))
            .perform(ViewActions.scrollTo())
            .check(ViewAssertions.matches(isDisplayed()))
    }
}