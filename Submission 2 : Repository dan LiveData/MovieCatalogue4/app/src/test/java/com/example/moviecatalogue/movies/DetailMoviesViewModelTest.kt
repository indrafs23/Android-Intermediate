package com.example.moviecatalogue.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.Unit.FakeDataDummy
import com.example.moviecatalogue.Unit.getOrAwaitValue
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.ContentRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class DetailMoviesViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()
    private lateinit var detailMoviesViewModel: DetailMoviesViewModel
    private val contentRepository = mock(ContentRepository::class.java)
    private var movieResult: MutableLiveData<DataMoviesEntity> = MutableLiveData()
    private val fakeDataDummy = FakeDataDummy()

    @Before
    fun before() {
        detailMoviesViewModel = DetailMoviesViewModel(contentRepository)
        detailMoviesViewModel.id = 475557

        movieResult = fakeDataDummy.returnDataDummyMovie()
        Mockito.`when`(contentRepository.getMovie(475557)).thenReturn(movieResult)
    }

    @Test
    fun getDataMoviesEntity() {
        val data = detailMoviesViewModel.getMovie()
        verify(contentRepository).getMovie(475557)

        val movieid = data.getOrAwaitValue().id
        Assert.assertNotNull(movieid)
        Assert.assertEquals(475557, data.getOrAwaitValue().id)
    }

    @Test
    fun loadDetailDataMovies() {
        val data = fakeDataDummy.returnDataDummyListMovie()
        val movie = MutableLiveData<List<DataMoviesEntity>>()
        movie.setValue(arrayListOf(data))

        Mockito.`when`(contentRepository.getListMovie()).thenReturn(movie)

        val observer = mock(Observer::class.java)

        detailMoviesViewModel.getMovie().observeForever(observer as Observer<in DataMoviesEntity>)

        Mockito.verify(observer).onChanged(data)
    }
}