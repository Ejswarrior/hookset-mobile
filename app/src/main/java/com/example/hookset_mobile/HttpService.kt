package com.example.hookset_mobile

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class HttpService(val client: HttpClient) {




    fun get(url: String) {
        suspend {
            client.get(url)

        }
    }
}