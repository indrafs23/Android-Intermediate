package com.example.moviecatalogue.service

import androidx.paging.DataSource
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import com.example.moviecatalogue2.database.TvShowDao

class DatabaseTvShowRepository(private val tvShowDao: TvShowDao?) {
    fun getAllTvShow(): DataSource.Factory<Int, DataTvShowEntity> {
        return tvShowDao!!.getAllTvShow()
    }

    fun insert(dataTvShowEntity: DataTvShowEntity) {
        tvShowDao!!.insertTvShow(dataTvShowEntity)
    }

    fun delete(dataTvShowEntity: DataTvShowEntity) {
        tvShowDao!!.deleteTvShow(dataTvShowEntity)
    }

    fun update(dataTvShowEntity: DataTvShowEntity) {
        tvShowDao!!.updateTvShow(dataTvShowEntity)
    }

    fun searchId(id: Int) : DataTvShowEntity {
        return tvShowDao!!.searchId(id)
    }
}