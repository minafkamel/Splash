package com.helloclue.androidassignment.photolist.data

import javax.inject.Singleton

@Singleton
class Repository @Singleton constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getRandomPhoto() = remoteDataSource.getPhotos()
}
