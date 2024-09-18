package com.example.hookset_mobile

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

enum class Login_state(val value: String) {
    LoggedIn("logged_in"),
    LoggedOut("logged_out")
}

class AuthService(private val client: HttpClient, private val context: Context) {
    private val storeAuthToken = stringPreferencesKey("stored_auth_token")
    private var _loginState = Login_state.LoggedOut.value
    private val authServiceTag = "AuthService"


    fun getLoginState(): String {
        return _loginState
    }

  
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
        _loginState = Login_state.LoggedOut.value
    }

    suspend fun logIn(email: String, password: String): String {
        Log.d("loginResponse","before login")

        val response: HttpResponse = client.post("https://10.0.2.2:7225/login?email=$email&password=$password")
        Log.d("loginResponse", response.body<String>().toString())
        if(response.status === HttpStatusCode.OK) {
            updateAuthToken(response.body<String>().toString())
            _loginState = Login_state.LoggedIn.value
            return "Success"
        }
        else {
            if(_loginState == Login_state.LoggedIn.value) _loginState = Login_state.LoggedOut.value
            return "Failed"
        }

    }


}