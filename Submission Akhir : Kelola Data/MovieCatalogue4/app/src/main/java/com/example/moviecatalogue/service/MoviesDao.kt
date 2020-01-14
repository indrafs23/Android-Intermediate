package com.example.moviecatalogue2.database

import android.database.Cursor
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity


@Dao
interface MoviesDao {
    @Query("SELECT * from movies ORDER BY id ASC")
    fun getAllMovies(): DataSource.Factory<Int, DataMoviesEntity>

    @Insert(onConflict = REPLACE)
    fun insertMovies(dataMovies: DataMoviesEntity)

    @Delete
    fun deleteMovies(dataMovies: DataMoviesEntity)

    @Update
    fun updateMovies(dataMovies: DataMoviesEntity)

    @Query("SELECT * FROM movies WHERE id LIKE :search")
    fun searchId(search: Int): DataMoviesEntity

    @Query("SELECT * from movies")
    fun getAllMoviesCursor(): Cursor
}