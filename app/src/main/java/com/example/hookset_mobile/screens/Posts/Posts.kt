package com.example.hookset_mobile.screens.Posts

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hookset_mobile.AuthService
import io.ktor.client.HttpClient
import org.koin.android.ext.android.inject
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
    private val httpClient: HttpClient by inject()
    private val authService: AuthService by inject()
    private val context: Context by inject()
    val modifier: Modifier = Modifier
    private val postRepo: PostsRepository = PostsRepository(httpClient, authService, context)



    @Composable
    fun PostList(posts: List<PostDTO>) {
        if(posts.isNotEmpty())
            LazyColumn(state = rememberLazyListState(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(items = posts) {post ->


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
        Column {
            if(!postRepo.loading) 
            PostList(posts = postRepo.postState)
            else 
                Text(text = "loading")
        }

    }
}