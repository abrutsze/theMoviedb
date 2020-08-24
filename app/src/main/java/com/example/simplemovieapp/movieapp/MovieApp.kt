package com.example.simplemovieapp.movieapp

import android.app.Application
import com.example.data.di.apiModule
import com.example.data.di.repositoryModule
import com.example.domain.di.interactorModule
import com.example.simplemovieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieApp)
            modules(modules)
        }
    }

    val modules = listOf(
        apiModule,
        repositoryModule,
        interactorModule,
        viewModelModule
    )
}