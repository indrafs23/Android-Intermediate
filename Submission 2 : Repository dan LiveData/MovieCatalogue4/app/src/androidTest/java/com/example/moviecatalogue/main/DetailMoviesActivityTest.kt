package com.example.moviecatalogue.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.Utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test




class DetailMoviesActivityTest {
    @Rule
    @JvmField
    val activityTestRule = object :
        ActivityTestRule<DetailMoviesActivity>(DetailMoviesActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailMoviesActivity::class.java)
            result.putExtra("id", 475557)
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
    fun loadDetailMovie() {
        onView(withId(R.id.detail_film_photo)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_film_name)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_film_date)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_film_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_film_description)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_film_director)).perform(scrollTo()).check(matches(isDisplayed()))
    }
}