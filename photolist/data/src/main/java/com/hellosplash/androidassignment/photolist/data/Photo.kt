package com.hellosplash.androidassignment.photolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("urlRegular") val urlRegular: String?,  // Flattened url
    @SerializedName("locationName") val locationName: String?, // Flattened location
    @SerializedName("likes") val likes: String?,
    @SerializedName("description") val description: String?
)