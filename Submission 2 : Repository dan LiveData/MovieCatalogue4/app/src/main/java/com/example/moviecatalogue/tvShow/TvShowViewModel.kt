package com.example.moviecatalogue.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.service.ContentRepository
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity

class TvShowViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    fun getTvShow(): LiveData<List<DataTvShowEntity>> {
        return contentRepository.getListTv()
    }
}