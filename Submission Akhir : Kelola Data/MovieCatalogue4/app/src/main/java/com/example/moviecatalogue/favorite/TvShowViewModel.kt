package com.example.moviecatalogue.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.service.DatabaseTvShowRepository
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import com.example.moviecatalogue2.database.TvShowDatabase


class TvShowViewModel(application: Application): ViewModel() {
    private val tvShowDao by lazy {
        return@lazy TvShowDatabase.getInstance(application)?.TvShowDao()
    }
    private val databaseTvShowRepository = DatabaseTvShowRepository(tvShowDao)

    fun insert(dataTvShowEntity: DataTvShowEntity) {
        databaseTvShowRepository.insert(dataTvShowEntity)
    }

    fun update(dataTvShowEntity: DataTvShowEntity) {
        databaseTvShowRepository.update(dataTvShowEntity)
    }

    fun delete(dataTvShowEntity: DataTvShowEntity) {
        databaseTvShowRepository.delete(dataTvShowEntity)
    }

    fun searchId(id:Int): DataTvShowEntity{
        return databaseTvShowRepository.searchId(id)
    }

    fun getAllTvShow(): LiveData<PagedList<DataTvShowEntity>> {
        return LivePagedListBuilder(databaseTvShowRepository.getAllTvShow(), 20).build()
    }
}