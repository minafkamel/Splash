package com.hello.splash.photolist.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getRandomPhoto() {
        val response = remoteDataSource.getPhoto()

        if (response.body() != null) {
            val photo = response.body() as Photo
            localDataSource.add(photo)
        } else {
            throw Exception()
        }
    }

    fun getPhotoById(id: String) = localDataSource.getPhotoById(id)

    val photosFlow = localDataSource.getAllPhotos()
}

