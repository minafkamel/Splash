package com.helloclue.androidassignment.photolist.data

import retrofit2.Response
import retrofit2.http.GET

interface RemoteDataSource {

    // Load the Auth token from https://unsplash.com/documentation
    @GET(ENDPOINT)
    suspend fun getPhotos(): Response<Photo>


    companion object {
        private const val ENDPOINT = "photos/random/"
    }
}
