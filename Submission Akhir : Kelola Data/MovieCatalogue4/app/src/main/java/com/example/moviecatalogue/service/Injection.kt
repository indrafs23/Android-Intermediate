package com.example.moviecatalogue.service

import android.app.Application

class Injection {
    fun provideRepository(application: Application): ContentRepository? {
        val remoteRepository = RemoteRepository()
        return ContentRepository().getInstance(remoteRepository)
    }
}