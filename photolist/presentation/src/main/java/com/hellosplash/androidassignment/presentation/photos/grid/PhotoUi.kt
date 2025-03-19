package com.hellosplash.androidassignment.presentation.photos.grid

import com.hellosplash.androidassignment.domain.photos.GetPhotosUseCase.PhotoInfo

data class PhotoUi(val photoId: String, val imageUrl: String)

fun PhotoInfo.toUiModel() = PhotoUi(
    photoId = this.id,
    imageUrl = this.url
)
