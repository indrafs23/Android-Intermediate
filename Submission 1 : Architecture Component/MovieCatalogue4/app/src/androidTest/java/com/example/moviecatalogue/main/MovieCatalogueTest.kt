package com.example.moviecatalogue.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import org.junit.Rule
import org.junit.Test

class MovieCatalogueTest {
    @Rule
    @JvmField
    val activityTestRule =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickListMovie(){
        Thread.sleep(3000)
        onView(withId(R.id.moviesRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.moviesRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        Thread.sleep(3000)
        onView(withId(R.id.detail_film_name)).check(matches(isDisplayed()))
    }

    @Test
    fun clickListTvShow(){
        Thread.sleep(3000)
        onView(withId(R.id.navigation_tvShow)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.tvShowRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.tvShowRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        Thread.sleep(3000)
        onView(withId(R.id.detail_TvShow_name)).check(matches(isDisplayed()))
    }
}