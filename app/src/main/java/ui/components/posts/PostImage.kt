package ui.components.posts

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Scale
import coil.util.DebugLogger

@Composable
fun PostImage(height: Int, width: Int, src: String, description: String,  modifier: Modifier,  onPress: (() -> Unit)? = null) {
    val imageLoader = LocalContext.current.imageLoader.newBuilder().logger(DebugLogger()).build()
    AsyncImage(
        imageLoader = imageLoader,
        model = ImageRequest.Builder(LocalContext.current).data(src).scale(Scale.FIT).crossfade(true).build(),
        contentDescription = description,
        onError = {it ->
            Log.d("asyncImageError", it.toString())
        },
        modifier = modifier
        .width(width.dp)
        .height(height.dp)
        .clickable { onPress?.invoke() }
    )
}
