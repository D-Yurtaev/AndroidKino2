package com.yuraev.kinopoiskapi

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.yuraev.kinopoiskapi.model.KinopoiskDTO
import com.yuraev.kinopoiskapi.utils.ApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface KinopoiskApi {
    @GET("v1.4/movie")
    suspend fun getAnime(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("selectFields") selectFields: Array<String> = arrayOf(
            "id",
            "name",
            "description",
            "shortDescription",
            "ageRating",
            "poster"
        ),
        @Query("type") type: String = "anime",
        @Query("ageRating") ageRating: String = "18",
        @Query("rating.imdb") imdbRating: String = "7",
        @Query("year") year: String = "2024",

    ): Result<KinopoiskDTO>
}

fun KinopoiskApi(
    baseUrl: String,
    apiKey: String,
    client: OkHttpClient? = null,
    json: Json = Json
): KinopoiskApi {
    val retrofit = retrofit(baseUrl, apiKey, client, json)
    return retrofit.create(KinopoiskApi::class.java)
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    client: OkHttpClient?,
    json: Json
): Retrofit {
    val contentType = "application/json".toMediaType()
    val converterFactory = json.asConverterFactory(contentType)

    val modifiedClient = (client?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(ApiKeyInterceptor(apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(modifiedClient)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(converterFactory)
        .build()

}
