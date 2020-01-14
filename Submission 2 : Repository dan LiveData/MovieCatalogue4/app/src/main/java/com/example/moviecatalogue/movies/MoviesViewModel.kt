package com.example.moviecatalogue.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.ContentRepository


class MoviesViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    fun getMovie(): LiveData<List<DataMoviesEntity>> {
        return contentRepository.getListMovie()
    }
}