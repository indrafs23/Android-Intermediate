package com.example.moviecatalogue.service

import androidx.paging.DataSource
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue2.database.MoviesDao

class DatabaseMoviesRepository(private val moviesDao: MoviesDao?) {

    fun getAllMovies(): DataSource.Factory<Int, DataMoviesEntity> {
        return moviesDao!!.getAllMovies()
    }

    fun insert(dataMoviesEntity: DataMoviesEntity) {
        moviesDao!!.insertMovies(dataMoviesEntity)
    }

    fun delete(dataMoviesEntity: DataMoviesEntity) {
        moviesDao!!.deleteMovies(dataMoviesEntity)
    }

    fun update(dataMoviesEntity: DataMoviesEntity) {
        moviesDao!!.updateMovies(dataMoviesEntity)
    }

    fun searchId(id: Int) : DataMoviesEntity {
        return moviesDao!!.searchId(id)
    }
}