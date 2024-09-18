package ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun FishLogText(text: String, color: Color, fontSize: Int, modifier: Modifier) {
    Text(text = text, color = color, fontSize = fontSize.sp, modifier = modifier)
}