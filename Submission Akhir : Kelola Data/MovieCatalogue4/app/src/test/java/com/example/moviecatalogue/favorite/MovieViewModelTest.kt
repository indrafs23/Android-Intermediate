package com.example.moviecatalogue.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.DatabaseMoviesRepository
import com.example.moviecatalogue2.database.MoviesDao
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieViewModelTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()
    private val movieViewModel= mock(MovieViewModel::class.java)
    private lateinit var databaseMoviesRepository: DatabaseMoviesRepository
    private var movieResult: MutableLiveData<PagedList<DataMoviesEntity>> = MutableLiveData()

    @Before
    fun setUp(){
        val moviesDao = Mockito.mock(MoviesDao::class.java)
        databaseMoviesRepository = DatabaseMoviesRepository(moviesDao)
        Mockito.`when`(moviesDao.getAllMovies()).thenReturn(getMockedGetAllMovies())
    }

    @Test
    fun getAllMovie() {
        val result = databaseMoviesRepository.getAllMovies()
        assert(result is DataSource.Factory<Int, DataMoviesEntity>)
    }

    @Test
    fun getMovie(){
        val pagedList: PagedList<*> = Mockito.mock(PagedList::class.java)
        movieResult.value = pagedList as PagedList<DataMoviesEntity>

        Mockito.`when`(movieViewModel.getAllMovie()).thenReturn(movieResult)

        val observer = Mockito.mock(Observer::class.java)

        movieViewModel.getAllMovie().observeForever(observer as Observer<in PagedList<DataMoviesEntity>>)

        Mockito.verify(observer).onChanged(pagedList)
    }

    private fun getMockedGetAllMovies() = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataMoviesEntity>
}