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
    private val context: Context by inject()
    private var description by mutableStateOf("")
    private var photoUri by mutableStateOf<Uri?>(null)
    private var locationCaught by mutableStateOf("")
    private var weight by mutableStateOf<Int?>(null)
    private var height by mutableStateOf<Int?>(null)
    private var length by mutableStateOf<Int?>(null)
    private var fishSpecies by mutableStateOf<String?>(null)


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

    //adding form data to response
//        val client = HttpClient(Apache) {}
//
//        val file = File("path/to/some.file")
//        val chatId = "123"
//
//        client.submitFormWithBinaryData(
//            url = "https://api.telegram.org/bot<token>/sendDocument?chat_id=$chatId",
//            formData = formData {
//                append("document", file.readBytes(), Headers.build {
//                    append(HttpHeaders.ContentDisposition, "filename=${file.name}")
//                })
//            }
//        )

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
                        contentDescription = description,
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
                        inputValue = description,
                        onChange = { description = it },
                        hidden = false,
                        multiLined = 10,
                        minLines = 6,
                        labelText = "Description"
                    )
                }

                Row(modifier = modifier.padding(0.dp, 12.dp, 0.dp, 0.dp)) {
                    HooksetInput(
                        modifier = modifier,
                        inputValue = description,
                        onChange = { fishSpecies = it },
                        hidden = false,
                        labelText = "What type of fish was caught?"
                    )
                }

                Row(modifier = modifier.padding(0.dp, 12.dp, 0.dp, 0.dp)) {
                    HooksetInput(
                        modifier = modifier,
                        inputValue = description,
                        onChange = { locationCaught = it },
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
                            inputValue = description,
                            onChange = { weight = it.toInt() },
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
                            inputValue = description,
                            onChange = { height = it.toInt() },
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
                    onClick = { /*TODO*/ },
                    modifier = modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    enabled = description.isNotEmpty() && fishSpecies != null
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}