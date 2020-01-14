package com.example.moviecatalogue.service

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.favorite.MovieViewModel
import com.example.moviecatalogue.favorite.TvShowViewModel


class ViewModelDatabaseFactory : ViewModelProvider.NewInstanceFactory {

    @Volatile
    private lateinit var instance: ViewModelDatabaseFactory
    private lateinit var mApplication: Application

    constructor(application: Application) {
        this.mApplication = application
    }

    constructor()

    fun getInstance(application: Application): ViewModelDatabaseFactory {
        synchronized(ViewModelDatabaseFactory::class.java) {
            instance = ViewModelDatabaseFactory(application)
        }
        return instance
    }

    @NonNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(TvShowViewModel::class.java)) {
            return TvShowViewModel(mApplication) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}