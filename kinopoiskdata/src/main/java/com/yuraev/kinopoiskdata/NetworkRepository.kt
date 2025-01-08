package com.yuraev.kinopoiskdata

import com.yuraev.database.KinopoiskDataBase
import com.yuraev.database.model.DocsDBO
import com.yuraev.kinopoiskapi.KinopoiskApi
import com.yuraev.kinopoiskapi.model.DocsDTO
import com.yuraev.kinopoiskapi.model.KinopoiskDTO
import com.yuraev.kinopoiskcommon.Logger

import com.yuraev.kinopoiskdata.model.DocsData
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach


class NetworkRepository @Inject constructor(
    private val kinopoiskApi: KinopoiskApi,
    private val logger: Logger
){

     fun getFromServer(
         ageRating: String,
         imdbRating: String,
         year: String,
     ): Flow<RequestResult<List<DocsData>>> {
        val apiRequest = flow {
            emit(kinopoiskApi.getAnime(
                ageRating = ageRating,
                imdbRating = imdbRating,
                year = year
            ))
        }.onEach { result ->
            if (result.isFailure) {
                logger.e(Log_Tag,"error from server = ${result.exceptionOrNull()}")
            }
        }.map { result ->
            result.toRequestResult()
        }




        val start = flowOf<RequestResult<KinopoiskDTO>>(RequestResult.InProgress())
        return merge(apiRequest, start).map { result ->
            result.map { response -> response.docs.map { it.toDocsData() } }
        }
    }
    companion object{
        const val Log_Tag = "Repository"
    }

}



