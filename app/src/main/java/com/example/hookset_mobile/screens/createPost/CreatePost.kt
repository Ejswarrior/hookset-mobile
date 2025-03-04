package com.example.hookset_mobile.screens.createPost

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Scale
import coil.util.DebugLogger
import com.example.hookset_mobile.AuthService
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ui.components.HooksetInput.HooksetInput
import utils.GetScreenSize

class CreatePost(navController: NavController): ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Row() {
                CreatePostScreen()
            }
        }
    }
    private val httpClient: HttpClient by inject()
    private val authService: AuthService by inject()
    private val context: Context by inject()

    private var photoUri by mutableStateOf<Uri?>(null)
    private val createPostRepo: CreatePostRepository = CreatePostRepository(httpClient, authService, context)


    @Composable
    fun ColumnScope(modifier: Modifier, content: @Composable ColumnScope.()-> Unit) {
        Column(Modifier.fillMaxHeight().padding(0.dp,0.dp,0.dp, 48.dp), verticalArrangement = Arrangement.SpaceBetween) {
            content()
        }
    }

    @Composable
    fun CreatePostScreen(modifier: Modifier = Modifier) {
        val screenSize = GetScreenSize()

        val mediaPicker = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
            if(uri != null) photoUri = uri;
        }
        val imageLoader = LocalContext.current.imageLoader.newBuilder().logger(DebugLogger()).build()

        ColumnScope(modifier = modifier) {
            Column(
                Modifier
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState()).weight(1f)
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Log your catch",
                        color = Color.Black,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(bottom = 48.dp),
                        textAlign = TextAlign.Center
                    )
                }

                if(photoUri == null) Button(
                    modifier = modifier
                        .height(140.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    onClick = { mediaPicker.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) },
                    shape = CutCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Icon(Icons.Filled.Add, "Add Post Icon", modifier = Modifier, Color.LightGray)
                }
                else
                    AsyncImage(
                        imageLoader = imageLoader,
                        model = ImageRequest.Builder(LocalContext.current).data(photoUri).scale(Scale.FIT).crossfade(true).build(),
                        contentDescription = "Image picker",
                        onError = {it ->
                            Log.d("asyncImageError", it.toString())
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clickable { mediaPicker.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) }
                    )

                Row(modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {
                    HooksetInput(
                        modifier = modifier,
                        inputValue = createPostRepo.description,
                        onChange = { createPostRepo.description = it },
                        hidden = false,
                        multiLined = 10,
                        minLines = 6,
                        labelText = "Description"
                    )
                }

                Row(modifier = modifier.padding(0.dp, 12.dp, 0.dp, 0.dp)) {
                    createPostRepo.fishSpecies?.let {
                        HooksetInput(
                            modifier = modifier,
                            inputValue = it,
                            onChange = { createPostRepo.fishSpecies = it },
                            hidden = false,
                            labelText = "What type of fish was caught?"
                        )
                    }
                }

                Row(modifier = modifier.padding(0.dp, 12.dp, 0.dp, 0.dp)) {
                    HooksetInput(
                        modifier = modifier,
                        inputValue = createPostRepo.locationCaught,
                        onChange = { createPostRepo.locationCaught = it },
                        hidden = false,
                        labelText = "Where was this caught?"
                    )
                }


                Row(
                    modifier = modifier
                        .padding(0.dp, 12.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Weight", fontSize = 20.sp)
                    Box(modifier = modifier.width(180.dp)) {
                        HooksetInput(
                            modifier = modifier,
                            inputValue = createPostRepo.weight.toString(),
                            onChange = { createPostRepo.weight = it.toInt() },
                            hidden = false,
                            numInput = true
                        )
                    }
                }
                Row(
                    modifier = modifier
                        .padding(0.dp, 12.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Height", fontSize = 20.sp)
                    Box(modifier = modifier.width(180.dp)) {
                        HooksetInput(
                            modifier = modifier,
                            inputValue = createPostRepo.height.toString(),
                            onChange = { createPostRepo.height = it.toInt() },
                            hidden = false,
                            numInput = true
                        )
                    }
                }
                Row(
                    modifier = modifier
                        .padding(0.dp, 12.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Height", fontSize = 20.sp)
                    Box(modifier = modifier.width(180.dp)) {
                        HooksetInput(
                            modifier = modifier,
                            inputValue = createPostRepo.length.toString(),
                            onChange = { createPostRepo.length = it.toInt() },
                            hidden = false,
                            numInput = true
                        )
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth()
               .padding(24.dp, 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                OutlinedButton(onClick = { /*TODO*/ }, modifier = modifier.width(150.dp)) {
                    Text(text = "Cancel")
                }

                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            Log.d("fileCLick", "clicked")
                            if (photoUri !== null) {
                                createPostRepo.uploadPostImage(fileUrl = photoUri!!)
                            }
                        }
                        },
                    modifier = modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    enabled = photoUri.toString().isNotEmpty()
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}