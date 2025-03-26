package com.hello.splash.photolist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Photo::class], version = 1)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun dataSource(): LocalDataSource
}