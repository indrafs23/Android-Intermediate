package com.example.moviecatalogue.Unit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.movies.dataMovies.ListDataMoviesEntity
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import com.example.moviecatalogue.tvShow.dataTvShow.ListDataTvShowEntity

class FakeDataDummy {

    fun returnDataDummyMovie(): MutableLiveData<DataMoviesEntity> {
        val movieResult: MutableLiveData<DataMoviesEntity> = MutableLiveData()
        var dataMoviesEntity = DataMoviesEntity()

        dataMoviesEntity.popularity = "619.889"
        dataMoviesEntity.vote_average = "5228"
        dataMoviesEntity.video = "false"
        dataMoviesEntity.poster_path = "udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"
        dataMoviesEntity.id = 475557
        dataMoviesEntity.adult = "false"
        dataMoviesEntity.backdrop_path = "n6bUvigpRFqSwmPp1m2YADdbRBc.jpg"
        dataMoviesEntity.original_languange = "en"
        dataMoviesEntity.original_title = "Joker"
        dataMoviesEntity.genre_ids = arrayListOf(80, 18, 53)
        dataMoviesEntity.title = "Joker"
        dataMoviesEntity.vote_average = "8.5"
        dataMoviesEntity.overview =
            "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."
        dataMoviesEntity.release_date = "2019-10-04"

        movieResult.postValue(dataMoviesEntity)
        return movieResult
    }

    fun returnDataDummyTvShow(): MutableLiveData<DataTvShowEntity> {
        val tvResult: MutableLiveData<DataTvShowEntity> = MutableLiveData()
        var dataTvShowEntity = DataTvShowEntity()

        dataTvShowEntity.original_name = "Arrow"
        dataTvShowEntity.genre_ids = arrayListOf(80, 18, 9648, 10759)
        dataTvShowEntity.name = "Arrow"
        dataTvShowEntity.popularity = "249.875"
        dataTvShowEntity.origin_country = arrayListOf("US")
        dataTvShowEntity.vote_count = "2795"
        dataTvShowEntity.first_air_date = "2012-10-10"
        dataTvShowEntity.backdrop_path = "dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg"
        dataTvShowEntity.original_language = "en"
        dataTvShowEntity.id = 1412
        dataTvShowEntity.vote_average = "5.8"
        dataTvShowEntity.overview =
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
        dataTvShowEntity.poster_path = "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"

        tvResult.postValue(dataTvShowEntity)
        return tvResult
    }

    fun returnDataDummyListMovie(): DataMoviesEntity {
        var dataMoviesEntity = DataMoviesEntity()

        dataMoviesEntity.popularity = "619.889"
        dataMoviesEntity.vote_average = "5228"
        dataMoviesEntity.video = "false"
        dataMoviesEntity.poster_path = "udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"
        dataMoviesEntity.id = 475557
        dataMoviesEntity.adult = "false"
        dataMoviesEntity.backdrop_path = "n6bUvigpRFqSwmPp1m2YADdbRBc.jpg"
        dataMoviesEntity.original_languange = "en"
        dataMoviesEntity.original_title = "Joker"
        dataMoviesEntity.genre_ids = arrayListOf(80, 18, 53)
        dataMoviesEntity.title = "Joker"
        dataMoviesEntity.vote_average = "8.5"
        dataMoviesEntity.overview =
            "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."
        dataMoviesEntity.release_date = "2019-10-04"

        return dataMoviesEntity
    }

    fun returnDataDummyListTvShow(): DataTvShowEntity {
        var dataTvShowEntity = DataTvShowEntity()

        dataTvShowEntity.original_name = "Arrow"
        dataTvShowEntity.genre_ids = arrayListOf(80, 18, 9648, 10759)
        dataTvShowEntity.name = "Arrow"
        dataTvShowEntity.popularity = "249.875"
        dataTvShowEntity.origin_country = arrayListOf("US")
        dataTvShowEntity.vote_count = "2795"
        dataTvShowEntity.first_air_date = "2012-10-10"
        dataTvShowEntity.backdrop_path = "dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg"
        dataTvShowEntity.original_language = "en"
        dataTvShowEntity.id = 1412
        dataTvShowEntity.vote_average = "5.8"
        dataTvShowEntity.overview =
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
        dataTvShowEntity.poster_path = "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"

        return dataTvShowEntity
    }

    fun returnDataDummyListDataMovie(): LiveData<ListDataMoviesEntity> {
        var listDataMoviesEntity = MutableLiveData<ListDataMoviesEntity>()
        var dataMoviesEntity = DataMoviesEntity()

        dataMoviesEntity.popularity = "619.889"
        dataMoviesEntity.vote_average = "5228"
        dataMoviesEntity.video = "false"
        dataMoviesEntity.poster_path = "udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"
        dataMoviesEntity.id = 475557
        dataMoviesEntity.adult = "false"
        dataMoviesEntity.backdrop_path = "n6bUvigpRFqSwmPp1m2YADdbRBc.jpg"
        dataMoviesEntity.original_languange = "en"
        dataMoviesEntity.original_title = "Joker"
        dataMoviesEntity.genre_ids = arrayListOf(80, 18, 53)
        dataMoviesEntity.title = "Joker"
        dataMoviesEntity.vote_average = "8.5"
        dataMoviesEntity.overview =
            "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."
        dataMoviesEntity.release_date = "2019-10-04"

        listDataMoviesEntity.value?.results = arrayListOf(dataMoviesEntity)
        return listDataMoviesEntity
    }

    fun returnDataDummyListDataTvShow(): LiveData<ListDataTvShowEntity> {
        val listDataTvShowEntity = MutableLiveData<ListDataTvShowEntity>()
        var dataTvShowEntity = DataTvShowEntity()

        dataTvShowEntity.original_name = "Arrow"
        dataTvShowEntity.genre_ids = arrayListOf(80, 18, 9648, 10759)
        dataTvShowEntity.name = "Arrow"
        dataTvShowEntity.popularity = "249.875"
        dataTvShowEntity.origin_country = arrayListOf("US")
        dataTvShowEntity.vote_count = "2795"
        dataTvShowEntity.first_air_date = "2012-10-10"
        dataTvShowEntity.backdrop_path = "dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg"
        dataTvShowEntity.original_language = "en"
        dataTvShowEntity.id = 1412
        dataTvShowEntity.vote_average = "5.8"
        dataTvShowEntity.overview =
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
        dataTvShowEntity.poster_path = "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"

        listDataTvShowEntity.postValue(ListDataTvShowEntity(1, 1, 1, arrayListOf(dataTvShowEntity)))
        return listDataTvShowEntity
    }
}