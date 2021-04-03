package com.example.ankitjadavpracticaltest

import android.app.Application
import com.example.ankitjadavpracticaltest.data.di.networkModule
import com.example.ankitjadavpracticaltest.data.di.repoModule
import com.example.ankitjadavpracticaltest.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppSingleton : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppSingleton)
            modules(listOf(viewModelModule, networkModule, repoModule))
        }
    }
}