package com.example.moviecatalogue.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.Unit.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailMoviesViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()
    private lateinit var detailMoviesViewModel : DetailMoviesViewModel

    @Before
    fun before(){
        detailMoviesViewModel = DetailMoviesViewModel()
    }

    @Test
    fun getDataMoviesEntity() {
        val data = detailMoviesViewModel.loadDetailDataMovies(475557)
        Assert.assertEquals(475557, data.getOrAwaitValue().id)
    }

    @Test
    fun loadDetailDataMovies() {
        val liveData = detailMoviesViewModel.loadDetailDataMovies(475557).observeForever {
            Assert.assertNotNull(it)
        }
    }
}