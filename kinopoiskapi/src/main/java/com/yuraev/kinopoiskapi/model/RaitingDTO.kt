package com.yuraev.kinopoiskapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingDTO(
    @SerialName("imdb")
    val imdb: Double? = null,
)
