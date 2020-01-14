package com.example.moviecatalogue.service

import com.example.moviecatalogue.movies.dataMovies.ListDataMoviesEntity
import com.example.moviecatalogue.tvShow.dataTvShow.ListDataTvShowEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
        const val LANGUAGE = "en-US"
    }

    @GET("/3/discover/movie")
    fun getMovies(
        @Query("api_key") key: String,
        @Query("language") key2: String
    ): Observable<ListDataMoviesEntity>

    @GET(value = "/3/discover/tv")
    fun getTvShow(
        @Query("api_key") key: String,
        @Query("language") key2: String
    ): Observable<ListDataTvShowEntity>
}