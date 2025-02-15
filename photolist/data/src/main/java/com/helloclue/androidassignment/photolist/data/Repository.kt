package com.helloclue.androidassignment.photolist.data

import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getRandomPhoto(): Photo {
        val response = remoteDataSource.getPhoto()

        if (response.body() != null) {
            val photo = response.body() as Photo
            localDataSource.add(photo)
            return photo
        } else {
            throw Exception()
        }
    }

    suspend fun getUrls() =
        localDataSource.getAllPhotos().map { photos -> photos.map { it.urlRegular } }
}

