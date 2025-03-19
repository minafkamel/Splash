package com.hellosplash.androidassignment.presentation.details

import com.hellosplash.androidassignment.domain.details.GetDetailsUseCase.DetailsInfo

data class DetailsUi(val location: String, val likes: String, val description: String)

fun DetailsInfo.toUiModel() = DetailsUi(
    location = this.locationName,
    likes = this.likes,
    description = this.description
)
