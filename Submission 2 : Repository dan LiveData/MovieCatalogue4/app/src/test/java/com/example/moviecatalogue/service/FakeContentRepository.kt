package com.example.moviecatalogue.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity

class FakeContentRepository : ContentData {
    private var remoteRepository = RemoteRepository()

    override fun getTv(id: Int): LiveData<DataTvShowEntity> {
        val data = remoteRepository.getListTv(
            BuildConfig.API_KEY,
            TmdbApi.LANGUAGE
        )

        val result = Transformations.map(data) {
            for (tv in it.results) {
                if (id == tv.id) {
                    return@map tv
                }
            }
            return@map DataTvShowEntity()
        }
        return result
    }

    override fun getMovie(id: Int): LiveData<DataMoviesEntity> {
        val data = remoteRepository.getListMovie(
            BuildConfig.API_KEY,
            TmdbApi.LANGUAGE
        )

        val result = Transformations.map(data) {
            for (movie in it.results) {
                if (id == movie.id) {
                    return@map movie
                }
            }
            return@map DataMoviesEntity()
        }
        return result
    }

    constructor(remoteRepository: RemoteRepository) : this() {
        this.remoteRepository = remoteRepository
    }

    constructor()

    fun getInstance(remoteRepository: RemoteRepository): ContentRepository? {
        var instance: ContentRepository? = null

        if (instance == null) {
            synchronized(ContentRepository::class.java) {
                instance = ContentRepository(remoteRepository)
            }
        }
        return instance
    }

    override fun getListMovie(): LiveData<List<DataMoviesEntity>> {
        val data = remoteRepository.getListMovie(
            BuildConfig.API_KEY,
            TmdbApi.LANGUAGE
        )
        return Transformations.map(data) { it.results }
    }

    override fun getListTv(): LiveData<List<DataTvShowEntity>> {
        val data = remoteRepository.getListTv(
            BuildConfig.API_KEY,
            TmdbApi.LANGUAGE
        )
        return Transformations.map(data) { it.results }
    }
}