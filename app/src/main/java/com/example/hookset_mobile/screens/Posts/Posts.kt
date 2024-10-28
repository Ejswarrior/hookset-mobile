package com.example.hookset_mobile.screens.Posts

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
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
    val httpClient: HttpClient by inject()
    private val authService: AuthService by inject()
    private val context: Context by inject()
    private val postRepo: PostsRepository = PostsRepository(httpClient, authService, context)

    val modifier: Modifier = Modifier
    object postList {
        val avatarImageUrl = "https://th.bing.com/th/id/OIP.L1svECFMJ4lNsseT6Ooi-gHaHa?rs=1&pid=ImgDetMain"
        val userName = "Ejswarrior"
        val timeStamp = "5m"
        val fishSpecies = "Steelhead"
        val description = "Look at this huge fish I caught at the niagara river! Shoutout to the guy that helped me net it!"
        val postUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFTQBraO32lJCrlInBc6A-YTHAW_C0ngGkfA&s"
        val modifier = Modifier
    }
    init {
        Log.d("postScreen", "hit post init")
        suspend {
            postRepo.getPosts()
        }
    }



    @Composable
    fun PostScreen() {

        LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            item {
                Post(
                    avatarImageUrl = postList.avatarImageUrl,
                    userName = postList.userName,
                    timeStamp = postList.timeStamp,
                    fishSpecies = postList.fishSpecies,
                    description = postList.description,
                    postUrl = postList.postUrl,
                    modifier = postList.modifier
                )
            }
            item {
                Post(
                    avatarImageUrl = postList.avatarImageUrl,
                    userName = postList.userName,
                    timeStamp = postList.timeStamp,
                    fishSpecies = postList.fishSpecies,
                    description = postList.description,
                    postUrl = postList.postUrl,
                    modifier = postList.modifier
                )
            }
            item {
                Post(
                    avatarImageUrl = postList.avatarImageUrl,
                    userName = postList.userName,
                    timeStamp = postList.timeStamp,
                    fishSpecies = postList.fishSpecies,
                    description = postList.description,
                    postUrl = postList.postUrl,
                    modifier = postList.modifier
                )
            }
            item {
                Post(
                    avatarImageUrl = postList.avatarImageUrl,
                    userName = postList.userName,
                    timeStamp = postList.timeStamp,
                    fishSpecies = postList.fishSpecies,
                    description = postList.description,
                    postUrl = postList.postUrl,
                    modifier = postList.modifier
                )
            }
        }
    }
}