package com.yuraev.kinopoiskdata

import com.yuraev.database.model.DocsDBO
import com.yuraev.database.model.PosterDBO
import com.yuraev.kinopoiskapi.model.DocsDTO
import com.yuraev.kinopoiskapi.model.PosterDTO
import com.yuraev.kinopoiskdata.model.DocsData
import com.yuraev.kinopoiskdata.model.PosterData

fun DocsDTO.docsDbo(): DocsDBO {
    return DocsDBO(
        id = id ?: 0,
        name = name,
        description = description,
        shortDescription = shortDescription,
        ageRating = ageRating.toString(),
        year = year,
        imdbRating = ratingDTO?.imdb?.toString(),
        poster = posterDTO.toPosterDbo()
    )
}
fun DocsDTO.toDocsData(): DocsData {
    return DocsData(
        id = id ?: 0,
        name = name,
        description = description,
        shortDescription = shortDescription,
        ageRating = ageRating.toString(),
        year = year,
        imdbRating = ratingDTO?.imdb?.toString(),
        posterData = posterDTO.toPosterData()
    )
}

internal fun PosterDTO?.toPosterData(): PosterData? {
    return this?.let { PosterData(previewUrl = it.previewUrl, url = it.url) }
}

internal fun PosterDTO?.toPosterDbo(): PosterDBO? {
    return this?.let { PosterDBO(previewUrl = it.previewUrl, url = it.url) }
}
fun DocsDBO.toDocsData(): DocsData {
    return DocsData(
        id = id,
        name = name,
        description = description,
        shortDescription = shortDescription,
        ageRating = ageRating.toString(),
        year = year,
        imdbRating = imdbRating,
        posterData = poster.toPosterData()
    )
}

internal fun PosterDBO?.toPosterData(): PosterData? {
    return this?.let { PosterData(previewUrl = it.previewUrl, url = it.url) }
}
fun DocsData.toDocsDbo(): DocsDBO {
    return DocsDBO(
        id = id,
        name = name,
        description = description,
        shortDescription = shortDescription,
        ageRating = ageRating.toString(),
        year = year,
        imdbRating = imdbRating,
        poster = posterData.toPosterDbo()
    )
}
private fun PosterData?.toPosterDbo(): PosterDBO? {
    return this?.let { PosterDBO(previewUrl = it.previewUrl, url = it.url) }
}




