package com.yuraev.kinopoiskmain.fulldoc

import com.yuraev.kinopoiskdata.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsExistInFavoritesUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(id: Long): Flow<Boolean> {
        return favoriteRepository.isExist(id)
    }
}
