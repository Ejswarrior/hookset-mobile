package com.example.hookset_mobile.screens.Posts

import com.example.hookset_mobile.AuthService
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface postDTO {

}
class PostsRepository(val httpClient: HttpClient, val authService: AuthService) {

    public fun getPosts() {

        val posts = httpClient.get()
    }
}