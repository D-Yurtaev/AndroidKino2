package com.yuraev.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "docs")
data class DocsDBO(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("name") val name: String? = null,
    @ColumnInfo("description") val description: String? = null,
    @ColumnInfo("shortDescription") val shortDescription: String? = null,
    @ColumnInfo("ageRating") val ageRating: String? = null,
    @ColumnInfo("year") val year: Int? = null,
    @ColumnInfo("rating_imdb") val imdbRating: String? = null,
    @Embedded val poster: PosterDBO? = null,
)


data class PosterDBO(
    @ColumnInfo("previewUrl") val previewUrl: String? = null,
    @ColumnInfo("url") val url: String? = null)
