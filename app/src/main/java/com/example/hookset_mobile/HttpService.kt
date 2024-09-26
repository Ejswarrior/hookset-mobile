package com.example.hookset_mobile

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class HttpService(val client: HttpClient, val authService: AuthService) {
    var authToken: String = ""

    init {
        suspend {
            val token = authService.getAuthToken()
            if(token == null) authService.logOut()
            else authToken = token
        }
    }


    suspend inline fun <reified ResponseType> get(url: String): ResponseType {
            val response = client.get(url) {
                headers{
                    append(HttpHeaders.Authorization, authToken)
                }
            }
            return response.body<ResponseType>()
    }
}