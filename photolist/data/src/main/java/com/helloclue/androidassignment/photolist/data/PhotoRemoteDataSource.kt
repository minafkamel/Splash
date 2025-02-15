package com.helloclue.androidassignment.photolist.data

import retrofit2.Response
import retrofit2.http.GET

interface PhotoRemoteDataSource {

    // Load the Auth token from https://unsplash.com/documentation
    @GET(ENDPOINT)
    fun getPhotos(): Response<Photo>


    companion object {
        private const val ENDPOINT = "photos/random/"
    }
}
