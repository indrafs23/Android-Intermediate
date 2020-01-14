package com.example.moviecatalogue.service

interface RepositoryCallbackTvShow<ListDataTvShowEntity> {
    fun onDataLoaded(data: ListDataTvShowEntity)
    fun onDataError(errorMessage: String)
}