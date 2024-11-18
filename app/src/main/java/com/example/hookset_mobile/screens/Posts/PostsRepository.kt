package com.example.hookset_mobile.screens.Posts

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.hookset_mobile.ApiBuilder
import com.example.hookset_mobile.AuthService
import com.example.hookset_mobile.dataStore
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO (
    @SerialName("id")
    val id: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("createdDate")
    val createdDate: String,
    @SerialName("likes")
    val likes: Int,
    @SerialName("description")
    val description: String,
    @SerialName("userName")
    val userName: String,
    @SerialName("updatedDate")
    val updatedDate: String,
)


class PostsRepository(val httpClient: HttpClient, val authService: AuthService, val context: Context) {
    private val storeUserId = stringPreferencesKey("stored_user_id")

    public suspend fun getPosts() {

        val storage: Flow<String?> = context.dataStore.data.map { settings ->
            settings[storeUserId] ?: ""
        }
        storage.first()?.let { Log.d("userId", it) }
        val userId = storage.first()
        val posts = ApiBuilder(httpClient, authService).get<List<PostDTO>>("https://10.0.2.2:7225/posts/list-posts") {
            userId?.let { it1 -> parameters.append("userId", it1) }
            parameters.append("perPage", "25")
            parameters.append("page", "1")
        }

        Log.d("post response", posts.body.toString())
    }
}