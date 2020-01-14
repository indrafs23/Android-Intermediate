package com.example.moviecatalogue.tvShow.dataTvShow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataTvShowEntity(
    var original_name: String? = "",
    var genre_ids: List<Int>? = emptyList(),
    var name: String? = "",
    var popularity: String? = "",
    var origin_country: List<String>? = emptyList(),
    var vote_count: String? = "",
    var first_air_date: String? = "",
    var backdrop_path: String? = "",
    var original_language: String? = "",
    var id: Int = -1,
    var vote_average: String? = "",
    var overview: String? = "",
    var poster_path: String? = ""
) : Parcelable