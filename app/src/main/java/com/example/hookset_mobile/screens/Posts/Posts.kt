package com.example.hookset_mobile.screens.Posts

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hookset_mobile.AuthService
import io.ktor.client.HttpClient
import org.koin.android.ext.android.inject
import ui.components.posts.Post
import utils.GetScreenSize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class Posts(val navController: NavController): ComponentActivity() {
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

                    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy h:mma", Locale.ENGLISH)
                    val parsedDate = LocalDateTime.parse(post.createdDate, DateTimeFormatter.ISO_DATE_TIME)

                    Post(
                        avatarImageUrl =  "",
                        userName = post.userName,
                        timeStamp = dateFormatter.format(parsedDate),
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
        val screenSize = GetScreenSize()
        Log.d("screnSize", screenSize.toString())
        Column {
             Row(
                 Modifier
                     .background(Color.Black)
                     .fillMaxWidth()
                     .height(64.dp)
                     .align(Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                 Text(text = "Fish Logs", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight(600))

             }


            if(!postRepo.loading)
                PostList(posts = postRepo.postState)
            else 
                Text(text = "loading")

        }

        Box(modifier = Modifier.offset((screenSize.width - 65.dp), (screenSize.height - 145.dp))) {
            SmallFloatingActionButton(onClick = { navController.navigate("createPost")}, contentColor = Color(Color.White.value), containerColor = Color(Color.Red.value)) {
                Icon(Icons.Filled.Add, "Add Post Icon", modifier = Modifier, Color.White)
            }
        }
    }
}