package com.example.moviecatalogue.service

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.Utils.EspressoIdlingResource
import com.example.moviecatalogue.movies.dataMovies.ListDataMoviesEntity
import com.example.moviecatalogue.tvShow.dataTvShow.ListDataTvShowEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class RemoteRepository() {

    @SuppressLint("CheckResult")
    fun getListMovie(apiKey: String, language: String): LiveData<ListDataMoviesEntity> {
        var data = MutableLiveData<ListDataMoviesEntity>()
        EspressoIdlingResource.increment()
        MyRetrofit.createService(TmdbApi::class.java)
            .getMovies(apiKey, language)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    data.postValue(it)
                    if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                },
                onError = { it.printStackTrace() },
                onComplete = { print("Done") }
            )
        return data
    }

    @SuppressLint("CheckResult")
    fun getListTv(apiKey: String, language: String): LiveData<ListDataTvShowEntity> {
        var data = MutableLiveData<ListDataTvShowEntity>()
        EspressoIdlingResource.increment()
        MyRetrofit.createService(TmdbApi::class.java)
            .getTvShow(apiKey, language)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    data.postValue(it)
                    if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                },
                onError = {
                    it.printStackTrace()
                },
                onComplete = { print("Done") }
            )

        return data
    }
}