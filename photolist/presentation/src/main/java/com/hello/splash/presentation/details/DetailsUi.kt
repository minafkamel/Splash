package com.hello.splash.presentation.details

import com.hello.splash.common.ui.mappers.DateFormatter
import com.hello.splash.domain.details.GetDetailsUseCase.DetailsInfo

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
