package com.example.hookset_mobile.screens.posts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class Posts {



    @Composable
    fun PostScreen() {
        LazyColumn(modifier = Modifier.verticalScroll(rememberScrollState())) {

        }
    }
}