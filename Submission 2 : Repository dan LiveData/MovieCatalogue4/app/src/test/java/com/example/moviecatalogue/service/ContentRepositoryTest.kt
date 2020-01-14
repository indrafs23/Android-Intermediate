package com.example.moviecatalogue.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.Unit.FakeDataDummy
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ContentRepositoryTest {

    @get:Rule
    private val testRule = InstantTaskExecutorRule()
    private val remoteRepository = mock(RemoteRepository::class.java)
    private val fakeContentRepository = FakeContentRepository(remoteRepository)
    private val fakeDataDummy = FakeDataDummy()

    @Test
    fun getTv() {
        val dataTvShowEntity = fakeDataDummy.returnDataDummyListDataTvShow()
        Mockito.`when`(remoteRepository.getListTv(BuildConfig.API_KEY, TmdbApi.LANGUAGE))
            .thenReturn(dataTvShowEntity)
        val listTv = fakeContentRepository.getTv(1412)
        verify(remoteRepository).getListTv(BuildConfig.API_KEY, TmdbApi.LANGUAGE)
        Assert.assertNotNull(listTv)
    }

    @Test
    fun getMovie() {
        val dataMoviesEntity = fakeDataDummy.returnDataDummyListDataMovie()
        Mockito.`when`(remoteRepository.getListMovie(BuildConfig.API_KEY, TmdbApi.LANGUAGE))
            .thenReturn(dataMoviesEntity)
        val listMovie = fakeContentRepository.getMovie(475557)
        verify(remoteRepository).getListMovie(BuildConfig.API_KEY, TmdbApi.LANGUAGE)
        Assert.assertNotNull(listMovie)
    }

    @Test
    fun getListMovie() {
        val dataMoviesEntity = fakeDataDummy.returnDataDummyListDataMovie()
        Mockito.`when`(remoteRepository.getListMovie(BuildConfig.API_KEY, TmdbApi.LANGUAGE))
            .thenReturn(dataMoviesEntity)
        val listMovie = fakeContentRepository.getListMovie()
        verify(remoteRepository).getListMovie(BuildConfig.API_KEY, TmdbApi.LANGUAGE)
        Assert.assertNotNull(listMovie)
        Assert.assertEquals(dataMoviesEntity.value?.results?.size, listMovie.value?.size)
    }

    @Test
    fun getListTv() {
        val dataTvShowEntity = fakeDataDummy.returnDataDummyListDataTvShow()
        Mockito.`when`(remoteRepository.getListTv(BuildConfig.API_KEY, TmdbApi.LANGUAGE))
            .thenReturn(dataTvShowEntity)
        val listTv = fakeContentRepository.getListTv()
        verify(remoteRepository).getListTv(BuildConfig.API_KEY, TmdbApi.LANGUAGE)
        Assert.assertNotNull(listTv)
        Assert.assertEquals(dataTvShowEntity.value?.results?.size, listTv.value?.size)
    }
}