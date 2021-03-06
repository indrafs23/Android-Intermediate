package com.example.moviecatalogue2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import com.example.moviecatalogue.utils.Converter

@Database(entities = arrayOf(DataTvShowEntity::class), version = 1, exportSchema = false)
@TypeConverters(Converter::class)

abstract class TvShowDatabase : RoomDatabase() {

    abstract fun TvShowDao(): TvShowDao

    companion object {
        private var INSTANCE: TvShowDatabase? = null

        fun getInstance(context: Context): TvShowDatabase? {
            if (INSTANCE == null) {
                synchronized(TvShowDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TvShowDatabase::class.java,
                        "tvshowdata.db"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}