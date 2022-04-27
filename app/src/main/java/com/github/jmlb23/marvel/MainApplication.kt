package com.github.jmlb23.marvel

import android.app.Application
import com.github.jmlb23.mvvm.diAndroid
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(diAndroid)
        }
    }
}