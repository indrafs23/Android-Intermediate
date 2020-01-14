package com.example.moviecatalogue.movies.dataMovies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListDataMoviesEntity(
    var page: Int = 0,
    var total_results: Int = 0,
    var total_pages: Int = 0,
    var results: List<DataMoviesEntity> = emptyList()
) : Parcelable

