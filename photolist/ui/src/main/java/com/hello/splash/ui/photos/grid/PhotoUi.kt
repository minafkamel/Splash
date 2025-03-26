package com.hello.splash.ui.photos.grid

import com.hello.splash.domain.photos.GetPhotosUseCase.PhotoInfo

data class PhotoUi(val photoId: String, val imageUrl: String)

fun PhotoInfo.toUiModel() = PhotoUi(
    photoId = this.id,
    imageUrl = this.url
)
