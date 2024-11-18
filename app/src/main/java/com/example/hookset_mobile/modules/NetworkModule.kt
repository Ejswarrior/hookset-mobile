package com.example.hookset_mobile.modules

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import javax.net.ssl.SSLSession


val networkModule = module {
    single {
        HttpClient(OkHttp) {
            engine {
                config {
                    followRedirects(true)
                    hostnameVerifier(hostnameVerifier = { hostname: String, session: SSLSession -> if(hostname == "10.0.2.2") true else false })
                }

            }
            install(Resources)
            install(HttpCookies)
            install(ContentNegotiation) {
                        json()
                ContentType("application", "json+hal")
            }
            install(ResponseObserver) {
                    onResponse { reponse ->
                        Log.d("url", reponse.headers.toString())
                        Log.d("HTTP Status", reponse.status.value.toString())
                    }
                }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

}
