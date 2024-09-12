package ui.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PostImage(height: Int, width: Int, src: String, context: Context, description: String,  modifier: Modifier, onPress: (() -> Unit)?) {
    AsyncImage(model = ImageRequest.Builder(context).data(src).crossfade(true).build(), contentDescription = description, modifier = modifier.width(width.dp).height(height.dp).clickable { onPress?.invoke() })
}