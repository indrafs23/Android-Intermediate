package com.example.moviecatalogue.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.Unit.FakeDataDummy
import com.example.moviecatalogue.Unit.getOrAwaitValue
import com.example.moviecatalogue.service.ContentRepository
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class DetailTvShowViewModelTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private val contentRepository = mock(ContentRepository::class.java)
    private var tvResult: MutableLiveData<DataTvShowEntity> = MutableLiveData()
    private val fakeDataDummy = FakeDataDummy()

    @Before
    fun before() {
        detailTvShowViewModel = DetailTvShowViewModel(contentRepository)
        detailTvShowViewModel.id = 1412

        tvResult = fakeDataDummy.returnDataDummyTvShow()
        Mockito.`when`(contentRepository.getTv(1412)).thenReturn(tvResult)
    }

    @Test
    fun getListTvShow() {
        val data = detailTvShowViewModel.getTvShow()
        Mockito.verify(contentRepository).getTv(1412)

        val tvid = data.getOrAwaitValue().id
        Assert.assertNotNull(tvid)
        Assert.assertEquals(1412, data.getOrAwaitValue().id)
    }

    @Test
    fun loadDetailListTvShow() {
        val data = fakeDataDummy.returnDataDummyListTvShow()
        val tvShow = MutableLiveData<List<DataTvShowEntity>>()
        tvShow.setValue(arrayListOf(data))

        Mockito.`when`(contentRepository.getListTv()).thenReturn(tvShow)

        val observer = mock(Observer::class.java)

        detailTvShowViewModel.getTvShow().observeForever(observer as Observer<in DataTvShowEntity>)

        Mockito.verify(observer).onChanged(data)
    }
}