package com.yuraev.kinopoisk.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.yuraev.database.KinopoiskDataBase
import com.yuraev.kinopoisk.BuildConfig
import com.yuraev.kinopoiskapi.KinopoiskApi
import com.yuraev.kinopoiskcommon.AndroidLogger
import com.yuraev.kinopoiskcommon.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideKinopoiskApi(): KinopoiskApi {
        return KinopoiskApi(
            baseUrl = BuildConfig.KP_API_BASE_URL,
            apiKey = BuildConfig.KP_API_KEY,
            client = null
        )
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): KinopoiskDataBase {
        return KinopoiskDataBase(context)
    }

    @Provides
    fun provideLogger(): Logger {
        return AndroidLogger()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(produceFile = { context.preferencesDataStoreFile("settings") })
    }


}
