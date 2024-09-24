package com.example.hookset_mobile.screens.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ui.components.posts.Post

class Posts(navController: NavController) {
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