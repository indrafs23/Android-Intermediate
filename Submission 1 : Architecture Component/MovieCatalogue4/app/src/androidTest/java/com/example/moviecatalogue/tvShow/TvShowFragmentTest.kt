package com.example.moviecatalogue.tvShow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.main.MainActivity
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
    fun setUp() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.mainActivity, academyFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadTvShow() {
        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.moviesRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.moviesRecyclerView))
            .check(RecyclerViewItemCountAssertion(20))
    }
}