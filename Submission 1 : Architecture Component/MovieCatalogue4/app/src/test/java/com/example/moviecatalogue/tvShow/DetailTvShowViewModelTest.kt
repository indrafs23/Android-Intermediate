package com.example.moviecatalogue.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.Unit.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailTvShowViewModelTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel

    @Before
    fun before(){
        detailTvShowViewModel = DetailTvShowViewModel()
    }

    @Test
    fun getListTvShow() {
        val liveData = detailTvShowViewModel.loadDetailListTvShow(1412)
        Assert.assertEquals(1412,liveData.getOrAwaitValue().id)
    }

    @Test
    fun loadDetailListTvShow() {
        val liveData = detailTvShowViewModel.loadDetailListTvShow(1412).observeForever {
            Assert.assertNotNull(it)
        }
    }
}