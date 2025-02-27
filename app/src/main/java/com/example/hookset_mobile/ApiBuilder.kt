package com.example.hookset_mobile

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLBuilder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GetResponseReturn <out T>(val status: HttpStatusCode, val responseText: String, val body: List<T>?) {
     val statusCode = status;
     val errorText = responseText;
     val requestBody = body
 }

class ApiBuilder public constructor(val httpClient: HttpClient, val authService: AuthService) {

    suspend fun retrieveAuthTokenOrLogOut(): String {
        val authToken = authService.getAuthToken()
        if (authToken == null) {
            authService.logOut()
            throw Error()
        }else return authToken
    }

    suspend inline fun <reified T>get(path: String, noinline urlParams:  URLBuilder.(URLBuilder) -> Unit): GetResponseReturn<T>  {

        try {
            val authToken = retrieveAuthTokenOrLogOut()

            val response = httpClient.get(path) {
                headers {
                    append(HttpHeaders.Authorization, authToken)
                }
                url (
                    urlParams
                )
                setBody("")

            }

            if (
                response.status.value.toString() == HttpStatusCode.OK.value.toString() ||
                response.status.value.toString() == "405") {
                val responseBody = Json.encodeToString<List<T>>(value = response.body())
                return GetResponseReturn<T>(response.status, response.status.toString(), Json.decodeFromString(responseBody))
            } else {
                return GetResponseReturn<T>(response.status, response.status.toString(), null)
            }
        } catch(e: Exception) {
            Log.e("get post", e.toString())
            throw Error(e)
        }
    }

    suspend fun post(path: String) {
        val authToken = retrieveAuthTokenOrLogOut()
        val response = httpClient.post(path) {
            headers {
                append(HttpHeaders.Authorization, authToken)
            }
            setBody("")
        }
    }

}