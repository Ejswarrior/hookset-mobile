package ui.components.posts

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hookset_mobile.R
import ui.components.FishLogText
import ui.components.IconButton
import ui.components.ProfilePicture
val dimensions = Resources.getSystem().displayMetrics
val postWidth = dimensions.widthPixels -24
@Composable
fun PostHeader(avatarImageUrl: String, userName: String, timeStamp: String, fishSpecies: String, modifier: Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = modifier
        .padding(8.dp)
        .width(postWidth.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.width(
            postWidth.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ProfilePicture(src = avatarImageUrl, description = "Profile picture", modifier = modifier, size = 45)

                Column {
                    Text(text = userName, fontSize = 18.sp, color = Color.Black)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(text = timeStamp, fontSize = 16.sp, color = Color.LightGray)
                        Text(text = " -  $fishSpecies", fontSize = 16.sp, color = Color.LightGray)
                    }
                }
            }

            FishLogText(text = "...", color = Color.Black, fontSize = 18, modifier = modifier )

        }
    }
}

@Composable
fun PostBody(description: String, postUrl: String, modifier: Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(text = description, maxLines = 4, fontSize = 16.sp, overflow = TextOverflow.Ellipsis, color = Color.Black, textAlign = TextAlign.Center, modifier = modifier.widthIn(min = 10.dp, max = postWidth.dp))
        PostImage(height = 150, width = postWidth, src = postUrl, description = "Picture of a fish", modifier = modifier)
    }
}
@Composable
fun PostFooter(modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.width(210.dp)) {
        IconButton(text = "246", icon = R.drawable.baseline_favorite_border_24, description = "Favorite button" , modifier = modifier) {
            Log.d("iconButton", "button Pressed")
        }

        IconButton(text = "246", icon = R.drawable.baseline_comment_24, description = "Comment button" , modifier = modifier) {
            Log.d("iconButton", "button Pressed")
        }
    }
}


@Composable
fun Post(avatarImageUrl: String, userName: String, timeStamp: String, fishSpecies: String, description: String, postUrl: String, modifier: Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = modifier.border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(16.dp))) {
        PostHeader(avatarImageUrl = avatarImageUrl, userName = userName, timeStamp = timeStamp, fishSpecies = fishSpecies, modifier = modifier)
        PostBody(description = description, postUrl = postUrl, modifier = modifier)
    }
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
        PostFooter(modifier = modifier)
    }
}

@Preview
@Composable
fun PostPreview() {
   Post(avatarImageUrl = "https://th.bing.com/th/id/OIP.L1svECFMJ4lNsseT6Ooi-gHaHa?rs=1&pid=ImgDetMain", userName = "Ejswarrior", timeStamp = "5m", fishSpecies = "Steelhead", description = "Look at this huge fish I caught at the niagara river! Shoutout to the guy that helped me net it!", postUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFTQBraO32lJCrlInBc6A-YTHAW_C0ngGkfA&s", modifier = Modifier)
}


