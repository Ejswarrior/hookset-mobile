package ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hookset_mobile.R
import utils.getFormatedNumber


@Composable
fun IconButton(text: String, icon: Int, description: String, modifier: Modifier, onPress: () -> Unit) {

    TextButton(onClick = {onPress()}) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painterResource(id = icon), contentDescription = description, tint = Color.Black, modifier = modifier
                    .height(18.dp)
                    .width(18.dp))

            Text(text = text, fontSize = 16.sp, color = Color.Black)
        }
    }

}
@Preview
@Composable
fun PreviewButton() {
    IconButton(text = getFormatedNumber(440402), icon = R.drawable.baseline_favorite_border_24, description = "like button",  modifier = Modifier, onPress = { Log.d("iconButton", "pressed")})
}