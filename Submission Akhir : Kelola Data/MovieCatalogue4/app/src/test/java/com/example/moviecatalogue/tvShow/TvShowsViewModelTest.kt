package com.example.moviecatalogue.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.Unit.FakeDataDummy
import com.example.moviecatalogue.service.ContentRepository
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class TvShowsViewModelTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()
    private val contentRepository = mock(ContentRepository::class.java)
    private lateinit var tvShowsViewModel: TvShowsViewModel
    private var tvResult: MutableLiveData<List<DataTvShowEntity>> = MutableLiveData()
    private val fakeDataDummy = FakeDataDummy()

    @Before
    fun before() {
        tvShowsViewModel = TvShowsViewModel(contentRepository)
        val dataTvShowEntity = fakeDataDummy.returnDataDummyListTvShow()
        tvResult.postValue(arrayListOf(dataTvShowEntity))
        Mockito.`when`(contentRepository.getListTv()).thenReturn(tvResult)
    }

    @Test
    fun getListTvShow() {
        val getListTv = tvShowsViewModel.getTvShow()
        verify(contentRepository).getListTv()
        Assert.assertNotNull(getListTv)
        Assert.assertEquals(1, getListTv.value?.size)
    }

    @Test
    fun loadLiveDataTvShow() {
        val data = fakeDataDummy.returnDataDummyListTvShow()
        val tvShow = MutableLiveData<List<DataTvShowEntity>>()
        tvShow.setValue(arrayListOf(data))

        `when`(contentRepository.getListTv()).thenReturn(tvShow)

        val observer = mock(Observer::class.java)

        tvShowsViewModel.getTvShow().observeForever(observer as Observer<in List<DataTvShowEntity>>)

        verify(observer).onChanged(arrayListOf(data))
    }
}