package com.yuraev.kinopoiskapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DocsDTO(
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("shortDescription") val shortDescription: String? = null,
    @SerialName("ageRating") val ageRating: Int? = null,
    @SerialName("rating") val ratingDTO: RatingDTO? = RatingDTO(),
    @SerialName("poster") val posterDTO: PosterDTO? = PosterDTO(),
    @SerialName("year") val year: Int? = null

)
