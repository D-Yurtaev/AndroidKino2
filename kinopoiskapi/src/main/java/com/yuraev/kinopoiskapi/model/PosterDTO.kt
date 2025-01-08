package com.yuraev.kinopoiskapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PosterDTO(

    @SerialName("previewUrl") val previewUrl: String? = null,
    @SerialName("url") val url: String? = null

)
