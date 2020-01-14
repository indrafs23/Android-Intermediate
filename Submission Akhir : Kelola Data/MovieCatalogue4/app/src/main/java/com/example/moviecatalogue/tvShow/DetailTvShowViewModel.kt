package com.example.moviecatalogue.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.service.ContentRepository
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity

class DetailTvShowViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    var id: Int = 0

    fun getTvShow(): LiveData<DataTvShowEntity> {
        return contentRepository.getTv(id)
    }
}