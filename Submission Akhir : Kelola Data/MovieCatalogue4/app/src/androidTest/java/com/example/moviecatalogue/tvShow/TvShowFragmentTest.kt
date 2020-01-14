package com.example.moviecatalogue.tvShow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.main.MainActivity
import com.example.moviecatalogue.utils.EspressoIdlingResource
import com.example.moviecatalogue.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {

    @Rule
    @JvmField
    val activityTestRule =
        ActivityTestRule(MainActivity::class.java)
    private val academyFragment = TvShowFragment()

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.navigation_tvShow)).perform(click())
        onView(withId(R.id.tvShowRecyclerView))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvShowRecyclerView))
            .check(RecyclerViewItemCountAssertion(20))
    }
}