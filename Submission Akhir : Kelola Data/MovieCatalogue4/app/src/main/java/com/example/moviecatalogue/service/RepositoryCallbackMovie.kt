package com.example.moviecatalogue.service

interface RepositoryCallbackMovie<ListDataMoviesEntity> {
    fun onDataLoaded(data: ListDataMoviesEntity)
    fun onDataError(errorMessage: String)
}