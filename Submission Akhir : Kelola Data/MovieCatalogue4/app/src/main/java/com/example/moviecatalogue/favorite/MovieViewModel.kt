package com.example.moviecatalogue.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.DatabaseMoviesRepository
import com.example.moviecatalogue2.database.MoviesDatabase


class MovieViewModel(application: Application): ViewModel() {
    private val moviesDao by lazy {
        return@lazy MoviesDatabase.getInstance(application)?.MoviesDao()
    }
    private val databaseMovieRepository = DatabaseMoviesRepository(moviesDao)

    fun insert(dataMoviesEntity: DataMoviesEntity) {
        databaseMovieRepository.insert(dataMoviesEntity)
    }

    fun update(dataMoviesEntity: DataMoviesEntity) {
        databaseMovieRepository.update(dataMoviesEntity)
    }

    fun delete(dataMoviesEntity: DataMoviesEntity) {
        databaseMovieRepository.delete(dataMoviesEntity)
    }

    fun searchId(id:Int) : DataMoviesEntity{
        return databaseMovieRepository.searchId(id)
    }

    fun getAllMovie(): LiveData<PagedList<DataMoviesEntity>> {
        return LivePagedListBuilder(databaseMovieRepository.getAllMovies(), 20).build()
    }
}