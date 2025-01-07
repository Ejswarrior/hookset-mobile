package com.example.hookset_mobile.screens.createPost

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class CreatePost(navController: NavController): ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier) {
                CreatePostScreen()
            }
        }
    }

    val mediaPicker = registerForActivityResult(PickVisualMedia()) { uri ->
        if(uri != null) Log.d("createPost", uri.toString())
    }


    @Composable
    fun CreatePostScreen() {
        Column(modifier = Modifier.padding(24.dp, 12.dp)) {
            Button(
                modifier = Modifier.height(140.dp).fillMaxWidth().align(Alignment.CenterHorizontally),
                onClick = { mediaPicker.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))},
                shape = CutCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Icon(Icons.Filled.Add, "Add Post Icon", modifier = Modifier, Color.LightGray)
            }
        }
    }
}