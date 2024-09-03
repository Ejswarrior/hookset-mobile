package com.example.hookset_mobile

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthService(val client: HttpClient, val context: Context) {
    private val storeAuthToken = stringPreferencesKey("stored_auth_token")


    suspend fun getAuthToken(): String? {
        val authToken: Flow<String?> = context.dataStore.data.map { settings ->
            settings[storeAuthToken]
        }
        return authToken.first()
    }

    suspend fun updateAuthToken(newAuthToken: String) {
        context.dataStore.edit { settings ->
            settings[storeAuthToken] = newAuthToken
        }
    }


    private suspend fun removeAuthToken() {
        context.dataStore.edit { settings ->
            settings.remove(storeAuthToken)
        }
    }

    public suspend fun logOut() {
        removeAuthToken()
        // TODO: need to add additional logout functionality
    }

    suspend fun logIn() {
        updateAuthToken("")
        // TODO: need to add login functionality
    }


}