package com.yuraev.kinopoiskmain.fulldoc

import com.yuraev.kinopoiskdata.FavoriteRepository
import com.yuraev.kinopoiskdata.model.DocsData
import com.yuraev.kinopoiskdata.model.PosterData
import com.yuraev.kinopoiskmain.DocsUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveToFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(doc: DocsUI) {
        withContext(Dispatchers.IO) {
            favoriteRepository.saveNetworkResponseToCache(listOf(doc.toDocsData()))
        }
    }
}
internal fun DocsUI.toDocsData(): DocsData {
    return DocsData(
        id = id,
        name = name,
        description = description,
        shortDescription = shortDescription,
        ageRating = ageRating,
        year = year,
        imdbRating = imdbRating,
        posterData = PosterData(previewUrl = previewUrl, url = url)

    )
}
