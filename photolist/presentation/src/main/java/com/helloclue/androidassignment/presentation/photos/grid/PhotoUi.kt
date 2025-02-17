package com.helloclue.androidassignment.presentation.photos.grid

import com.helloclue.androidassignment.domain.GetPhotosUseCase.PhotoInfo

data class PhotoUi(val photoId: String, val imageUrl: String)

fun PhotoInfo.toUiModel() = PhotoUi(
    photoId = this.id,
    imageUrl = this.url
)
