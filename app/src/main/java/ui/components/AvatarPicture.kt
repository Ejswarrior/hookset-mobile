package ui.components

import android.content.Context
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hookset_mobile.R
import org.jetbrains.annotations.Async

@Preview
@Composable
fun ProfilePicture(context: Context, src: String, description: String, modifier: Modifier,  size: Int,){
    AsyncImage(
        model = ImageRequest.Builder(context).data(src).crossfade(true).build(),
        contentDescription = description,
        placeholder = painterResource(R.drawable.profilepic),
        contentScale = ContentScale.Crop,
        modifier = modifier.size(size.dp).clip(CircleShape)
    )
}
