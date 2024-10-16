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
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map

enum class Login_state(val value: String) {
    LoggedIn("logged_in"),
    LoggedOut("logged_out")
}
interface LoginResponse {
    val token: String
    val userId: String
    val firstName: String
    val lastName: String
}

class UserInfo(userId: String, firstName: String, lastName: String) {
    public val userId = userId
    public val firstName = firstName
    public val lastName = lastName
}

class AuthService(private val client: HttpClient, private val context: Context) {
    private val storeAuthToken = stringPreferencesKey("stored_auth_token")
    private val storeUserId = stringPreferencesKey("stored_user_id")
    private val storeFirstName = stringPreferencesKey("stored_firstname")
    private val storeLastName = stringPreferencesKey("stored_lastname")

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

    suspend fun storeUserData(userId:String, firstName: String, lastName: String) {
        context.dataStore.edit { settings ->
            settings[storeUserId] = userId
            settings[storeFirstName] = firstName
            settings[storeLastName] = lastName
        }
    }

    suspend fun getUserData(): UserInfo {
        var firstName = ""

        val userValues: Flow<String> = context.dataStore.data.map { settings ->
            settings[storeUserId] ?: ""
            firstName = settings[storeFirstName] ?: ""
            settings[storeLastName] ?: ""
        }

        return UserInfo(userId = userValues.first(), firstName =  firstName, lastName = userValues.last())
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
        Log.d("loginResponse", response.status.toString())
        Log.d("statusCode", if(HttpStatusCode.OK.value.toString() == response.status.value.toString()) "true" else "false")
        if(response.status.value.toString() == HttpStatusCode.OK.value.toString()) {
            Log.d("login body", response.body<LoginResponse>().toString())
            val loginResponse: LoginResponse = response.body()
            Log.d("loging body", "before update auth")
            updateAuthToken(loginResponse.token)
            Log.d("loging body", "after update auth")

            storeUserData(loginResponse.userId, loginResponse.firstName, loginResponse.lastName)
            Log.d("loging body", "after update user values")

            _loginState = Login_state.LoggedIn.value
            Log.d("login", "hit success")
            return "Success"
        }
        else {
            if(_loginState == Login_state.LoggedIn.value) _loginState = Login_state.LoggedOut.value
            return "Failed"
        }

    }


}