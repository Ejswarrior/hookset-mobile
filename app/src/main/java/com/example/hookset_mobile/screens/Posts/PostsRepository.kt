package com.example.hookset_mobile.screens.Posts

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import com.example.hookset_mobile.ApiBuilder
import com.example.hookset_mobile.AuthService
import com.example.hookset_mobile.dataStore
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO (
    val id: String,
    val userId: String,
    val createdDate: String,
    val likes: Int,
    val description: String,
    val userName: String,
    val updatedDate: String?,
    val bodyOfWaterCaughtIn: String?,
    val length: Int?,
    val weight: Int?
)


class PostsRepository(val httpClient: HttpClient, val authService: AuthService, @SuppressLint("StaticFieldLeak") val context: Context): ViewModel() {
    private val storeUserId = stringPreferencesKey("stored_user_id")

    public suspend fun getPosts(): List<PostDTO>? {

        val storage: Flow<String?> = context.dataStore.data.map { settings ->
            settings[storeUserId] ?: ""
        }
        storage.first()?.let { Log.d("userId", it) }
        val userId = storage.first()
        val posts = ApiBuilder(httpClient, authService).get<PostDTO>("https://10.0.2.2:7225/posts/list-posts") {
            userId?.let { it1 -> parameters.append("userId", it1) }
        }
        if(posts.requestBody != null) Log.d("post body", posts.requestBody.toString())

        return posts.requestBody;
    }
}