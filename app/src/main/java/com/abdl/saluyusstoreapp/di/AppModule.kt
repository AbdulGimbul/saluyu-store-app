package com.abdl.saluyusstoreapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.abdl.saluyusstoreapp.data.api.ApiConfig
import com.abdl.saluyusstoreapp.data.api.ApiService
import com.abdl.saluyusstoreapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun peovideDataStore(@ApplicationContext context: Context): DataStore<Preferences>{
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            produceFile = { context.preferencesDataStoreFile(LoginSession.DATA) }
        )
    }

    @Singleton
    @Provides
    fun provideApiConfig(loginSession: LoginSession): ApiService {
        return ApiConfig.getApiService(loginSession)
    }

    @Singleton
    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }
}