package com.hello.splash.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {

    @Provides
    fun providesContext(application: Application): Context = application.applicationContext
}