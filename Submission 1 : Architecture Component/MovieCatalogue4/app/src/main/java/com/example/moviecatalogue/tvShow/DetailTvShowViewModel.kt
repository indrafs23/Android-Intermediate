package com.example.moviecatalogue.tvShow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.service.TmdbApi
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import com.example.moviecatalogue.tvShow.dataTvShow.ListDataTvShowEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailTvShowViewModel : ViewModel() {
    val listTvShow = MutableLiveData<DataTvShowEntity>()

    fun loadDetailListTvShow(id: Int): LiveData<DataTvShowEntity> {
        Log.d("Masuk", "<>")
        val retrofit = Retrofit.Builder()
            .baseUrl(TmdbApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieApi = retrofit.create(TmdbApi::class.java)
        val call = movieApi.getTvShow(BuildConfig.API_KEY, TmdbApi.LANGUAGE)

        call.enqueue(object : Callback<ListDataTvShowEntity> {
            override fun onFailure(call: Call<ListDataTvShowEntity>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ListDataTvShowEntity>,
                response: Response<ListDataTvShowEntity>
            ) {
                if (response.code() == 200) {
                    val filmResponse = response.body() as ListDataTvShowEntity
                    for (tv in filmResponse.results) {
                        if (id == tv.id) {
                            listTvShow.value = tv
                            break
                        }
                    }
                }
            }
        })
        return listTvShow
    }
}