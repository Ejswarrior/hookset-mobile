package com.example.hookset_mobile.screens.Posts

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PostsModel: ViewModel() {

    val posts = mutableStateListOf<PostDTO>()


}