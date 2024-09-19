package ui.components

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hookset_mobile.R

@Composable
fun PostButton(modifier: Modifier, onClick: () -> Unit) {
    Button(onClick = { /*TODO*/ }, shape = CircleShape, colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),contentPadding = PaddingValues(0.dp),  modifier = modifier.size(50.dp)) {
        Icon(
            painterResource(id = R.drawable.fish), contentDescription = "fish icon", tint = Color.White, modifier = modifier.size(18.dp))
    }
}

@Preview
@Composable
fun PostButtonPreview() {
    PostButton(modifier = Modifier) { Log.d("postButton", "pressed post button") }
}
