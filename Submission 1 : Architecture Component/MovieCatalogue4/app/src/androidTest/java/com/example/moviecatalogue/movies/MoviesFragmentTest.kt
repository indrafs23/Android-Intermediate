package com.example.moviecatalogue.movies

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.main.MainActivity
import com.example.moviecatalogue.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@LargeTest
class MoviesFragmentTest {

    @Rule
    @JvmField
    val activityTestRule =
        ActivityTestRule(MainActivity::class.java)
    private val academyFragment = MoviesFragment()

    @Before
    fun setUp() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.mainActivity, academyFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadMovie() {
        Thread.sleep(3000)
        onView(withId(R.id.moviesRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.moviesRecyclerView)).check(RecyclerViewItemCountAssertion(20))
    }
}
