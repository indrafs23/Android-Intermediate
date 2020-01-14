package com.example.moviecatalogue.utils

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.core.Is.`is`
import org.junit.Assert

class RecyclerViewItemCountAssertion(expectedResult: Int) : ViewAssertion {
    private var expectedCount = expectedResult

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
       if (noViewFoundException != null){
           throw noViewFoundException
       }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        Log.d("Masuk",adapter.toString())
        Assert.assertNotNull(adapter)
        Assert.assertThat(adapter?.itemCount, `is`(expectedCount))
    }
}