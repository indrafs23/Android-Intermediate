package com.example.moviecatalogue.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.ContentRepository

class DetailMoviesViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    var id: Int = 0

    fun getMovie(): LiveData<DataMoviesEntity> {
        return contentRepository.getMovie(id)
    }
}
