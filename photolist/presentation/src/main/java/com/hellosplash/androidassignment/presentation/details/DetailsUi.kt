package com.hellosplash.androidassignment.presentation.details

import com.hellosplash.androidassignment.common.ui.mappers.DateFormatter
import com.hellosplash.androidassignment.domain.details.GetDetailsUseCase.DetailsInfo

data class DetailsUi(
    val location: String,
    val likes: String,
    val description: String,
    val date: String
)

fun DetailsInfo.toUiModel(dateFormatter: DateFormatter) = DetailsUi(
    location = this.locationName,
    likes = this.likes,
    description = this.description,
    date = dateFormatter.format(this.creationDate)
)
