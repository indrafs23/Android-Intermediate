package com.example.moviecatalogue2.database

import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity

@Dao
interface TvShowDao {
    @Query("SELECT * from tvShow ORDER BY id ASC")
    fun getAllTvShow(): DataSource.Factory<Int, DataTvShowEntity>

    @Insert(onConflict = REPLACE)
    fun insertTvShow(dataTvShow: DataTvShowEntity)

    @Delete
    fun deleteTvShow(dataTvShow: DataTvShowEntity)

    @Update
    fun updateTvShow(dataTvShow: DataTvShowEntity)

    @Query("SELECT * FROM tvShow WHERE id LIKE :search")
    fun searchId(search: Int): DataTvShowEntity
}