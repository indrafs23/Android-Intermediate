package com.example.moviecatalogue.service

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity

interface ContentData {
    fun getListMovie(): LiveData<List<DataMoviesEntity>>
    fun getListTv(): LiveData<List<DataTvShowEntity>>
    fun getTv(id: Int): LiveData<DataTvShowEntity>
    fun getMovie(id: Int): LiveData<DataMoviesEntity>
}