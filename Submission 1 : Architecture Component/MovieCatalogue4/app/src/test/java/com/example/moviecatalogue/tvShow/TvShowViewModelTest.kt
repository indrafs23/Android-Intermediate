package com.example.moviecatalogue.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowViewModelTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var tvShowViewModel : TvShowViewModel

    @Before
    fun before(){
        tvShowViewModel = TvShowViewModel()
    }

    @Test
    fun getListTvShow() {
        val liveData = tvShowViewModel.loadListTvShow().observeForever {
            Assert.assertNotNull(it)
        }
    }

    @Test
    fun loadListTvShow() {
        val liveData = tvShowViewModel.loadListTvShow().observeForever {
            Assert.assertEquals(1000, it.size)
        }
    }
}