package ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Scale
import coil.util.DebugLogger
import com.example.hookset_mobile.R

@Composable
fun ProfilePicture(src: String, description: String, modifier: Modifier,  size: Int,){
    val imageLoader = LocalContext.current.imageLoader.newBuilder().logger(DebugLogger()).build()

    AsyncImage(
        imageLoader = imageLoader,
        model = ImageRequest.Builder(LocalContext.current).data(src).scale(Scale.FIT).crossfade(true).build(),
        contentDescription = description,
        placeholder = painterResource(R.drawable.profilepic),
        contentScale = ContentScale.Crop,
        modifier = modifier.size(size.dp).clip(CircleShape)
    )
}
