package com.hello.splash.ui.details

import com.hello.splash.common.ui.mappers.DateFormatter
import com.hello.splash.domain.details.GetDetailsUseCase.DetailsInfo
import com.hello.splash.ui.R

data class DetailsUi(
    val description: String,
    val imageUrl: String,
    val gridItems: List<Pair<Int, String>>
)

fun DetailsInfo.toUiModel(dateFormatter: DateFormatter) = DetailsUi(
    description = this.description,
    imageUrl = this.url,
    gridItems = listOf(
        Pair(R.string.details_location, this.locationName),
        Pair(R.string.details_likes, this.likes),
        Pair(R.string.details_date, dateFormatter.format(this.creationDate))
    )
)
