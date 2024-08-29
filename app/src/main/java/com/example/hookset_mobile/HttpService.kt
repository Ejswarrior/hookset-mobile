package com.example.hookset_mobile

import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.get

class HttpService() {

        private val client: HttpClient = HttpClient() {
                install(Resources)
            }



    fun get(url: String) {
        client.get(url)
    }
}