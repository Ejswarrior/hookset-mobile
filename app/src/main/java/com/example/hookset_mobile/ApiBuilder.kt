package com.example.hookset_mobile

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLBuilder

class GetResponseReturn <out T>(val status: HttpStatusCode, val responseText: String, val body: T?) {
     val statusCode = status;
     val errorText = responseText;
     val requestBody = body
 }

class ApiBuilder public constructor(val httpClient: HttpClient, val authService: AuthService) {

    suspend inline fun <reified T>get(path: String, noinline urlParams:  URLBuilder.(URLBuilder) -> Unit): GetResponseReturn<T>  {
        val authToken = authService.getAuthToken()
        if (authToken == null) {
            authService.logOut()
            throw Error()
        }

        val response = httpClient.get(path) {
            headers {
                append(HttpHeaders.Authorization, authToken)
            }
            url (
                urlParams
            )

        }
        if (response.status.value.toString() == HttpStatusCode.OK.value.toString()) {
            val responseBody: T = response.body()
            return GetResponseReturn<T>(response.status, response.status.toString(), responseBody)
        } else {
            return GetResponseReturn<T>(response.status, response.status.toString(), null)
        }
    }
}