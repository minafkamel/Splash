package com.helloclue.androidassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClueApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}