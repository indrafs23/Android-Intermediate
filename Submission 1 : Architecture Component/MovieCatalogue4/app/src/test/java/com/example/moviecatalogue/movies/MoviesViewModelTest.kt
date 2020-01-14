package com.example.moviecatalogue.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @get:Rule val testRule = InstantTaskExecutorRule()

    private lateinit var moviesViewModel : MoviesViewModel

    @Before
    fun before(){
        moviesViewModel = MoviesViewModel()
    }

    @Test
    fun loadListMovie() {
        val liveData = moviesViewModel.loadListMovies().observeForever {
            Assert.assertEquals(1000, it.size)
        }
    }

    @Test
    fun getListMovie() {
        val liveData = moviesViewModel.loadListMovies().observeForever {
            Assert.assertNotNull(it)
        }
    }

}