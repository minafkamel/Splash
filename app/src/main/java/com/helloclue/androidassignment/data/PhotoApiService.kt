package com.helloclue.androidassignment.data

import com.helloclue.androidassignment.model.Photo
import retrofit2.Response
import retrofit2.http.GET

interface PhotoApiService {

    // Load the Auth token from https://unsplash.com/documentation
    @GET("photos/random/")
    fun getPhotos(): Response<Photo>
}
