package com.abdl.saluyusstoreapp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginSession @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        const val DATA = "Data"
        private const val IsLogin = "IsLogin"
        private const val UserId = "UserId"
        private const val USERNAME = "Username"
        private const val TOKEN = "Token"
        val name = stringPreferencesKey(USERNAME)
        val isLogin = booleanPreferencesKey(IsLogin)
        val userToken = stringPreferencesKey(TOKEN)
        val userId = stringPreferencesKey(UserId)
    }

    fun isUserLoggedIn(): Flow<Boolean>{
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { pref ->
            pref[isLogin] ?: false
        }
    }

    fun getUsername(): Flow<String>{
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { pref ->
            pref[name] ?: ""
        }
    }

    fun getToken(): Flow<String>{
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { pref ->
            pref[userToken] ?: ""
        }
    }

    fun getUserId(): Flow<String>{
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { pref ->
            pref[userId] ?: ""
        }
    }

    suspend fun setUserData(isUserLoggedIn: Boolean, username: String, token: String, idUser: String) {
        dataStore.edit { pref ->
            pref[isLogin] = isUserLoggedIn
            pref[name] = username
            pref[userToken] = token
            pref[userId] = idUser
        }
    }

    suspend fun clearData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}