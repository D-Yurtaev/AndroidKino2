package com.yuraev.kinopoiskmain.favorite

import com.yuraev.kinopoiskdata.FavoriteRepository
import com.yuraev.kinopoiskmain.DocsUI
import com.yuraev.kinopoiskmain.network.toDocsUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository

) {
    operator fun invoke(): Flow<List<DocsUI>> {
        return favoriteRepository.getAllFromDatabase()
            .map { lst -> lst.map { it.toDocsUI() } }


    }
}


