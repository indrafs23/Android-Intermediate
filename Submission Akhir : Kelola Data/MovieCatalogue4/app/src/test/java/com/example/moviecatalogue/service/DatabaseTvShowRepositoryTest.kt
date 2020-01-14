package com.example.moviecatalogue.service

import androidx.paging.DataSource
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import com.example.moviecatalogue2.database.TvShowDao
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class DatabaseTvShowRepositoryTest {
    private lateinit var databaseTvShowRepository : DatabaseTvShowRepository

    @Before
    fun setUp(){
        val tvShowDao = Mockito.mock(TvShowDao::class.java)
        databaseTvShowRepository = DatabaseTvShowRepository(tvShowDao)
        Mockito.`when`(tvShowDao.getAllTvShow()).thenReturn(getMockedGetAllTvShow())
    }

    @Test
    fun getAllTvShow() {
        val result = databaseTvShowRepository.getAllTvShow()
        assert(result is DataSource.Factory<Int, DataTvShowEntity>)
    }

    private fun getMockedGetAllTvShow() = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataTvShowEntity>
}