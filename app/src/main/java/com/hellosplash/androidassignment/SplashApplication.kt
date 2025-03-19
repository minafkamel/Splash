package com.hellosplash.androidassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SplashApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}