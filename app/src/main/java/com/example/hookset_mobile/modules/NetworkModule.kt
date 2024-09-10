package com.example.hookset_mobile.modules

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import org.koin.dsl.module


val networkModule = module {
    single {
        HttpClient(Android) {
            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
//                proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 7225))
            }
            install(Resources)
            install(HttpCookies)

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
class NetworkModule {
}