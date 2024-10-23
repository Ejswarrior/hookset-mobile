package com.example.hookset_mobile

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerialName
 class FailedResponse(val status: HttpStatusCode, val responseText: String) {
     val statusCode = status;
     val errorText = responseText;
 }

class ApiBuilder private constructor(val httpClient: HttpClient, val authService: AuthService) {

    suspend inline fun <reified T>get(path: String): T  {
        val authToken = authService.getAuthToken()
        if (authToken == null) {
            authService.logOut()
            throw Error()
        }

        val response = httpClient.get(path) {
            headers {
                append(HttpHeaders.Authorization, authToken)
            }
        }
        if (response.status.value.toString() == HttpStatusCode.OK.value.toString()) {
            val responseBody: T = response.body()
            return responseBody
        } else {
            return FailedResponse(response.status, response.toString())
        }
    }
}