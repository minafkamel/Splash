package com.helloclue.androidassignment.photolist.data

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("urls") var urls: Urls,
)


data class Urls(
    @SerializedName("regular") var regular: String,
)
