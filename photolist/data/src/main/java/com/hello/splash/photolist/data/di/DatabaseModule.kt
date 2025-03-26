package com.hello.splash.photolist.data.di

import android.content.Context
import androidx.room.Room
import com.hello.splash.photolist.data.LocalDataSource
import com.hello.splash.photolist.data.Photo
import com.hello.splash.photolist.data.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePhotoDatabase(context: Context): PhotoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PhotoDatabase::class.java,
            "photo_database"
        ).build()
    }

    @Provides
    fun providePhotoLocalDataSource(photoDatabase: PhotoDatabase): LocalDataSource {
        return photoDatabase.dataSource()
    }
}