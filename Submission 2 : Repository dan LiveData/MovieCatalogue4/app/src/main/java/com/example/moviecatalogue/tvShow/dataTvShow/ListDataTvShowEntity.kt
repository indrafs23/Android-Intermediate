package com.example.moviecatalogue.tvShow.dataTvShow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListDataTvShowEntity(
    var page: Int = 0,
    var total_results: Int = 0,
    var total_pages: Int = 0,
    var results: List<DataTvShowEntity> = emptyList()
) : Parcelable