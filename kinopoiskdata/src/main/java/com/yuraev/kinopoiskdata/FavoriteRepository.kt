package com.yuraev.kinopoiskdata

import com.yuraev.database.KinopoiskDataBase
import com.yuraev.kinopoiskapi.model.DocsDTO
import com.yuraev.kinopoiskdata.model.DocsData
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class FavoriteRepository @Inject constructor(
    private val dataBase: KinopoiskDataBase,
) {
    suspend fun saveNetworkResponseToCache(data: List<DocsData>) {
        val dbos = data.map { docs -> docs.toDocsDbo() }
        dataBase.docsDao.insert(dbos)
    }
    fun getAllFromDatabase(): Flow<List<DocsData>> {

        val dbRequest = dataBase.docsDao.getAll()
            .map { lst -> lst.map { it.toDocsData() } }

        return dbRequest

    }

    fun isExist(id: Long): Flow<Boolean> {
        return dataBase.docsDao.isExist(id)
    }
}
