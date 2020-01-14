package com.example.moviecatalogue.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.movies.dataMovies.ListDataMoviesEntity
import com.example.moviecatalogue.service.TmdbApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoviesViewModel : ViewModel() {
    val listMovies = MutableLiveData<List<DataMoviesEntity>>()

    fun loadListMovies(): LiveData<List<DataMoviesEntity>> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi = retrofit.create(TmdbApi::class.java)
        val call = movieApi.getMovies(BuildConfig.API_KEY, TmdbApi.LANGUAGE)
        call.enqueue(object : Callback<ListDataMoviesEntity> {
            override fun onFailure(call: Call<ListDataMoviesEntity>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ListDataMoviesEntity>,
                response: Response<ListDataMoviesEntity>
            ) {
                if (response.code() == 200) {
                    val filmResponse = response.body() as ListDataMoviesEntity
                    Log.d("Masuk",filmResponse.toString())
                    listMovies.value = filmResponse.results
                }
            }
        })
        return listMovies
    }
}