package com.yuraev.kinopoiskmain.network

import com.yuraev.kinopoiskdata.NetworkRepository
import com.yuraev.kinopoiskdata.RequestResult
import com.yuraev.kinopoiskdata.map
import com.yuraev.kinopoiskdata.model.DocsData
import com.yuraev.kinopoiskdata.model.PosterData
import com.yuraev.kinopoiskmain.DocsUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFromServerSUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
     operator fun invoke(
        ageRating: String,
        imdbRating: String,
        year: String,
    ): Flow<RequestResult<List<DocsUI>>> {
        return networkRepository.getFromServer(
            ageRating = ageRating,
            imdbRating = imdbRating,
            year = year
        ).map { result: RequestResult<List<DocsData>> ->
            result.map { docs -> docs.map { it.toDocsUI() } }
        }
    }
}

internal fun DocsData.toDocsUI(): DocsUI {
    return DocsUI(
        id = id,
        name = name,
        description = description,
        shortDescription = shortDescription,
        ageRating = ageRating,
        year = year,
        imdbRating = imdbRating,
        previewUrl = posterData?.previewUrl,
        url = posterData?.url,
    )
}







