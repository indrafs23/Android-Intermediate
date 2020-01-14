package com.example.moviecatalogue.service

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.movies.DetailMoviesViewModel
import com.example.moviecatalogue.movies.MoviesViewModel
import com.example.moviecatalogue.tvShow.DetailTvShowViewModel
import com.example.moviecatalogue.tvShow.TvShowViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory {

    @Volatile
    private lateinit var instance: ViewModelFactory

    private lateinit var contentRepository: ContentRepository

    private constructor(contentRepository: ContentRepository) : this() {
        this.contentRepository = contentRepository
    }

    constructor()

    fun getInstance(application: Application): ViewModelFactory {
        synchronized(ViewModelFactory::class.java) {
            val injection = Injection().provideRepository(application)
            if (injection != null) {
                instance = ViewModelFactory(injection)
            }
        }
        return instance
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(contentRepository) as T
        } else if (modelClass.isAssignableFrom(TvShowViewModel::class.java)) {
            return TvShowViewModel(contentRepository) as T
        } else if (modelClass.isAssignableFrom(DetailMoviesViewModel::class.java)) {
            return DetailMoviesViewModel(contentRepository) as T
        } else if (modelClass.isAssignableFrom(DetailTvShowViewModel::class.java)) {
            return DetailTvShowViewModel(contentRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}