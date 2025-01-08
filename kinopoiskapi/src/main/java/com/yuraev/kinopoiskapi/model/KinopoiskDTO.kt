package com.yuraev.kinopoiskapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class KinopoiskDTO(

    @SerialName("docs") val docs: ArrayList<DocsDTO> = arrayListOf(),
    @SerialName("total") val total: Int? = null,
    @SerialName("limit") val limit: Int? = null,
    @SerialName("page") val page: Int? = null,
    @SerialName("pages") val pages: Int? = null

)
