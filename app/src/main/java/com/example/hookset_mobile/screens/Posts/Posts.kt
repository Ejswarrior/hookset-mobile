package com.example.hookset_mobile.screens.Posts

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hookset_mobile.AuthService
import io.ktor.client.HttpClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import ui.components.HooksetButton
import ui.components.posts.Post


class Posts(navController: NavController): ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier) {
                PostScreen()
            }
        }
    }
    val httpClient: HttpClient by inject()
    private val authService: AuthService by inject()
    private val context: Context by inject()
    val modifier: Modifier = Modifier
    
    
    @Composable
    fun PostList(posts: List<PostDTO>) {
        if(posts.isNotEmpty())
            LazyColumn(state = rememberLazyListState(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(posts) {post ->
                    Post(
                        avatarImageUrl =  "",
                        userName = post.userName,
                        timeStamp = post.createdDate,
                        fishSpecies = "Steelhead",
                        description = post.description,
                        postUrl = "postList.postUrl",
                        modifier = Modifier
                    )
                }
            }
    }
    

    @Composable
    fun PostScreen() {
        val postRepo: PostsRepository = PostsRepository(httpClient, authService, context, posts)

        Column {
            HooksetButton(modifier).button(variant = "primary", buttonText = "Posts", disabled = false, onButtonClick = { runBlocking { launch {
                val postsResponse = postRepo.getPosts()
                if(postsResponse != null) posts =
                    posts + postsResponse.toMutableList()

                Log.d("post response", postsResponse?.size.toString())
                Log.d("posts", posts.size.toString())
            }} })
            if() PostList(posts = posts)
        }

    }
}