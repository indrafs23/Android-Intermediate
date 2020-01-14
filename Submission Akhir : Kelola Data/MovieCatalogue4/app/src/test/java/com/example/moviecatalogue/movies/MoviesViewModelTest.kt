package com.example.moviecatalogue.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.Unit.FakeDataDummy
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.ContentRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*


class MoviesViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var moviesViewModel: MoviesViewModel
    private val contentRepository = mock(ContentRepository::class.java)
    private var movieResult: MutableLiveData<List<DataMoviesEntity>> = MutableLiveData()
    private val fakeDataDummy = FakeDataDummy()
    private var getListMovie = MutableLiveData<List<DataMoviesEntity>>()

    @Before
    fun before() {
        moviesViewModel = MoviesViewModel(contentRepository)
        val dataMoviesEntity = fakeDataDummy.returnDataDummyListMovie()
        movieResult.postValue(arrayListOf(dataMoviesEntity))
        Mockito.`when`(contentRepository.getListMovie()).thenReturn(movieResult)
    }

    @Test
    fun loadListMovie() {
        getListMovie = moviesViewModel.getMovie() as MutableLiveData<List<DataMoviesEntity>>
        verify(contentRepository).getListMovie()
        Assert.assertNotNull(getListMovie)
        Assert.assertEquals(1, getListMovie.value?.size)
    }

    @Test
    fun loadLiveDataMovie() {
        val data = fakeDataDummy.returnDataDummyListMovie()
        val movie = MutableLiveData<List<DataMoviesEntity>>()
        movie.setValue(arrayListOf(data))

        `when`(contentRepository.getListMovie()).thenReturn(movie)

        val observer = mock(Observer::class.java)

        moviesViewModel.getMovie().observeForever(observer as Observer<in List<DataMoviesEntity>>)

        verify(observer).onChanged(arrayListOf(data))
    }
}