package com.yuraev.kinopoiskdata

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviePreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val IsOnly2024 = booleanPreferencesKey("movie_year")
        val Imdb = booleanPreferencesKey("imdb")
        val IS_18_OLD = booleanPreferencesKey("is_18_old")
    }

    suspend fun saveMovieYear(isOnly2024: Boolean) {
        dataStore.edit { preferences ->
            preferences[IsOnly2024] = isOnly2024
        }
    }

    val isOnly2024: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IsOnly2024] == true
    }

    suspend fun saveImdb(isImdbMoreSeven: Boolean) {
        dataStore.edit { preferences ->
            preferences[Imdb] = isImdbMoreSeven
        }
    }

    val isImdbMoreSeven: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[Imdb] == true
    }

    suspend fun is18Old(is18Old: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_18_OLD] = is18Old
        }
    }

    val is18Old: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_18_OLD] == true
    }
}
