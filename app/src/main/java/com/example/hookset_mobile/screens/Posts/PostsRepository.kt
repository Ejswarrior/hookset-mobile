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
import kotlinx.serialization.json.Json

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
    val updatedDate: String?,
    @SerialName("bodyOfWaterCaughtIn")
    val bodyOfWaterCaughtIn: String?,
    @SerialName("length")
    val length: Int?,
    @SerialName("weight")
    val weight: Int?
)


class PostsRepository(val httpClient: HttpClient, val authService: AuthService, val context: Context, var postState: List<PostDTO>) {
    private val storeUserId = stringPreferencesKey("stored_user_id")

    public suspend fun getPosts() {

        val storage: Flow<String?> = context.dataStore.data.map { settings ->
            settings[storeUserId] ?: ""
        }
        storage.first()?.let { Log.d("userId", it) }
        val userId = storage.first()
        val posts = ApiBuilder(httpClient, authService).get<List<PostDTO>>("https://10.0.2.2:7225/posts/list-posts") {
            userId?.let { it1 -> parameters.append("userId", it1) }
        }

        if(posts.requestBody != null) Log.d("post body", Json.decodeFromString(posts.requestBody.first().toString()))
        if(posts.requestBody != null) postState = Json.decodeFromString(posts.requestBody.toString());
    }
}