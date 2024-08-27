package com.example.hookset_mobile

import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.Resources

class HttpService( authToken: String) {
    private var authToken: String? = null

    init {
        this.authToken = authToken
    }

    companion object {
        @Volatile
        private var instance: HttpService? = null

        private val client: HttpClient by lazy {
            HttpClient() {
                install(Resources)
            }
        }
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: HttpService(authToken = ).also { instance = it }
            }

    }


    fun get() {

    }
}