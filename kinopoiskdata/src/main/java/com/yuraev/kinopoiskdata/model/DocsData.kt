package com.yuraev.kinopoiskdata.model


data class DocsData(
    val id: Long = 0,
    val name: String? = null,
    val description: String? = null,
    val shortDescription: String? = null,
    val ageRating: String? = null,
    val year: Int? = null,
    val imdbRating: String? = "8",
    val posterData: PosterData? = null,
    val previewUrl: String? = null,
    val url: String? = null
)


data class PosterData(
    val previewUrl: String? = null,
    val url: String? = null
)
