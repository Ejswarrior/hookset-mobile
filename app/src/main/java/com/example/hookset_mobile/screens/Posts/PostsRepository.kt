package com.example.hookset_mobile.screens.Posts

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.hookset_mobile.ApiBuilder
import com.example.hookset_mobile.AuthService
import com.example.hookset_mobile.dataStore
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.map
import java.util.Date

data class PostDTO (
    val id: String,
    val userId: String,
    val createdDate: Date,
    val likes: Int,
    val description: String,
    val userName: String,
    val updatedDate: String,
)


class PostsRepository(val httpClient: HttpClient, val authService: AuthService, val context: Context) {
    private val storeUserId = stringPreferencesKey("stored_user_id")

    public suspend fun getPosts() {
        val userId: String = "";

         context.dataStore.data.map { settings ->
            settings[storeUserId] ?: ""
        }
        val posts = ApiBuilder(httpClient, authService).get<PostDTO>("https://10.0.2.2:7225/posts/") {
            parameters.append("userId", userId)
            parameters.append("perPage", "25")
            parameters.append("page", "1")
        }

        Log.d("post response", posts.toString())
    }
}