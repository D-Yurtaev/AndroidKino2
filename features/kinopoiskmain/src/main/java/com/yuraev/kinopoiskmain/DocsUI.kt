package com.yuraev.kinopoiskmain
import kotlinx.serialization.Serializable

@Serializable
data class DocsUI(
    val id: Long = 0,
    val name: String? = null,
    val description: String? = null,
    val shortDescription: String? = null,
    val ageRating: String? = null,
    val year: Int? = null,
    val imdbRating: String? = "8",

    val previewUrl: String? = null,
    val url: String? = null
)
