package com.hello.splash.photolist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDataSource {

    @Insert
    suspend fun add(photo: Photo)

    @Query("SELECT * FROM photos")
    fun getAllPhotos(): Flow<List<Photo>>

    @Query("SELECT * FROM photos WHERE id =:id")
    fun getPhotoById(id: String): Flow<Photo>
}
