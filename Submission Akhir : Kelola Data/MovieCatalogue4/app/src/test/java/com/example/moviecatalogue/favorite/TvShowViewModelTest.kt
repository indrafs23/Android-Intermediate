package com.example.moviecatalogue.favorite


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.moviecatalogue.service.DatabaseTvShowRepository
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import com.example.moviecatalogue2.database.TvShowDao
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TvShowViewModelTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()
    private val tvShowViewModel = mock(TvShowViewModel::class.java)
    private var tvResult: MutableLiveData<PagedList<DataTvShowEntity>> = MutableLiveData()
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

    @Test
    fun getTvShow() {
        val pagedList: PagedList<*> = Mockito.mock(PagedList::class.java)
        tvResult.value = pagedList as PagedList<DataTvShowEntity>

        Mockito.`when`(tvShowViewModel.getAllTvShow()).thenReturn(tvResult)

        val observer = Mockito.mock(Observer::class.java)

        tvShowViewModel.getAllTvShow().observeForever(observer as Observer<in PagedList<DataTvShowEntity>>)

        Mockito.verify(observer).onChanged(pagedList)
    }

    private fun getMockedGetAllTvShow() = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataTvShowEntity>
}