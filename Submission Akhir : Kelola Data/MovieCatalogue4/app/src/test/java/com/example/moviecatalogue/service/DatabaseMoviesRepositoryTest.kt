package com.example.moviecatalogue.service

import androidx.paging.DataSource
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue2.database.MoviesDao
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DatabaseMoviesRepositoryTest {
    private lateinit var databaseMoviesRepository: DatabaseMoviesRepository

    @Before
    fun setUp(){
        val moviesDao = mock(MoviesDao::class.java)
        databaseMoviesRepository = DatabaseMoviesRepository(moviesDao)
        `when`(moviesDao.getAllMovies()).thenReturn(getMockedGetAllMovies())
    }

    @Test
    fun getAllMovies() {
        val result = databaseMoviesRepository.getAllMovies()
        assert(result is DataSource.Factory<Int, DataMoviesEntity>)
    }

    private fun getMockedGetAllMovies() = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataMoviesEntity>
}